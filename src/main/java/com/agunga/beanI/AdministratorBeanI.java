/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agunga.beanI;

import com.agunga.model.Employee;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author agunga
 */
@Local
public interface AdministratorBeanI {

    public Employee addEmployee(Employee employee);

    public List<Employee> viewEmployees();

    public Employee viewEmployee(long id);

}
