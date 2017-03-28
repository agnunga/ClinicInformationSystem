package com.agunga.controllers;

import com.agunga.beans.Patient;
import com.agunga.beans.Receptionist;
import com.agunga.dao.ConnectionType;
import com.agunga.dao.MyConectivity;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import javax.inject.Inject;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/receptionist/register_patient")
public class RegisterPatientServlet extends HttpServlet {

    @Inject
    @ConnectionType(ConnectionType.Type.MYSQL)
    MyConectivity mcon;

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("/users/rec/registerPatient.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection conn = mcon.connectDB();
        PrintWriter out = response.getWriter();
        Patient patient = new Patient();
        patient.setName(request.getParameter("name"));
        patient.setNationalId(request.getParameter("nationalId"));
        patient.setDob(request.getParameter("dob"));
        patient.setPhone(request.getParameter("patient_phone"));
        patient.setSex(request.getParameter("sex"));

        patient.setPatientId(request.getParameter("patientId"));

        if (new Receptionist().registerPatient(patient, conn)) {
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
