package com.agunga.controller;

import com.agunga.beanI.DoctorBeanI;
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
    DoctorBeanI dbi;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("dbi", dbi);
        if (request.getParameter("id") != null) {
            RequestDispatcher rd = request.getRequestDispatcher("/users/doc/diagnose.jsp");
            rd.forward(request, response);
        } else {
            request.setAttribute("updated", "");
            RequestDispatcher rd = request.getRequestDispatcher("/users/doc/viewPatients.jsp");
            rd.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Patient patient = dbi.viewPatient(MyUtility.myParseLong(request.getParameter("id")));
        patient.setDiagnosis(request.getParameter("diagnosis"));
        request.setAttribute("dbi", dbi);

        if (dbi.diagnose(patient) != null) {
            request.setAttribute("updated", "Diagnosis for "
                    + patient.getName() + " (" + patient.getPatientId() + ") recorded successfully.");
            RequestDispatcher rd = request.getRequestDispatcher("/users/doc/viewPatients.jsp");
            rd.forward(request, response);
        } else {
            request.setAttribute("updated", "Diagnosis recording failed.");
            RequestDispatcher rd = request.getRequestDispatcher("/users/doc/viewPatients.jsp");
            rd.forward(request, response);
        }
    }
}
