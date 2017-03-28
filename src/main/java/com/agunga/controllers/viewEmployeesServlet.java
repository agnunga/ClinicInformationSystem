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

@WebServlet("/admin/view_employees")
public class viewEmployeesServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Inject
    @ConnectionType(ConnectionType.Type.MYSQL)
    MyConectivity mcon;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection conn = mcon.connectDB();
        request.setAttribute("mycon", conn);
        PrintWriter out = response.getWriter();
        RequestDispatcher dispatcher = request.getRequestDispatcher("/users/admin/viewEmployees.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }

}
