package com.agunga.controllers;

import com.agunga.beansI.ReceptionistBeanI;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/receptionist/view_patients")
public class ViewPatientsServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @EJB
    ReceptionistBeanI rbi;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("rbi", rbi);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/users/rec/viewPatients.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

}
