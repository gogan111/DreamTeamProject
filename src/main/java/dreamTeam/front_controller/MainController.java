package dreamTeam.front_controller;


import dreamTeam.DAO.UserDAOImpl;
import dreamTeam.domain.User;
import dreamTeam.service.UserServiceImpl;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

/**
 * {ip_address:port}/rest/persons
 * Post - adding new user
 * Get - receiving a user or users
 * Delete - deleting a user
 * Put - changing a user
 */

@Path("/persons")
public class MainController {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> getAllUsers() {
        return new UserServiceImpl(new UserDAOImpl()).getAllUsers();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addUser(User user) throws URISyntaxException {
        int id = new UserServiceImpl(new UserDAOImpl()).createUser(user);
        URI uri = new URI("/rest/persons/" + id);

        return Response.created(uri).build();
    }


    @GET
    @Path("{id}")
    public Response getUser(@PathParam("id") int id) {
        User user = new UserServiceImpl(new UserDAOImpl()).getUser(id);

        if (Integer.parseInt(user.getId()) != 0) {

            return Response.ok(user, MediaType.APPLICATION_JSON).build();
        } else {

            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response updateUser(@PathParam("id") int id, User user) {
        boolean updateUserField = new UserServiceImpl(new UserDAOImpl()).updateUser(user);
        if (updateUserField) {

            return Response.ok().build();
        } else {

            return Response.notModified().build();
        }
    }

    @DELETE
    @Path("{id}")
    public Response deletePerson(@PathParam("id") int id) {
        boolean delete = new UserServiceImpl(new UserDAOImpl()).deleteUser(id);
        if (delete) {

            return Response.ok().build();
        } else {

            return Response.notModified().build();
        }
    }


}