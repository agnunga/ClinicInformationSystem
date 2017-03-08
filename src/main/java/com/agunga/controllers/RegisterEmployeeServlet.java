package com.agunga.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.agunga.cis.Receptionist;


@WebServlet("/register_employee")
public class RegisterEmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("registerEmployee.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Receptionist receptionist = new Receptionist();
		
		receptionist.setName(request.getParameter("name"));
		receptionist.setNationalId(request.getParameter("nationalId"));
		receptionist.setPhone(request.getParameter("phone_no"));
		receptionist.setSex(request.getParameter("sex"));
		receptionist.setDob(request.getParameter("dob"));
		receptionist.setEmployeeNo(request.getParameter("employeeNo"));
		receptionist.setDateEmployed(request.getParameter("dateEmployed"));
		receptionist.setSalary(request.getParameter("salary"));
		receptionist.setTitle(request.getParameter("title"));
		receptionist.setAssignment(request.getParameter("assignment"));
		
		receptionist.registerReceptionist(receptionist);//registerEmployee
                response.sendRedirect("admin");
	}

}
