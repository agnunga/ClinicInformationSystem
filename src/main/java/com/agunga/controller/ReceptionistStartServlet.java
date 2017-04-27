/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agunga.controller;

import com.agunga.beanI.ReceptionistBeanI;
import com.agunga.model.Patient;
import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "ReceptionistStartServlet", urlPatterns = {"/receptionist"})
public class ReceptionistStartServlet extends HttpServlet {

    @EJB
    ReceptionistBeanI rbi;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null && !session.isNew() && session.getAttribute("rsession") != null) {
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
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
            dispatcher.forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Receptionist landing servlet";
    }

}
