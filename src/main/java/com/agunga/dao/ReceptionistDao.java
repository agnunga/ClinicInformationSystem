package com.agunga.dao;

import com.agunga.model.Employee;
import com.agunga.model.Receptionist;
import javax.persistence.EntityManager;

/**
 * Created by Administrator on 3/24/2017.
 */
public class ReceptionistDao extends GenericDao<Receptionist, Long> {

    public ReceptionistDao(EntityManager entityManager) {
        super(Receptionist.class, entityManager);
    }
}
