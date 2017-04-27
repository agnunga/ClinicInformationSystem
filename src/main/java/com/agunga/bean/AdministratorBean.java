package com.agunga.bean;

import com.agunga.dao.AdministratorDao;
import javax.ejb.Stateless;
import com.agunga.beanI.AdministratorBeanI;
import com.agunga.model.Employee;
import java.util.List;
import org.hibernate.Transaction;

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
        Transaction t = session.beginTransaction();
        try {
            session.persist(employee);
            t.commit();
            return employee;
        } catch (Exception e) {
            t.rollback();
            return null;
        }
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
