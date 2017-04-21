package com.agunga.dao.app;

import com.agunga.model.Patient;
import javax.persistence.EntityManager;

/**
 * Created by Administrator on 3/24/2017.
 */
public class CustomerDao extends GenericDao<Patient, Long> {
    public CustomerDao(EntityManager entityManager) {
        super(Patient.class, entityManager);
    }
}
