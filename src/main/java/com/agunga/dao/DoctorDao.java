package com.agunga.dao;

import com.agunga.model.Doctor;
import com.agunga.model.Employee;
import com.agunga.model.Receptionist;
import javax.persistence.EntityManager;

/**
 * Created by Administrator on 3/24/2017.
 */
public class DoctorDao extends GenericDao<Doctor, Long> {

    public DoctorDao(EntityManager entityManager) {
        super(Doctor.class, entityManager);
    }
}
