package com.agunga.controllers;
 
import com.agunga.beansI.EmployeeBeanI;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/start")
public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @EJB
    EmployeeBeanI eb;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        
        PrintWriter out = response.getWriter();
        String employeeno = (request.getParameter("employeeNo"));
        String password = (request.getParameter("password"));
        
        String[] role = eb.logIn(employeeno, password);
        out.print("Role is: " + role[0]);
        HttpSession session = request.getSession();

        switch (role[0]) {
            case "r": {
                session.setAttribute("rsession", role[1]);
                response.sendRedirect("receptionist");
            }
            case "d": {
                session.setAttribute("dsession", role[1]);
                response.sendRedirect("doctor");
            }
            case "n": {
                session.setAttribute("nsession", role[1]);
                response.sendRedirect("nurse");
            }
            case "l": {
                session.setAttribute("lsession", role[1]);
                response.sendRedirect("labtech");
            }
            case "a": {
                session.setAttribute("asession", role[1]);
                response.sendRedirect("admin");
            }
            default: {
                request.setAttribute("login_error", "Invalid Credentials. Try again");
                RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
                dispatcher.forward(request, response);
            }
        }
    }

}
