package com.agunga.dao;

import com.agunga.model.Patient;
import javax.persistence.EntityManager;

/**
 * Created by Administrator on 3/24/2017.
 */
public class PatientDao extends GenericDao<Patient, Long> {

    public PatientDao(EntityManager entityManager) {
        super(Patient.class, entityManager);
    }
}
