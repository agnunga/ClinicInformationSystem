package com.agunga.dao;

import com.agunga.model.Labtech;
import javax.persistence.EntityManager;

/**
 * Created by Administrator on 3/24/2017.
 */
public class LabtechDao extends GenericDao<Labtech, Long> {

    public LabtechDao(EntityManager entityManager) {
        super(Labtech.class, entityManager);
    }
}
