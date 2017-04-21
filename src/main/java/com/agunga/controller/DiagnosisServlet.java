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
import com.agunga.util.MyUtility;
import javax.ejb.EJB;

@WebServlet("/doctor/diagnose")
public class DiagnosisServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    @EJB
    ReceptionistBeanI rbi;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("rbi", rbi);
        if (request.getParameter("id") != null) {
            RequestDispatcher rd = request.getRequestDispatcher("/users/doc/diagnose.jsp");
            rd.forward(request, response);
        } else {
            request.setAttribute("updated", "Invalid Option. No record selected for update");
            RequestDispatcher rd = request.getRequestDispatcher("/users/doc/viewPatients.jsp");
            rd.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 
        Patient patient = rbi.viewPatient(MyUtility.myParseLong(request.getParameter("id")));
          
        patient.setPatientId(request.getParameter("diagnose"));

        if (rbi.updatePatient(patient) != null) {
            request.setAttribute("updated", "Updated successfull.");
            RequestDispatcher rd = request.getRequestDispatcher("/users/doc/viewPatients.jsp");
            rd.forward(request, response);
        } else {
            request.setAttribute("updated", "Update failed.");
            RequestDispatcher rd = request.getRequestDispatcher("/users/doc/viewPatients.jsp");
            rd.forward(request, response);
        }
    }
}
