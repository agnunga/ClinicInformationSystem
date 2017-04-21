package com.agunga.bean;

import com.agunga.dao.AdministratorDao; 
import javax.ejb.Stateless;
import com.agunga.beanI.AdministratorBeanI;
import com.agunga.model.Employee;
import java.util.List; 

/**
 * Created by agunga on 1/18/17.
 */
@Stateless(mappedName = "administratorBean")
public class AdministratorBean extends BaseBean implements AdministratorBeanI {

//    EntityManagerFactory emf = Persistence.createEntityManagerFactory(
//            "jdbc:mysql://localhost:3306/CISJPA;user=root;password="
//    );
//    EntityManager em = emf.createEntityManager();
    @Override
    public Employee addEmployee(Employee employee) {
        AdministratorDao ad = new AdministratorDao(em);
        return ad.save(employee);
    }

    @Override
    public List<Employee> viewEmployees() {
        return employeeBean.viewEmployees();
    }

    @Override
    public Employee viewEmployee(long id) {
        return employeeBean.viewEmployee(id);
    }

}
