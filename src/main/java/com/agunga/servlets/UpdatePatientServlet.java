package com.agunga.servlets;

import java.io.IOException;
import java.io.PrintWriter; 

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.agunga.cis.Patient; 
import com.agunga.cis.Receptionist; 

@WebServlet("/update_patient")
public class UpdatePatientServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("id")!=null){
			RequestDispatcher rd = request.getRequestDispatcher("updatePatient.jsp");
			rd.forward(request, response);
		}else{
			request.setAttribute("updated", "Invalid Option. No record selected for update");
			RequestDispatcher rd = request.getRequestDispatcher("viewPatients.jsp");
			rd.forward(request, response);
		}
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

		if (new Receptionist().updatePatintDetails(patient)) {
			out.print("UPDATED");
			//			response.sendRedirect("view_patients?id="+patient.getNationalId()+"&us=0");
			request.setAttribute("updated", patient.getName()+" ("+ patient.getNationalId()+") successfully updated.");
			RequestDispatcher rd = request.getRequestDispatcher("viewPatients.jsp");
			rd.forward(request, response);
		}else{
			out.print("UPDATE FAILED");
			response.sendRedirect("update_patient?id="+patient.getNationalId()+"&uf=1");

		}
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}

}
