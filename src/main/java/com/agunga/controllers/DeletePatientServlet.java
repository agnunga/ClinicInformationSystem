package com.agunga.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.agunga.beans.Patient;
import com.agunga.beans.Receptionist;
import com.agunga.dao.ConnectionType;
import com.agunga.dao.MyConectivity;
import java.sql.Connection;
import javax.inject.Inject;

@WebServlet("/receptionist/delete_patient")
public class DeletePatientServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Inject
    @ConnectionType(ConnectionType.Type.MYSQL)
    MyConectivity mcon;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection conn = mcon.connectDB();
        request.setAttribute("mycon", conn);
        if (request.getParameter("id") != null) {
            RequestDispatcher rd = request.getRequestDispatcher("/users/rec/viewPatients.jsp");
            rd.forward(request, response);
        } else {
            request.setAttribute("updated", "Invalid Option. No record selected for update");
            RequestDispatcher rd = request.getRequestDispatcher("/users/rec/viewPatients.jsp");
            rd.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection conn = mcon.connectDB();
        request.setAttribute("mycon", conn);

        Patient patient = new Patient();
        int id = Integer.parseInt(request.getParameter("patientId"));

        if (new Receptionist().deletePatientDetails(patient, id, conn)) {
            response.sendRedirect("view_patients?id=" + patient.getNationalId() + "&us=0");
            request.setAttribute("deleted", patient.getName() + " (" + patient.getNationalId() + ") successfully updated.");
            RequestDispatcher rd = request.getRequestDispatcher("/users/rec/viewPatients.jsp");
            rd.forward(request, response);
        } else {
            response.sendRedirect("/receptionist/update_patient?id=" + patient.getNationalId() + "&uf=1");
        }
    }
}
