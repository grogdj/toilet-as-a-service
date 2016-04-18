/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.toilet.model;

import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author salaboy
 */
@XmlRootElement
public class Roll {

    public enum Type {
        SMALL, MEDIUM, BIG
    };
    private String name;
    private Date startDate = new Date();
    private Type type = Type.MEDIUM;

    public Roll() {

    }

    public Roll(String name) {
        this.name = name;
        this.startDate = new Date();
        this.type = Type.MEDIUM;
    }

    public Roll(String name, Type type) {
        this.name = name;
        this.type = type;
    }

    public Roll(String name, long startDate, Type type) {
        this.name = name;
        this.startDate = new Date(startDate);
        this.type = type;
    }

    public Roll(String name, Date startDate, Type type) {
        this.name = name;
        this.startDate = startDate;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

}
