package com.github.maximovj;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/")
public class ApiResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String apiTest() {
        return "Quarkus + Java v17 | API Payments v1.0";
    }
}
