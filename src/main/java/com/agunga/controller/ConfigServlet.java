package com.agunga.controller;
 
import com.agunga.db.ConnectionType;
import com.agunga.db.MyConectivity;
import com.trial.trials.ManageEmployee;
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
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@WebServlet("/config")
public class ConfigServlet extends HttpServlet {

//    SessionFactory factory = HibernateUtils.getSessionFactory();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//        ManageEmployee me = new ManageEmployee(factory);
//        me.addEmployee();

        RequestDispatcher dispatcher = request.getRequestDispatcher("config.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();

    }
}
