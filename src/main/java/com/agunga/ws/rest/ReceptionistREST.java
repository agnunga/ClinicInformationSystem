/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agunga.ws.rest;

import com.agunga.beans.ReceptionistBean;
import com.agunga.models.Patient; 
import java.util.ArrayList;
import javax.ejb.EJB;
import javax.ejb.Stateless;
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

/**
 *
 * @author agunga
 */ 
@Path("/receptionist")
public class ReceptionistREST {

    @EJB
    ReceptionistBean receptionistBean;

    @GET
    @Path("/view_patient/{param}")
    @Produces(MediaType.APPLICATION_JSON)
    public Patient viewSinglePatientDetails(@PathParam("param") String id) {
        return receptionistBean.viewPatient(id).get(0);
    }

    @POST
    @Path("/register_patient/{param}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response registerPatient(Patient patient) {
        if (receptionistBean.addPatient(patient)) {
            return Response.status(200).entity("Patient Registered. ").build();
        } else {
            return Response.status(200).entity("Patient registration failed").build();
        }
    }

    @GET
    @Path("/view/{param}")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Patient> viewPatientsDetails(@PathParam("param") String id) {
        ArrayList<Patient> patients = receptionistBean.viewPatient(id);
        return patients;
    }

    @PUT
    @Path("/update/{param}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updatePatintDetails(Patient patient) {
        if (receptionistBean.updatePatient(patient)) {
            return Response.status(200).entity("patient updated").build();
        } else {
            return Response.status(200).entity("Update failed").build();
        }
    }

    @DELETE
    @Path("/delete/{param}")
    public Response deletePatientDetails(String id) {
        if (receptionistBean.deletePatient(id)) {
            return Response.status(200).entity("Patient deleted").build();
        } else {
            return Response.status(200).entity("Failed to delete").build();
        }
    }

}
