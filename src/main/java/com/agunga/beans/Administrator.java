package com.agunga.beans;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by agunga on 1/18/17.
 */
public class Administrator extends Employee {

    private String adminId;

    public String getAdminId() {
        return adminId;
    }

    public void registerSpecificEmployee(Employee employee) {

    }

    public ArrayList<Employee> viewEmployees(Connection conn) {
        ArrayList<Employee> employees = new ArrayList<>();

        String sql = "SELECT employees.nationalid, employeeno, title, dateemployed, "
                + " name, dob, phone, sex "
                + " FROM employees INNER JOIN persons"
                + " WHERE employees.nationalid = persons.nationalid;";
        ResultSet resultSet = mcon.select(sql, conn);
        try {
            while (resultSet.next()) {
                Administrator e = new Administrator();

                e.setNationalId(resultSet.getString(1));
                e.setEmployeeNo(resultSet.getString(2));
                e.setTitle(resultSet.getString(3));
                e.setDateEmployed(resultSet.getString(4));
                e.setName(resultSet.getString(5));
                e.setDob(resultSet.getString(6));
                e.setPhone(resultSet.getString(7));
                e.setSex(resultSet.getString(8));

                employees.add(e);
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return employees;
    }

    @Override
    void work() {
    }
}
