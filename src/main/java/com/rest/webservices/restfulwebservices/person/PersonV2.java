package com.rest.webservices.restfulwebservices.person;

public class PersonV2 {

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;

    public PersonV2(String name) {
        this.name = name;
    }
}
