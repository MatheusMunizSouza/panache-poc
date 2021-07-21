package com.matheus.repository;

import com.matheus.model.Fruit;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class FruitRepository implements PanacheRepository<Fruit> {

    public Fruit findByName(String name) {
        return find("name", name).firstResult();
    }
}
