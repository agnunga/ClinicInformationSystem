package com.agunga.controller;

import com.agunga.beanI.NurseBeanI;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.agunga.model.Patient;
import com.agunga.util.MyUtility;
import java.sql.Time;
import java.time.LocalDateTime;
import java.time.LocalTime;
import javax.ejb.EJB;

@WebServlet("/nurse/dispatch")
public class DispatchServlet extends HttpServlet {
    
    private static final long serialVersionUID = 1L;
    @EJB
    NurseBeanI nbi;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("nbi", nbi);
        if (request.getParameter("id") != null) {
            RequestDispatcher rd = request.getRequestDispatcher("/users/nurse/dispatchDrugs.jsp");
            rd.forward(request, response);
        } else {
            request.setAttribute("updated", "");
            RequestDispatcher rd = request.getRequestDispatcher("/users/nurse/viewPatients.jsp");
            rd.forward(request, response);
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        Patient patient = nbi.viewPatient(MyUtility.myParseLong(request.getParameter("id")));
        
        patient.setDrugs(request.getParameter("dispatch"));
        patient.setCheckout(LocalDateTime.now());
        request.setAttribute("nbi", nbi);
        if (nbi.dispatchDrugs(patient) != null) {
            request.setAttribute("updated", "Drugs dispatched to "
                    + patient.getName() + " (" + patient.getPatientId() + ") and successfully recorded.");
            RequestDispatcher rd = request.getRequestDispatcher("/users/nurse/viewPatients.jsp");
            rd.forward(request, response);
        } else {
            request.setAttribute("updated", "Dispatch recording failed.");
            RequestDispatcher rd = request.getRequestDispatcher("/users/nurse/viewPatients.jsp");
            rd.forward(request, response);
        }
    }
}
