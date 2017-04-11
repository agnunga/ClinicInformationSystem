package com.agunga.controllers;
 
import com.agunga.beansI.ReceptionistBeanI;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.agunga.models.Patient;
import javax.ejb.EJB;

@WebServlet("/receptionist/update_patient")
public class UpdatePatientServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    @EJB
    ReceptionistBeanI receptionistBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("id") != null) {
            RequestDispatcher rd = request.getRequestDispatcher("/users/rec/updatePatient.jsp");
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
        patient.setName(request.getParameter("name"));
        patient.setNationalId(request.getParameter("nationalId"));
        patient.setDob(request.getParameter("dob"));
        patient.setPhone(request.getParameter("patient_phone"));
        patient.setSex(request.getParameter("sex"));

        patient.setPatientId(request.getParameter("patientId"));

        if (receptionistBean.updatePatient(patient)) {
            response.sendRedirect("view_patients?id=" + patient.getNationalId() + "&us=0");
            request.setAttribute("updated", patient.getName() + " (" + patient.getNationalId() + ") successfully updated.");
            RequestDispatcher rd = request.getRequestDispatcher("/users/rec/viewPatients.jsp");
            rd.forward(request, response);
        } else {
            response.sendRedirect("/receptionist/update_patient?id=" + patient.getNationalId() + "&uf=1");
        }
    }
}
