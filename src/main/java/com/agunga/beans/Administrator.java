package com.agunga.beans;

import static com.agunga.beans.Employee.connection;
import com.agunga.dao.DbType;
import com.agunga.dao.DbUtil;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by agunga on 1/18/17.
 */
public class Administrator extends Employee {

    public static Connection connection = null;

    private String adminId;

    public String getAdminId() {
        return adminId;
    }

    public void registerSpecificEmployee(Employee employee) {

    }

    public ArrayList<Employee> viewEmployees() {
        connection = DbUtil.connectDB(DbType.MYSQL);
        ArrayList<Employee> employees = new ArrayList<>();

        String sql = "SELECT employees.nationalid, employeeno, title, dateemployed, "
                + " name, dob, phone, sex "
                + " FROM employees INNER JOIN persons"
                + " WHERE employees.nationalid = persons.nationalid;";
        ResultSet resultSet = DbUtil.select(sql);
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
            e.printStackTrace();
        }
        return employees;
    }

    @Override
    void work() {
        // TODO Auto-generated method stub

    }
}
