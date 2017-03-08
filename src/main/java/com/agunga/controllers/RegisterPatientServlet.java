package com.agunga.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.agunga.cis.MyUtility;
import com.agunga.cis.Patient;
import com.agunga.cis.Person;
import com.agunga.cis.Receptionist;
import com.agunga.db.DbType;
import com.agunga.db.DbUtil;

@WebServlet("/add_patient")
public class RegisterPatientServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("registerPatient.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		Patient patient = new Patient();
		patient.setName(request.getParameter("name"));
		patient.setNationalId(request.getParameter("nationalId"));
		patient.setDob(request.getParameter("dob"));
		patient.setPhone(request.getParameter("patient_phone"));
		patient.setSex(request.getParameter("sex"));
		
		patient.setPatientId(request.getParameter("patientId"));
		
		if (new Receptionist().registerPatient(patient)) {
			out.print("INSERTED");
		}else{
//			out.print("INSERT FAILED");
			RequestDispatcher rd = request.getRequestDispatcher("registerPatient.jsp");
			rd.forward(request, response);
		}
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}

}
