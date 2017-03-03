package com.agunga.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.agunga.cis.Employee;

@WebServlet("/start")
public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        String employeeno = (request.getParameter("employeeNo"));
        String password = (request.getParameter("password"));
//        getServletContext().getRealPath(request.getServletPath());
//        getServletContext().getServerInfo();
        String[] role = Employee.logIn(employeeno, password);
        out.print("Role is: " + role[0]);
        HttpSession session = request.getSession();

        switch (role[0]) {
            case "r": {
                session.setAttribute("rsession", role[1]);
                response.sendRedirect("receptionist");
            }
            case "d": {
                session.setAttribute("dsession", role[1]);
                RequestDispatcher dispatcher = request.getRequestDispatcher("doctor.jsp");
                dispatcher.forward(request, response);
            }
            case "n": {
                session.setAttribute("nsession", role[1]);
                RequestDispatcher dispatcher = request.getRequestDispatcher("nurse.jsp");
                dispatcher.forward(request, response);
            }
            case "l": {
                session.setAttribute("lsession", role[1]);
                RequestDispatcher dispatcher = request.getRequestDispatcher("labtech.jsp");
                dispatcher.forward(request, response);
            }
            case "a": {
                session.setAttribute("asession", role[1]);
//                RequestDispatcher dispatcher = request.getRequestDispatcher("admin.jsp");
//                dispatcher.forward(request, response);
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
