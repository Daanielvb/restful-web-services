package com.rest.webservices.restfulwebservices.person;

public class PersonV1 {

    private String firstName;

    private String lastName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public PersonV1(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
