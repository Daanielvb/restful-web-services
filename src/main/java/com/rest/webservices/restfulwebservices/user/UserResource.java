package com.rest.webservices.restfulwebservices.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
public class UserResource {

    @Autowired
    private UserDAOService userDAOService;

    @GetMapping(path = "/users")
    public List<User> retrieveAll(){
        return userDAOService.findAll();
    }

    @GetMapping(path = "/users/{id}")
    public Resource<User> retrieve(@PathVariable Integer id) {
        User user = userDAOService.findOne(id);
        if (user == null){
            throw new UserNotFoundException("id-" + id);
        }
        Resource<User> model = new Resource<>(userDAOService.findOne(id));
        ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAll());
        model.add(linkTo.withRel("all-users"));

        return model;
    }

    @PostMapping(path = "/users")
    public ResponseEntity<User> create(@Valid @RequestBody User user){
        User savedUser = userDAOService.save(user);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping(path = "/users/{id}")
    public void delete(@PathVariable Integer id){
        User user = userDAOService.deleteById(id);
        if (user == null){
            throw new UserNotFoundException("id-"+ id);
        }
    }


}
