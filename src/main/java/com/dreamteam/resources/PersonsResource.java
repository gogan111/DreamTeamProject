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
    public Response addPerson(Person person) throws URISyntaxException {
        Users users = new UserImpl1();
        int id = new PersonServiceImpl().saveOrUpdate(person,users);
        System.out.println(id);
        URI uri = new URI("/rest/persons/"+id);

        return Response.created(uri).build();
    }


    @GET
    @Path("{id}")
    public Response getPerson (@PathParam("id") int id){
        Users users = new UserImpl1();
        Person person = new PersonServiceImpl().getPerson(id, users);
        if (person.getId() != 0) {
            return Response.ok(person, MediaType.APPLICATION_JSON).build();
        }
        else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response updatePerson (@PathParam("id") int id, Person person){
        Users users = new UserImpl1();
        int changePerson = new PersonServiceImpl().saveOrUpdate(person, users);
        if (person.getId() != 0) {
            return Response.ok().build();
        }
        else {
            return Response.notModified().build();
        }
    }

    @DELETE
    @Path("{id}")
    public Response deletePerson (@PathParam("id") int id){
        Users users = new UserImpl1();
        boolean delete = new PersonServiceImpl().personDelete(id, users);
        if (delete){
            return Response.ok().build();
        }
        else {
            return Response.notModified().build();
        }
    }


}
