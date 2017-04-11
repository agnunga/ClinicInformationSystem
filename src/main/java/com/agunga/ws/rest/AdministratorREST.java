/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agunga.ws.rest;
 
import com.agunga.beansI.AdministratorBeanI;
import com.agunga.models.Employee;
import com.agunga.models.ResponseObject; 
import java.util.ArrayList;
import javax.ejb.EJB;
import javax.ejb.Stateless; 
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
 
@Path("/admin")
public class AdministratorREST {

    @EJB
    AdministratorBeanI administratorBean;

    @POST
    @Path("/register_employee")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseObject registerSpecificEmployee(Employee employee) {
        ResponseObject responseObject = new ResponseObject();
        if (administratorBean.addEmployee(employee)) {
            responseObject.setStatus(true);
            responseObject.setMessage("Success - registered");
            responseObject.setData(employee);
        } else {
            responseObject.setStatus(false);
            responseObject.setMessage("Failed - not registered");
            responseObject.setData(employee);
        }
        return responseObject;
    }

    @GET
    @Path("/view_employees")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Employee> viewEmployees() {
        ArrayList<Employee> employees = administratorBean.viewEmployees();
        return employees;
    }

    @GET
    @Path("/view_employee/{param}")
    @Produces(MediaType.APPLICATION_JSON)
    public Employee viewEmployees(@PathParam("param") int index) {
        ArrayList<Employee> employees = administratorBean.viewEmployees();
        if (index < employees.size()) {
            return employees.get(index);
        } else {
            return null;
        }
    }

}
