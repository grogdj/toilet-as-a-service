/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.toilet.model;

import javax.ws.rs.core.Link;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 *
 * @author salaboy
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Bathroom {

    private String id;
    private String name;

    @XmlJavaTypeAdapter(Link.JaxbAdapter.class)
    private Link self;

    public Bathroom() {
    }

    public Bathroom(String name) {
        this.name = name;
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

    public Link getSelf() {
        return self;
    }

    public void setSelf(Link self) {
        this.self = self;
    }

    @Override
    public String toString() {
        return "Bathroom{" + "id=" + id + ", name=" + name + '}';
    }

}
