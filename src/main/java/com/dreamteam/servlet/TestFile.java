package com.dreamteam.servlet;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("test")
//localhost:8085/rest/test
public class TestFile {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return "Всем привет";
    }
}

