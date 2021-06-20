package dreamteam.front_controller;


import dreamteam.domain.User;
import dreamteam.service.UserServiceImpl;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

/**
 * {ip_address:port}/rest/persons
 */

@Path("/persons")
public class MainController {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> getAllUsers() {
        return new UserServiceImpl().getAllUsers();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    // Which MIME type is received from client;
    public Response addUser(User user) throws URISyntaxException {

        int id = new UserServiceImpl().createUser(user);
        System.out.println(id);
        URI uri = new URI("/rest/persons/" + id);

        return Response.created(uri).build();
    }


    @GET
    @Path("{id}")
    public Response getUser(@PathParam("id") int id) {

        User user = new UserServiceImpl().getUser(id);

        if (user.getId() != 0) {
            return Response.ok(user, MediaType.APPLICATION_JSON).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response updateUser(@PathParam("id") int id, User user) {
        boolean updateUserField = new UserServiceImpl().updateUser(user);
        if (updateUserField) {
            return Response.ok().build();
        } else {
            return Response.notModified().build();
        }
    }

    @DELETE
    @Path("{id}")
    public Response deletePerson(@PathParam("id") int id) {

        boolean delete = new UserServiceImpl().deleteUser(id);
        if (delete) {
            return Response.ok().build();
        } else {
            return Response.notModified().build();
        }
    }


}
