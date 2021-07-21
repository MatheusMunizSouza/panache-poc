package com.matheus.resource;

import com.matheus.model.Fruit;
import com.matheus.repository.FruitRepository;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.stream.Collectors;

@Resource
@Path("/fruits")
public class FruitResource {

    @Inject
    FruitRepository fruitRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        return Response.ok(this.fruitRepository.findAll().stream().collect(Collectors.toList())).build();
    }

    @GET
    @Path("/findByName/{name}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByName(@PathParam("name") String name) {
        return Response.ok(this.fruitRepository.findByName(name)).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response save(Fruit fruit) {
        this.fruitRepository.persist(fruit);
        return Response.ok(fruit).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response edit(Fruit editedFruit) {
        Fruit fruit = this.fruitRepository.findById(editedFruit.id);
        fruit.name = editedFruit.name;
        this.fruitRepository.persist(fruit);
        return Response.ok(fruit).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response delete(@PathParam("id") Long id) {
        this.fruitRepository.findById(id).delete();
        return Response.noContent().build();
    }
}
