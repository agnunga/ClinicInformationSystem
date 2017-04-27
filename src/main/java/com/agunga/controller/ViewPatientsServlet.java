package com.agunga.controller;

import com.agunga.beanI.ReceptionistBeanI;
import com.agunga.model.Patient;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/receptionist/view_patients")
public class ViewPatientsServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @EJB
    ReceptionistBeanI rbi;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Patient> patients = rbi.viewPatients();
        request.setAttribute("patients", patients);

        if (patients != null && patients.size() > 0) {
            request.setAttribute("message", "All patients");
            RequestDispatcher rd = request.getRequestDispatcher("/users/rec/viewPatients.jsp");
            rd.forward(request, response);
        } else {
            request.setAttribute("message", "No patients found.");
            RequestDispatcher rd = request.getRequestDispatcher("/users/rec/viewPatients.jsp");
            rd.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Patient> patients = rbi.viewByPatientId(request.getParameter("patientId"));
        request.setAttribute("patients", patients);

        if (patients != null && patients.size() > 0) {
            request.setAttribute("message", "Search results for " + request.getParameter("patientId"));
            RequestDispatcher rd = request.getRequestDispatcher("/users/rec/viewPatients.jsp");
            rd.forward(request, response);
        } else {
            request.setAttribute("message", "No record found for " + request.getParameter("patientId"));
            RequestDispatcher rd = request.getRequestDispatcher("/users/rec/viewPatients.jsp");
            rd.forward(request, response);
        }
    }

}
