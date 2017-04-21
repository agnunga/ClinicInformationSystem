package com.agunga.controller;

import com.agunga.beanI.DoctorBeanI;
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

@WebServlet("/doctor/prescribe")
public class PrescribeServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    @EJB
    DoctorBeanI dbi;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("dbi", dbi);
        if (request.getParameter("id") != null) {
            RequestDispatcher rd = request.getRequestDispatcher("/users/doc/prescribe.jsp");
            rd.forward(request, response);
        } else {
            request.setAttribute("updated", "Invalid Option. No record selected for update");
            RequestDispatcher rd = request.getRequestDispatcher("/users/doc/viewPatients.jsp");
            rd.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Patient patient = dbi.viewPatient(MyUtility.myParseLong(request.getParameter("id")));
        patient.setPrescription(request.getParameter("prescription"));
        request.setAttribute("dbi", dbi);

        if (dbi.prescribe(patient) != null) {
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
