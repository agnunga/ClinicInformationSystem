package com.agunga.controllers;

import com.agunga.beans.Administrator;
import com.agunga.dao.ConnectionType;
import com.agunga.dao.MyConectivity;
import java.io.IOException;
import java.sql.Connection;
import javax.inject.Inject;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("admin/register_employee")
public class RegisterEmployeeServlet extends HttpServlet {

    @Inject
    @ConnectionType(ConnectionType.Type.MYSQL)
    MyConectivity mcon;

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Connection conn = mcon.connectDB();
        request.setAttribute("mycon", conn);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/users/admin/registerEmployee.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Connection conn = mcon.connectDB();
        request.setAttribute("mycon", conn);

        Administrator emp = new Administrator();

        emp.setName(request.getParameter("name"));
        emp.setNationalId(request.getParameter("nationalId"));
        emp.setPhone(request.getParameter("phone_no"));
        emp.setSex(request.getParameter("sex"));
        emp.setDob(request.getParameter("dob"));
        emp.setEmployeeNo(request.getParameter("employeeNo"));
        emp.setDateEmployed(request.getParameter("dateEmployed"));
        emp.setSalary(request.getParameter("salary"));
        emp.setTitle(request.getParameter("title"));
        char t = emp.getTitle().charAt(0);

        if (emp.registerEmployee(emp, conn)) {
            switch (t) {
                case 'd': {
                    RequestDispatcher rd = request.getRequestDispatcher("/users/admin/registerEmployee.jsp");
                    request.setAttribute("message", "Success! Doctor registered");
                    rd.forward(request, response);
//                    response.sendRedirect("/ClinicInformationSystem/admin/register_employee?rs=1&e=d");
                    break;
                }
                default: {
                    RequestDispatcher rd = request.getRequestDispatcher("/users/admin/registerEmployee.jsp");
                    request.setAttribute("message", "Success! Employee registered.");
                    rd.forward(request, response);
//                    response.sendRedirect("/ClinicInformationSystem/admin/register_employee?rs=1");
                }
            }
        } else {
            RequestDispatcher rd = request.getRequestDispatcher("/users/admin/registerEmployee.jsp");
            request.setAttribute("message", "Failed! Employee not registered.");
            rd.forward(request, response);
//            response.sendRedirect("/ClinicInformationSystem/register_employee?rf=1");
        }
    }

}
