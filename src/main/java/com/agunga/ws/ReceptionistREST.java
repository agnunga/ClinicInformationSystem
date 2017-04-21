/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agunga.ws;

import com.agunga.bean.ReceptionistBean;
import com.agunga.model.Patient; 
import com.agunga.model.ResponseObject;
import com.agunga.util.MyUtility;
import java.util.List;
import javax.ejb.EJB;
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
    @Path("/add_patient/{param}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addPatient(Patient patient) {
        ResponseObject responseObject = new ResponseObject();
        return Response.status(200).entity(responseObject).build();
    }

    @GET
    @Path("/view/{param}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response viewPatientsDetails(@PathParam("param") String id) {
        return Response.status(200).entity(receptionistBean.viewPatient(MyUtility.myParseLong(id))).build();
    }

    @GET
    @Path("/view_all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response viewPatientsDetails() {
        List<Patient> patients = receptionistBean.viewPatients();
        return Response.status(200).entity(patients).build();
    }

    @POST
    @Path("/update/{param}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updatePatintDetails(@PathParam("param") String id) {
        Patient p = new Patient();
        if (receptionistBean.updatePatient(p) != null) {
            return Response.status(200).entity("patient updated").build();
        } else {
            return Response.status(200).entity("Update failed").build();
        }
    }

    @DELETE
    @Path("/delete/{param}")
    public Response deletePatientDetails(@PathParam("param") String id) {
        if (receptionistBean.deletePatient(MyUtility.myParseLong(id))) {
            return Response.status(200).entity("Patient deleted").build();
        } else {
            return Response.status(200).entity("Failed to delete").build();
        }
    }

}
