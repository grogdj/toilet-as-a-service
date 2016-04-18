/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.toilet.model;

import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.Link;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 *
 * @author salaboy
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Home {

    private String id;
    private String name;

    @XmlTransient
    private List<Person> persons = new ArrayList<>();
 
    @XmlTransient
    private List<Bathroom> bathrooms = new ArrayList<>();

    @XmlJavaTypeAdapter(Link.JaxbAdapter.class)
    private Link self;

    public Home() {
    }

    public Home(String name, List<Person> persons, List<Bathroom> bathrooms) {
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

    public Link getSelf() {
        return self;
    }

    public void setSelf(Link self) {
        this.self = self;
    }

}
