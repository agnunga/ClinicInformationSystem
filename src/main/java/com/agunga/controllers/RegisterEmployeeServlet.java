package com.agunga.controllers;

import com.agunga.beans.Administrator;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/register_employee")
public class RegisterEmployeeServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/users/admin/registerEmployee.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

        if (emp.registerEmployee(emp)) {
            switch (t) {
                case 'd': {
                    // something goes here to complete the registration as a doctor
                    response.sendRedirect("ClinicInformationSystem/register_employee?rs=1");
                    break;
                }
                default: {
                    response.sendRedirect("ClinicInformationSystem/register_employee?rs=1");
                }
            }
        } else {
            response.sendRedirect("/ClinicInformationSystem/register_employee?rf=1");
        }
    }

}
