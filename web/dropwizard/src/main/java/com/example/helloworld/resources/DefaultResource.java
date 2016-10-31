package com.example.helloworld.resources;

import io.dropwizard.hibernate.UnitOfWork;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import io.dropwizard.jersey.params.LongParam;
import javax.ws.rs.PUT;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.List;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class DefaultResource {
 
    @GET
    @UnitOfWork
    public String sayHello() {
        return "helloworld";
    }

 
   
}
