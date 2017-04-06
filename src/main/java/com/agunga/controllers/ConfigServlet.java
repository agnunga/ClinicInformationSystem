package com.agunga.controllers;

import com.agunga.dao.ConnectionType;
import com.agunga.dao.MyConectivity;
import java.io.IOException;
import java.io.PrintWriter;
import javax.inject.Inject;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/config")
public class ConfigServlet extends HttpServlet {

    @Inject
    @ConnectionType(ConnectionType.Type.MYSQL)
    MyConectivity mconM;

    @Inject
    @ConnectionType(ConnectionType.Type.ORACLE)
    MyConectivity mconO;
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("config.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String db = (request.getParameter("db"));

        ServletContext servletContext = getServletContext();

        if ("o".equals(request.getParameter("db"))) {
            servletContext.setAttribute("appCon", mconO);
        } else if ("m".equals(request.getParameter("db"))) {
            //ServletContect
            servletContext.setAttribute("appCon", mconM);
        }
    }
}
