package com.agunga.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by agunga on 1/18/17.
 */
abstract public class Employee extends Person {

    private String employeeNo;
    private String dateEmployed;
    private String salary;
    private String title;

    public String getEmployeeNo() {
        return employeeNo;
    }

    public void setEmployeeNo(String employeeNo) {
        this.employeeNo = employeeNo;
    }

    public String getDateEmployed() {
        return dateEmployed;
    }

    public void setDateEmployed(String dateEmployed) {
        this.dateEmployed = dateEmployed;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    abstract void work();

    public void createEployeeTable(Connection conn) {
        String sql = "create table employees(id int(11) AUTO_INCREMENT PRIMARY KEY, "
                + " nationalid int(8) NOT NULL, "
                + " employeeno varchar(15) NOT NULL, "
                + " salary varchar(12) NOT NULL, "
                + " title varchar(255), "
                + " password varchar(255));";

        mcon.createTable(sql, "employees", conn);
    }

    public boolean checkEmployee(Employee employee, Connection conn) {

        boolean exists = false;
        String sql = "SELECT employeeno "
                + " FROM employees "
                + " WHERE nationalid = " + employee.getNationalId() + ";";
        ResultSet resultSet = mcon.select(sql, conn);
        try {
            while (resultSet.next()) {
                exists = true;
                employee.setEmployeeNo(resultSet.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exists;
    }

    public boolean registerEmployee(Employee employee, Connection conn) {
        boolean added = false;
        super.registerPerson(employee, conn);
        if (checkEmployee(employee, conn)) {
            System.out.print("Employee Exists. ");
        } else {
            String sql = "INSERT INTO employees "
                    + " (nationalid, employeeno, dateemployed, salary, title, password) "
                    + " VALUES(?, ?, ?, ?, ?, ?)";

            try {
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setString(1, employee.getNationalId());
                preparedStatement.setString(2, employee.getEmployeeNo());
                preparedStatement.setString(3, employee.getDateEmployed());
                preparedStatement.setString(4, employee.getSalary());
                preparedStatement.setString(5, employee.getTitle());
                preparedStatement.setString(6, employee.getNationalId());

                if (mcon.insert(preparedStatement, conn) > 0) {
                    added = true;
                    System.out.print("Employee registered. ");
                } else {
                    System.err.print("Employee registration failed. ");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return added;
    }

    public String[] logIn(String employeeno, String password, Connection con) {
        String[] role = {"", ""};
        String sql = "SELECT title,  employeeno"
                + " FROM employees "
                + " WHERE employeeno = '" + employeeno + "' AND password = '" + password + "' "
                + "";
        ResultSet resultSet = mcon.select(sql, con);
        try {
            if (resultSet.next()) {
                role[0] = (resultSet.getString(1));
                role[1] = (resultSet.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return role;
    }

}
