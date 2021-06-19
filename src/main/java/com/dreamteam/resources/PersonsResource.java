package com.dreamteam.resources;


import com.dreamteam.Entity.Person;
import com.dreamteam.service.PersonServiceImpl;
import com.dreamteam.users.UserImpl1;
import com.dreamteam.users.Users;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Path("/persons")
public class PersonsResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    //Produces - Which type is responsed to client
    public List<Person> getPersons() {
        Users users = new UserImpl1();
        List<Person> people = new PersonServiceImpl().personList(users);
        return people;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    // Which MIME type is received from client;
    public Response addPerson (Person person) throws URISyntaxException {
        Users users = new UserImpl1();
        int id = new PersonServiceImpl().saveOrUpdate(person,users);
        System.out.println(id);
        URI uri = new URI("/persons/"+id);

        return Response.created(uri).build();
    }



}
