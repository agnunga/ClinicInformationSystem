/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agunga.ws.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

/**
 *
 * @author agunga
 */
@Path("/tryjersey")
public class TryJersey{

    @GET
    @Path("/test/{param}")
    public Response testing(@PathParam("param") String name) {
        return Response.status(200).entity("Hello from Jersey: I am " + name).build();
    }
}
