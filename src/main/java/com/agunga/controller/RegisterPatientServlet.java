package com.agunga.controller;

import com.agunga.beanI.ReceptionistBeanI;
import com.agunga.model.Patient;
import java.io.IOException;
import java.time.LocalDateTime;
import javax.ejb.EJB;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/receptionist/register_patient")
public class RegisterPatientServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @EJB
    ReceptionistBeanI receptionistBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("/users/rec/registerPatient.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        Patient patient = new Patient();
        patient.setName(request.getParameter("name"));
        patient.setNationalId(request.getParameter("nationalId"));
        patient.setDob(request.getParameter("dob"));
        patient.setPhone(request.getParameter("patient_phone"));
        patient.setSex(request.getParameter("sex"));
        patient.setCheckin(LocalDateTime.now());
        patient.setAddedBy(session.getAttribute("rsession").toString());

        patient.setPatientId(request.getParameter("patientId"));

        if (receptionistBean.addPatient(patient) != null) {
            request.setAttribute("message", "Success! Patient registered, Register another patient.");
            RequestDispatcher rd = request.getRequestDispatcher("/users/rec/registerPatient.jsp");
            rd.forward(request, response);
        } else {
            request.setAttribute("message", "Error! Patient registration failed");

            RequestDispatcher rd = request.getRequestDispatcher("/users/rec/registerPatient.jsp");
            rd.forward(request, response);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

}
