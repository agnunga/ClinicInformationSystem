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
        Patient p = receptionistBean.addPatient(patient);

        if (p != null) {
            responseObject.setStatus(true);
            responseObject.setMessage("Success - added");
            responseObject.setData(p);
        } else {
            responseObject.setStatus(false);
            responseObject.setMessage("Failed - not added");
            responseObject.setData(patient);
        }
        return Response.status(200).entity(responseObject).build();
    }

    @GET
    @Path("/view/{param}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response viewPatientsDetails(@PathParam("param") String id) {
        ResponseObject responseObject = new ResponseObject();
        Patient patient = receptionistBean.viewPatient(MyUtility.myParseLong(id));

        if (patient != null) {
            responseObject.setStatus(true);
            responseObject.setMessage("Success - retrieved");
            responseObject.setData(patient);
        } else {
            responseObject.setStatus(false);
            responseObject.setMessage("Failed - not retrieved");
            responseObject.setData(patient);
        }
        return Response.status(200).entity(responseObject).build();
    }

    @GET
    @Path("/view_all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response viewPatientsDetails() {
        ResponseObject responseObject = new ResponseObject();
        List<Patient> patients = receptionistBean.viewPatients();

        if (patients != null) {
            responseObject.setStatus(true);
            responseObject.setMessage("Success - retrieved");
            responseObject.setData(patients);
        } else {
            responseObject.setStatus(false);
            responseObject.setMessage("Failed - not retrieved");
            responseObject.setData(patients);
        }
        return Response.status(200).entity(responseObject).build();
    }

    @POST
    @Path("/update/{param}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updatePatintDetails(@PathParam("param") String id) {
        ResponseObject responseObject = new ResponseObject();
        Patient patient = receptionistBean.viewPatient(MyUtility.myParseLong(id));
        Patient p = receptionistBean.updatePatient(patient);

        if (p != null) {
            responseObject.setStatus(true);
            responseObject.setMessage("Success - updated");
            responseObject.setData(p);
        } else {
            responseObject.setStatus(false);
            responseObject.setMessage("Failed - not updated");
            responseObject.setData(p);
        }
        return Response.status(200).entity(responseObject).build();
    }

    @DELETE
    @Path("/delete/{param}")
    public Response deletePatientDetails(@PathParam("param") String id) {
        ResponseObject responseObject = new ResponseObject();
        boolean isDeleted = receptionistBean.deletePatient(MyUtility.myParseLong(id));

        if (isDeleted) {
            responseObject.setStatus(true);
            responseObject.setMessage("Success - deleted");
            responseObject.setData(isDeleted);
        } else {
            responseObject.setStatus(false);
            responseObject.setMessage("Failed - not deleted");
            responseObject.setData(isDeleted);
        }
        return Response.status(200).entity(responseObject).build();
    }

}
