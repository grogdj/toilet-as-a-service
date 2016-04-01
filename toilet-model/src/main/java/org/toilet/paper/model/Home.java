/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.toilet.paper.model;

import java.util.List;

/**
 *
 * @author salaboy
 */
public class Home {

    private String id;
    private String name;
    private List<Person> persons;
    private List<Bathroom> bathrooms;

    public Home() {
    }

    public Home(String id, String name, List<Person> persons, List<Bathroom> bathrooms) {
        this.id = id;
        this.name = name;
        this.persons = persons;
        this.bathrooms = bathrooms;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    public List<Bathroom> getBathrooms() {
        return bathrooms;
    }

    public void setBathrooms(List<Bathroom> bathrooms) {
        this.bathrooms = bathrooms;
    }

}
