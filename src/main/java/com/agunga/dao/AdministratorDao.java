package com.agunga.dao;

import com.agunga.model.Employee;
import javax.persistence.EntityManager;

/**
 * Created by Administrator on 3/24/2017.
 */
public class AdministratorDao extends GenericDao<Employee, Long> {

    public AdministratorDao(EntityManager entityManager) {
        super(Employee.class, entityManager);
    }
}
