package com.agunga.bean;

import com.agunga.beanI.EmployeeBeanI;
import com.agunga.model.Employee;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.ejb.Stateless; 
import com.agunga.model.Administrator; 
import com.agunga.util.MyUtility; 
import java.util.ArrayList;

/**
 * Created by agunga on 1/18/17.
 */
@Stateless(mappedName = "employeeBean")
public class EmployeeBean extends BaseBean implements EmployeeBeanI {

    @Override
    public boolean check(Employee employee) {

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

    @Override
    public boolean add(Employee employee) {
        boolean added = false;
        personBean.add(employee);
        if (check(employee)) {
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

    @Override
    public ArrayList<Employee> view() {
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
    public void update(Employee employee) {
        String sql_update = "UPDATE employees SET emp_no = ?, date_employed = ?, salary = ?, title= ? WHERE id = ?";

        int id = MyUtility.myScanner().nextInt();
        PreparedStatement preparedStatement;
        try {
            preparedStatement = conn.prepareStatement(sql_update);
            preparedStatement.setString(1, "111");
            preparedStatement.setString(2, "10/12/205");
            preparedStatement.setString(3, "8320");
            preparedStatement.setString(4, "Software Engineer XXX");
            preparedStatement.setInt(5, id);

            if (mcon.update(preparedStatement, conn) >= 0) {
                System.out.println("Employee deleted.");
            } else {
                System.out.println("Failed to delete employee.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Details After Updating.");
    }

    @Override
    public void delete(int id) {

        String sql_delete = "DELETE FROM employees WHERE id = ?";
        System.out.print("Enter id of column you want to delete: ");
        if (mcon.delete(sql_delete, id, conn) >= 0) {
            System.out.println("Employee deleted.");
        } else {
            System.out.println("Failed to delete employee.");
        }

        System.out.println("Details After Deleting.");
    }

    @Override
    public String[] logIn(String employeeno, String password) {
        if (mcon != null) {
            System.out.println("mcon is Not null logIn");
        } else {
            System.out.println("mcon null logIn");
        }

        if (conn != null) {
            System.out.println("con is NOT null logIn");
        } else {
            System.out.println("con null logIn");
        }

        String[] role = {"", ""};
        String sql = "SELECT title,  employeeno"
                + " FROM employees "
                + " WHERE employeeno = '" + employeeno + "' AND password = '" + password + "' "
                + "";
        ResultSet resultSet = mcon.select(sql, conn);
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
