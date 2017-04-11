package com.agunga.beans;

import java.util.ArrayList;
import javax.ejb.Stateless;
import com.agunga.beansI.AdministratorBeanI;
 import com.agunga.models.Employee;
 
/**
 * Created by agunga on 1/18/17.
 */
@Stateless(mappedName = "administratorBean")
public class AdministratorBean extends BaseBean implements AdministratorBeanI {

//    @EJB(name = "employeeBean")
//    EmployeeBeanI employeeBean;

    @Override
    public boolean addEmployee(Employee employee) {
        return employeeBean.add(employee);
    }

    @Override
    public ArrayList<Employee> viewEmployees() {
        return employeeBean.view();
    }

}
