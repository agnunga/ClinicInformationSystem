package com.agunga.controllers;

import com.agunga.dao.ConnectionType;
import com.agunga.dao.MyConectivity;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/receptionist/view_patients")
public class viewPatientsServlet extends HttpServlet {
@Inject
@ConnectionType(ConnectionType.Type.MYSQL)
MyConectivity mcon;
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        Connection conn = mcon.connectDB();
        request.setAttribute("mycon", conn);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/users/rec/viewPatients.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }


}
