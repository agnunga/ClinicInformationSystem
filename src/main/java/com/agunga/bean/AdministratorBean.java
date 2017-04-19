package com.agunga.bean;

import com.agunga.dao.AdministratorDao;
import java.util.ArrayList;
import javax.ejb.Stateless;
import com.agunga.beanI.AdministratorBeanI;
import com.agunga.model.Employee;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by agunga on 1/18/17.
 */
@Stateless(mappedName = "administratorBean")
public class AdministratorBean extends BaseBean implements AdministratorBeanI {

    @PersistenceContext(unitName = "CISJPA")
    private EntityManager em;

//    EntityManagerFactory emf = Persistence.createEntityManagerFactory(
//            "jdbc:mysql://localhost:3306/CISJPA;user=root;password="
//    );
//    EntityManager em = emf.createEntityManager();
    @Override
    public boolean addEmployee(Employee employee) {
        AdministratorDao ad = new AdministratorDao(em);
        return ad.save(employee) != null;
    }

    @Override
    public ArrayList<Employee> viewEmployees() {
        return employeeBean.view();
    }

}
