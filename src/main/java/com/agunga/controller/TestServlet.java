package com.agunga.controller;

import com.agunga.beanI.LabtechBeanI;
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
    LabtechBeanI lbi;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("lbi", lbi);
        if (request.getParameter("id") != null) {
            RequestDispatcher rd = request.getRequestDispatcher("/users/lab/test.jsp");
            rd.forward(request, response);
        } else {
            request.setAttribute("updated", "");
            RequestDispatcher rd = request.getRequestDispatcher("/users/lab/viewPatients.jsp");
            rd.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Patient patient = lbi.viewPatient(MyUtility.myParseLong(request.getParameter("id")));

        patient.setWeight(request.getParameter("weight"));
        patient.setBloodType(request.getParameter("blood"));
        patient.setTestResults(request.getParameter("test"));

        request.setAttribute("lbi", lbi);

        if (lbi.test(patient) != null) {
            request.setAttribute("updated", "Test result for "
                    + patient.getName() + " (" + patient.getPatientId() + ") recorded successfully.");
            RequestDispatcher rd = request.getRequestDispatcher("/users/lab/viewPatients.jsp");
            rd.forward(request, response);
        } else {
            request.setAttribute("updated", "Test result recording failed.");
            RequestDispatcher rd = request.getRequestDispatcher("/users/lab/viewPatients.jsp");
            rd.forward(request, response);
        }
    }
}
