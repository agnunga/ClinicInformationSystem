package com.agunga.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.ProcessBuilder.Redirect;
import java.util.Iterator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.agunga.cis.Administrator;
import com.agunga.cis.Patient;
import com.agunga.cis.Receptionist;


@WebServlet("/")
public class viewPatientsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		RequestDispatcher dispatcher = request.getRequestDispatcher("viewPatients.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doPut(req, resp);
	}
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doDelete(req, resp);
	}

}
