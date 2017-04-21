package com.agunga.controller;

import com.agunga.beanI.EmployeeBeanI;
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

@WebServlet("/admin/delete_employee")
public class DeleteEmployeeServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    @EJB
    EmployeeBeanI employeeBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setAttribute("employeeBean", employeeBean);

        if (request.getParameter("id") != null) {
            if (employeeBean.delete(MyUtility.myParseLong(request.getParameter("id")))) {
                request.setAttribute("deleted", "Deleted successfully");
                RequestDispatcher rd = request.getRequestDispatcher("/users/admin/viewEmployees.jsp");
                rd.forward(request, response);
            }
        } else {
            request.setAttribute("deleted", "Deletion failed");
            RequestDispatcher rd = request.getRequestDispatcher("/users/admin/viewEmployees.jsp");
            rd.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

}
