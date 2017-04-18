package com.agunga.controller;
 
import com.agunga.beanI.ReceptionistBeanI;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.agunga.model.Patient; 
import javax.ejb.EJB; 

@WebServlet("/receptionist/delete_patient")
public class DeletePatientServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @EJB
    ReceptionistBeanI receptionistBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("id") != null) {
            RequestDispatcher rd = request.getRequestDispatcher("/users/rec/viewPatients.jsp");
            rd.forward(request, response);
        } else {
            request.setAttribute("updated", "Invalid Option. No record selected for update");
            RequestDispatcher rd = request.getRequestDispatcher("/users/rec/viewPatients.jsp");
            rd.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
        Patient patient = new Patient();
        String id = request.getParameter("patientId");

        if (receptionistBean.deletePatient(id)) {
            response.sendRedirect("view_patients?id=" + patient.getNationalId() + "&us=0");
            request.setAttribute("deleted", patient.getName() + " (" + patient.getNationalId() + ") successfully updated.");
            RequestDispatcher rd = request.getRequestDispatcher("/users/rec/viewPatients.jsp");
            rd.forward(request, response);
        } else {
            response.sendRedirect("/receptionist/update_patient?id=" + patient.getNationalId() + "&uf=1");
        }
    }
}
