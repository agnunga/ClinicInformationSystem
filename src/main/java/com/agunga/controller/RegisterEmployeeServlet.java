package com.agunga.controller;

import com.agunga.beanI.AdministratorBeanI;
import com.agunga.model.Employee;
import com.agunga.util.MyUtility;
import java.io.IOException;
import javax.ejb.EJB;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("admin/register_employee")
public class RegisterEmployeeServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @EJB
    AdministratorBeanI administratorBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/users/admin/registerEmployee.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        Employee emp = new Employee();

        emp.setName(request.getParameter("name"));
        emp.setNationalId(request.getParameter("nationalId"));
        emp.setPhone(request.getParameter("phone_no"));
        emp.setSex(request.getParameter("sex"));
        emp.setDob(request.getParameter("dob"));
        emp.setEmployeeNo(request.getParameter("employeeNo"));
        emp.setDateEmployed(request.getParameter("dateEmployed"));
        emp.setSalary(request.getParameter("salary"));
        emp.setTitle(request.getParameter("title"));
        emp.setAddedBy(session.getAttribute("asession").toString());
        //Encrypting password
        emp.setPassword(MyUtility.encryptPassword(request.getParameter("nationalId")));
        char t = emp.getTitle().charAt(0);

        if (administratorBean.addEmployee(emp) != null) {
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
