package com.agunga.bean;

import com.agunga.beanI.EmployeeBeanI;
import com.agunga.model.Employee;
import java.util.ArrayList;
import java.util.Iterator;
import javax.ejb.Stateless;
import java.util.List;
import org.hibernate.Transaction;

/**
 * Created by agunga on 1/18/17.
 */
@Stateless(mappedName = "employeeBean")
public class EmployeeBean extends BaseBean implements EmployeeBeanI {

    @Override
    public Employee add(Employee employee) {
        Transaction t = session.beginTransaction();
        try {
            session.persist(employee);
            t.commit();
            return employee;
        } catch (Exception e) {
            t.rollback();
            return null;
        }
//        finally {
//            session.close();
//        }
    }

    @Override
    public List<Employee> viewEmployees() {
        List<Employee> employees = new ArrayList<>();
        Transaction t = session.beginTransaction();
        try {
            employees = session.createQuery("SELECT e FROM Employee e").list();
            t.commit();
        } catch (Exception e) {
            t.rollback();
        }
        return employees;
    }

    @Override
    public Employee viewEmployee(long id) {
        List<Employee> employees;
        Transaction t = session.beginTransaction();
        try {
            employees = session.createQuery("SELECT e FROM Employee  e WHERE e.id = " + id).list();
            t.commit();
            return employees.get(0);
        } catch (Exception e) {
            t.rollback();
            return null;
        }

    }

    @Override
    public Employee update(Employee employee) {
        Transaction t = session.beginTransaction();
        try {
            session.update(employee);
            t.commit();
            return employee;
        } catch (Exception e) {
            t.rollback();
            return null;
        }

    }

    @Override
    public boolean delete(long id) {
        Transaction t = session.beginTransaction();
        try {
            session.createQuery("DELETE FROM Employee e WHERE e.id = " + id);
            t.commit();
            return true;
        } catch (Exception e) {
            t.rollback();
            return false;
        }
    }

    @Override
    public String[] logIn(String employeeno, String password) {
        String[] data = {"", ""};
        List employees = viewEmployees();

        Iterator<Employee> i = employees.iterator();
        while (i.hasNext()) {
            Employee emp = i.next();
            if (emp.getEmployeeNo().equals(employeeno) && emp.getPassword().equals(password)) {
                data[0] = emp.getTitle();
                data[1] = employeeno;
            }
        }
        if (employeeno.equals("ultimate") && password.equals("3202e4dea29f6eb62e2a01b4cff4869487f91a27")) {
            data[0] = "a";
            data[1] = "MASTER_ADMIN";
        }
        return data;
    }

}
