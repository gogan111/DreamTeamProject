package dreamTeam.front_controller;


import dreamTeam.DAO.UserDAOImpl;
import dreamTeam.domain.User;
import dreamTeam.service.UserServiceImpl;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;


/**
 * {ip_address:port}/rest/persons
 * Post - adding new user
 * Get - receiving a user or users
 * Delete - deleting a user
 * Put - changing a user
 */

@WebServlet("/rest/persons")
public class MainController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp) throws ServletException, IOException {

        List<User> userList = new UserServiceImpl(new UserDAOImpl()).getAllUsers();
        String str = new JSONArray(userList).toString();
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        out.print(str);
        out.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req,
                          HttpServletResponse resp) throws ServletException, IOException {

        User user = new User();
        JSONObject jObj = new JSONObject(getBody(req));

        user.setName((String) jObj.get("name"));
        user.setSurname((String) jObj.get("surname"));
        user.setAge((String) jObj.get("age"));
        user.setEmail((String) jObj.get("email"));

       int id = new UserServiceImpl(new UserDAOImpl()).createUser(user);
        System.out.println(id);
       user.setId(String.valueOf(id));
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
       resp.setStatus(HttpServletResponse.SC_OK);


    }

    @Override
    protected void doPut(HttpServletRequest req,
                         HttpServletResponse resp) throws ServletException, IOException {
        User user = new User();
        JSONObject jObj = new JSONObject(getBody(req));
        user.setId(jObj.getString("id"));
        user.setName((String) jObj.get("name"));
        user.setSurname((String) jObj.get("surname"));
        user.setAge((String) jObj.get("age"));
        user.setEmail((String) jObj.get("email"));
        boolean updateUserField = new UserServiceImpl(new UserDAOImpl()).updateUser(user);

        if (updateUserField) {
            String str = new JSONObject(user).toString();
            PrintWriter out = resp.getWriter();
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            resp.setStatus(HttpServletResponse.SC_OK);
            out.print(str);
            out.flush();
        } else {
            resp.setStatus(HttpServletResponse.SC_NOT_MODIFIED);;
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req,
                            HttpServletResponse resp) throws ServletException, IOException {
        JSONObject jObj = new JSONObject(getBody(req));
       int id = Integer.parseInt(jObj.getString("id"));
        boolean delete = new UserServiceImpl(new UserDAOImpl()).deleteUser(id);
        if (delete) {
             resp.setStatus(HttpServletResponse.SC_OK);
        } else {
            resp.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
        }
    }

//    @GET
//    @Produces(MediaType.APPLICATION_JSON)
//    public List<User> getAllUsers() {
//        return new UserServiceImpl(new UserDAOImpl()).getAllUsers();
//    }
//
//    @POST
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response addUser(User user) throws URISyntaxException {
//        UserValidation userValidation = new UserValidation();
//        if (userValidation.validation(user)>0){
//            return Response.status(Response.Status.BAD_REQUEST).entity(userValidation).build();
//        }
//        int id = new UserServiceImpl(new UserDAOImpl()).createUser(user);
//        user.setId(String.valueOf(id));
//        return Response.ok(user, MediaType.APPLICATION_JSON).build();
//    }
//
//    @GET
//    @Path("{id}")
//    public Response getUser(@PathParam("id") int id) {
//        User user = new UserServiceImpl(new UserDAOImpl()).getUser(id);
//        if (Integer.parseInt(user.getId()) != 0) {
//            return Response.ok(user, MediaType.APPLICATION_JSON).build();
//        } else {
//            return Response.status(Response.Status.NOT_FOUND).build();
//        }
//    }
//
//    @PUT
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Path("{id}")
//    public Response updateUser(@PathParam("id") int id, User user) {
//        boolean updateUserField = new UserServiceImpl(new UserDAOImpl()).updateUser(user);
//        if (updateUserField) {
//
//            return Response.ok(user).build();
//        } else {
//
//            return Response.notModified().build();
//        }
//    }
//
//    @DELETE
//    @Path("{id}")
//    public Response deletePerson(@PathParam("id") int id) {
//        boolean delete = new UserServiceImpl(new UserDAOImpl()).deleteUser(id);
//        if (delete) {
//            return Response.ok().build();
//        } else {
//            return Response.notModified().build();
//        }
//    }
public String getBody(HttpServletRequest request) {

    String body = null;
    StringBuilder stringBuilder = new StringBuilder();
    BufferedReader bufferedReader = null;

    try {
        InputStream inputStream = request.getInputStream();
        if (inputStream != null) {
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            char[] charBuffer = new char[128];
            int bytesRead = -1;
            while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                stringBuilder.append(charBuffer, 0, bytesRead);
            }

            return stringBuilder.toString();
        } else {
            stringBuilder.append("");
        }
    } catch (IOException ex) {
        // throw ex;
        return "";
    } finally {
        if (bufferedReader != null) {
            try {
                bufferedReader.close();
            } catch (IOException ex) {

            }
        }
    }
    return null;
}

}
