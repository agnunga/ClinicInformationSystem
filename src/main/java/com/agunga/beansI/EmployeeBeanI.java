package com.agunga.beansI;

import com.agunga.models.Employee;
import java.util.ArrayList;
import javax.ejb.Local;

@Local
public interface EmployeeBeanI{

    public boolean check(Employee employee);

    public boolean add(Employee employee);

    public ArrayList<Employee> view();

    public void update(Employee employee);

    public void delete(int id);

    public String[] logIn(String employeeno, String password);
}
