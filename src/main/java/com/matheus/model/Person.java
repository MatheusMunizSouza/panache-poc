package com.matheus.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;

@Entity
public class Person extends PanacheEntity {

    public String name;

    public static Person findByName(String name){
        return find("name", name).firstResult();
    }

}
