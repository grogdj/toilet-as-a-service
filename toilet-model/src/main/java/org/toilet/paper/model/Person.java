/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.toilet.paper.model;

/**
 *
 * @author salaboy
 */
public class Person {

    public enum Gender {
        MALE, FEMALE
    };
    private String id;
    private int age;
    private Gender gender;

    public Person(String id, int age, Gender gender) {
        this.id = id;
        this.age = age;
        this.gender = gender;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

}
