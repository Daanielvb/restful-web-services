package com.rest.webservices.restfulwebservices.person;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonVersioningController {

    @GetMapping(path="/v1/person")
    public PersonV1 getPersonv1 (){
        return new PersonV1("name", "lastname");
    }

    @GetMapping(path="/v2/person")
    public PersonV2 getPersonv2 (){
        return new PersonV2("full name");
    }

    @GetMapping(path="/person/param", params = "version=1")
    public PersonV1 paramV1(){
        return new PersonV1("name", "lastname");
    }

    @GetMapping(path="/person/param", params = "version=2")
    public PersonV2 paramV2(){
        return new PersonV2("full name");
    }

    @GetMapping(path="/person/header", headers = "X-API-VERSION=1")
    public PersonV1 headerV1(){
        return new PersonV1("name", "lastname");
    }

    @GetMapping(path="/person/header", headers = "X-API-VERSION=2")
    public PersonV2 headerV2(){
        return new PersonV2("full name");
    }

    @GetMapping(path="/person/produces", produces = "application/vnd.company.app-v1+json")
    public PersonV1 producersV1(){
        return new PersonV1("name", "lastname");
    }

    @GetMapping(path="/person/producers", produces = "application/vnd.company.app-v2+json")
    public PersonV2 producersV2(){
        return new PersonV2("full name");
    }
}
