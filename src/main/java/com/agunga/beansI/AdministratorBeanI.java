/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agunga.beansI;

import com.agunga.models.Employee;
import java.util.ArrayList;
import javax.ejb.Local;

/**
 *
 * @author agunga
 */
@Local
public interface AdministratorBeanI {

    public boolean addEmployee(Employee employee);

    public ArrayList<Employee> viewEmployees();
}
