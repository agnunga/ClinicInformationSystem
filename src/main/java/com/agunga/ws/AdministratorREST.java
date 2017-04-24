/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agunga.ws;

import com.agunga.beanI.AdministratorBeanI;
import com.agunga.model.Employee;
import com.agunga.model.ResponseObject;
import com.agunga.util.MyUtility;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/admin")
public class AdministratorREST {

    @EJB
    AdministratorBeanI administratorBean;

    @POST
    @Path("/register_employee")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response registerSpecificEmployee(Employee employee) {
        ResponseObject responseObject = new ResponseObject();
        if (administratorBean.addEmployee(employee) != null) {
            responseObject.setStatus(true);
            responseObject.setMessage("Success - registered");
            responseObject.setData(employee);
        } else {
            responseObject.setStatus(false);
            responseObject.setMessage("Failed - not registered");
            responseObject.setData(employee);
        }
        return Response.status(200).entity(responseObject).build();
    }

    @GET
    @Path("/view_employees")
    @Produces(MediaType.APPLICATION_JSON)
    public Response viewEmployees() {
        ResponseObject responseObject = new ResponseObject();
        List<Employee> employees = administratorBean.viewEmployees();
        if (employees != null) {
            responseObject.setStatus(true);
            responseObject.setMessage("Success - registered");
            responseObject.setData(employees);
        } else {
            responseObject.setStatus(false);
            responseObject.setMessage("Failed - not registered");
            responseObject.setData(employees);
        }
        return Response.status(200).entity(responseObject).build();
    }

    @GET
    @Path("/view_employee/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response viewEmployees(@PathParam("id") String id) {
        ResponseObject responseObject = new ResponseObject();
        Employee employee = administratorBean.viewEmployee(MyUtility.myParseLong(id));
        if (employee != null) {
            responseObject.setStatus(true);
            responseObject.setMessage("Success - registered");
            responseObject.setData(employee);
        } else {
            responseObject.setStatus(false);
            responseObject.setMessage("Failed - not registered");
            responseObject.setData(employee);
        }
        return Response.status(200).entity(responseObject).build();
    }

}
