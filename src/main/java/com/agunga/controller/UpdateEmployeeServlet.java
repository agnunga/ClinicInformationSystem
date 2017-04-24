package com.agunga.controller;

import com.agunga.beanI.EmployeeBeanI;
import com.agunga.model.Employee;
import com.agunga.util.MyUtility;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/admin/update_employee")
public class UpdateEmployeeServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    @EJB
    EmployeeBeanI employeeBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setAttribute("employeeBean", employeeBean);

        if (request.getParameter("id") != null) {
            RequestDispatcher rd = request.getRequestDispatcher("/users/admin/updateEmployee.jsp");
            rd.forward(request, response);
        } else {
            request.setAttribute("updated", "Invalid Option. No record selected for update");
            RequestDispatcher rd = request.getRequestDispatcher("/users/admin/viewEmployees.jsp");
            rd.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("employeeBean", employeeBean);

        if (employeeBean == null) {
            PrintWriter out = response.getWriter();
            out.print(": U_Null");
        } else {
            Employee emp = employeeBean.viewEmployee(MyUtility.myParseLong(request.getParameter("id")));

            emp.setName(request.getParameter("name"));
            emp.setNationalId(request.getParameter("nationalId"));
            emp.setPhone(request.getParameter("phone_no"));
            emp.setSex(request.getParameter("sex"));
            emp.setDob(request.getParameter("dob"));
            emp.setEmployeeNo(request.getParameter("employeeNo"));
            emp.setDateEmployed(request.getParameter("dateEmployed"));
            emp.setSalary(request.getParameter("salary"));
            emp.setTitle(request.getParameter("title"));
            emp.setPassword(request.getParameter("nationalId"));
            char t = emp.getTitle().charAt(0);

            if (employeeBean.update(emp) != null) {
            request.setAttribute("updated", emp.getName()+"("+emp.getNationalId()+") updated successfully.");
                RequestDispatcher rd = request.getRequestDispatcher("/users/admin/viewEmployees.jsp");
                rd.forward(request, response);
            } else {
                request.setAttribute("updated", "Invalid Option. Update failed");
                RequestDispatcher rd = request.getRequestDispatcher("/users/admin/updateEmployee.jsp");
                rd.forward(request, response);
            }
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

}
