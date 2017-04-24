package com.agunga.dao;

import com.agunga.util.JLogger;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityTransaction;

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

    @Override
    public T save(T t) {
        EntityTransaction et = entityManager.getTransaction();
        try {
            et.begin();
            this.entityManager.persist(t);
            et.commit();
            return t;

        } catch (Exception ep) {
            et.rollback();
            jLogger.e(ep);
            return null;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public T findById(PK id) {
        EntityTransaction et = entityManager.getTransaction();
        try {
            et.begin();
            return this.entityManager.find(entityClass, id);
//            et.commit();
        } catch (PersistenceException pe) {
            et.rollback();
            jLogger.e(pe.getMessage());
            return null;
        }
    }

    @Override
    public T merge(T t) {
        EntityTransaction et = entityManager.getTransaction();
        try {
            et.begin();
            this.entityManager.merge(t);
            et.commit();
            return t;
        } catch (Exception pe) {
            et.rollback();
            jLogger.e(pe.getMessage());
            return null;
        }
    }

    @Override
    public List<T> findAll() {
        return this.entityManager.createQuery("SELECT t FROM " + entityClass.getSimpleName() + " t").getResultList();
    }

    @Override
    public boolean remove(T t) {
        EntityTransaction et = entityManager.getTransaction();
        try {
            et.begin();
            t = this.entityManager.merge(t);
            this.entityManager.remove(t);
//            this.entityManager.getTransaction().commit();
            return true;
        } catch (Exception pe) {
            et.rollback();
            jLogger.e(pe.getMessage());
            return false;
        }
    }
}
