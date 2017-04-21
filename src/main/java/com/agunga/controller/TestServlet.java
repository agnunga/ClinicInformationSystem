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

@WebServlet("/lab/test")
public class TestServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    @EJB
    ReceptionistBeanI rbi;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("rbi", rbi);
        if (request.getParameter("id") != null) {
            RequestDispatcher rd = request.getRequestDispatcher("/users/lab/test.jsp");
            rd.forward(request, response);
        } else {
            request.setAttribute("updated", "Invalid Option. No record selected for update");
            RequestDispatcher rd = request.getRequestDispatcher("/users/lab/viewPatients.jsp");
            rd.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 
        Patient patient = rbi.viewPatient(MyUtility.myParseLong(request.getParameter("id")));
        
        patient.setName(request.getParameter("name"));
        patient.setNationalId(request.getParameter("nationalId"));
        patient.setDob(request.getParameter("dob"));
        patient.setPhone(request.getParameter("patient_phone"));
        patient.setSex(request.getParameter("sex"));
        patient.setPatientId(request.getParameter("patientId"));

        if (rbi.updatePatient(patient) != null) {
            request.setAttribute("updated", "Updated successfull.");
            RequestDispatcher rd = request.getRequestDispatcher("/users/lab/viewPatients.jsp");
            rd.forward(request, response);
        } else {
            request.setAttribute("updated", "Update failed.");
            RequestDispatcher rd = request.getRequestDispatcher("/users/lab/viewPatients.jsp");
            rd.forward(request, response);
        }
    }
}
