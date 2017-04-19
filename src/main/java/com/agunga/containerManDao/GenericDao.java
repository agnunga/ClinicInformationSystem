package com.agunga.containerManDao;

import com.agunga.util.JLogger;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 3/23/2017.
 *
 * @param <T>
 * @param <PK>
 */
public class GenericDao<T, PK extends Serializable> implements GenericDaoI<T, PK> {

    private Class<T> entityClass;
    private EntityManager entityManager;
    private JLogger jLogger;

    public GenericDao(Class<T> entityClass, EntityManager entityManager) {
        this.entityClass = entityClass;
        this.entityManager = entityManager;
        this.jLogger = new JLogger(entityClass);
    }

    public T save(T t) {
        try {
            this.entityManager.persist(t);
            return t;
        } catch (PersistenceException pe) {
            jLogger.e(pe.getMessage());
            return null;
        }
    }

    public T findById(PK id) {
        try {
            return this.entityManager.find(entityClass, id);
        } catch (PersistenceException pe) {
            jLogger.e(pe.getMessage());
            return null;
        }
    }

    public T merge(T t) {
        try {
            return this.entityManager.merge(t);
        } catch (PersistenceException pe) {
            jLogger.e(pe.getMessage());
            return null;
        }
    }

    public List<T> findAll() {
        return this.entityManager.createQuery("SELECT t FROM " + entityClass.getSimpleName() + " t").getResultList();
    }

    public boolean remove(T t) {
        try {
            t = this.entityManager.merge(t);
            this.entityManager.remove(t);
            return true;
        } catch (PersistenceException pe) {
            jLogger.e(pe.getMessage());
            return false;
        }
    }
}
