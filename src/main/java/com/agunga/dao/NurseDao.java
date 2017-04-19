package com.agunga.dao;

import com.agunga.model.Nurse;
import javax.persistence.EntityManager;

/**
 * Created by Administrator on 3/24/2017.
 */
public class NurseDao extends GenericDao<Nurse, Long> {

    public NurseDao(EntityManager entityManager) {
        super(Nurse.class, entityManager);
    }
}
