package com.agunga.bean;

import java.util.ArrayList;
import javax.ejb.Stateless;
import com.agunga.beanI.AdministratorBeanI;
 import com.agunga.model.Employee;
 
/**
 * Created by agunga on 1/18/17.
 */
@Stateless(mappedName = "administratorBean")
public class AdministratorBean extends BaseBean implements AdministratorBeanI {

    @Override
    public boolean addEmployee(Employee employee) {
        return employeeBean.add(employee);
    }

    @Override
    public ArrayList<Employee> viewEmployees() {
        return employeeBean.view();
    }

}
