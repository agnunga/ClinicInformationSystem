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
import java.util.List;
import javax.ejb.EJB;

@WebServlet("/receptionist/delete_patient")
public class DeletePatientServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @EJB
    ReceptionistBeanI rbi;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Patient> patients = rbi.viewPatients();
        request.setAttribute("patients", patients);

        if (request.getParameter("id") != null) {
            if (rbi.deletePatient(MyUtility.myParseLong(request.getParameter("id")))) {
                request.setAttribute("deleted", "Deleted successfully");
                RequestDispatcher rd = request.getRequestDispatcher("/users/rec/viewPatients.jsp");
                rd.forward(request, response);
            }
        } else {
            request.setAttribute("deleted", "Deletion failed");
            RequestDispatcher rd = request.getRequestDispatcher("/users/rec/viewPatients.jsp");
            rd.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
