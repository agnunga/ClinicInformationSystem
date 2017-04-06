/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agunga.accounting;

import com.agunga.beans.Administrator;
import com.agunga.dao.ConnectionType;
import com.agunga.dao.MyConectivity;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author agunga
 */
@WebServlet(name = "recordTransaction", urlPatterns = {"/recordTransaction"})
public class RecordTransactionServlet extends HttpServlet {

    @Inject
    @ConnectionType(ConnectionType.Type.MYSQL)
    public MyConectivity mcon;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        Connection connX = mcon.connectDB();

        if (connX != null) {
            out.print("Connected thro' CDI");
        } else {
            out.print("Connected thro' CDI");
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("recordTransaction.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Connection conn = mcon.connectDB();

        PrintWriter out = response.getWriter();

//        String transactionID = (request.getParameter("employeeNo"));
        String amount = (request.getParameter("amount"));
//        String transactionFee = (request.getParameter(""));
        String type = (request.getParameter("type"));
//        String mode = (request.getParameter(""));

        String sql = "INSERT INTO transaction "
                + "(amount, transactionFee, type, mode) "
                + "VALUES (NULL, '', '', '', '', CURRENT_TIMESTAMP)";

        request.setAttribute("login_error", "Invalid Credentials. Try again");
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }

}
