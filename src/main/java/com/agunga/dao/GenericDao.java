package com.agunga.dao;

import com.agunga.util.JLogger;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import java.io.Serializable;
import java.util.List; 
import javax.annotation.Resource;
import javax.transaction.UserTransaction;

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

    @Resource
    UserTransaction utx;
    
    public GenericDao(Class<T> entityClass, EntityManager entityManager) {
        this.entityClass = entityClass;
        this.entityManager = entityManager;
        this.jLogger = new JLogger(entityClass);
    }

    @Override
    public T save(T t) {
        try {
//            this.entityManager.getTransaction().begin();
            this.entityManager.persist(t);
//            this.entityManager.getTransaction().commit();
            return t;
        } catch (Exception ep) {
//            this.entityManager.getTransaction().rollback();
            jLogger.e(ep);
            return null;
        }
    }

    @Override
    public T findById(PK id) {
        try {
            return this.entityManager.find(entityClass, id);
        } catch (PersistenceException pe) {
            jLogger.e(pe.getMessage());
            return null;
        }
    }

    @Override
    public T merge(T t) {
        try {
//            this.entityManager.getTransaction().begin();
            this.entityManager.merge(t);
//            this.entityManager.getTransaction().commit();
            return t;
        } catch (Exception pe) {
//            this.entityManager.getTransaction().rollback();
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
        try {
//            this.entityManager.getTransaction().begin();
            t = this.entityManager.merge(t);
            this.entityManager.remove(t);
//            this.entityManager.getTransaction().commit();
            return true;
        } catch (Exception pe) {
            jLogger.e(pe.getMessage());
            return false;
        }
    }
}
