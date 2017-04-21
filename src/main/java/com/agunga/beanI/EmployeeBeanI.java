package com.agunga.beanI;

import com.agunga.model.Employee;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Local;

@Local
public interface EmployeeBeanI {

    public Employee add(Employee employee);

    public List<Employee> viewEmployees();

    public Employee viewEmployee(long id);

    public Employee update(Employee e);

    public boolean delete(long id);

    public String[] logIn(String employeeno, String password);
}
