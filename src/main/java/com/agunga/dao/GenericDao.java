package com.agunga.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import java.io.Serializable;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Administrator on 3/23/2017.
 *
 * @param <T>
 * @param <PK>
 */
public class GenericDao<T, PK extends Serializable> implements GenericDaoI<T, PK> {

    private Logger logger;
    private Class<T> entityClass;
    private EntityManager entityManager;

    public GenericDao(Class<T> entityClass, EntityManager entityManager) {
        this.logger = LoggerFactory.getLogger(entityClass);
        this.entityClass = entityClass;
        this.entityManager = entityManager;
        this.logger = null;//new (entityClass);
    }

    @Override
    public T save(T t) {
        try {
            this.entityManager.persist(t);
            logger.info("sucess");
            return t;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        } finally {
        }
    }

    @Override
    public T findById(PK id) {
        try {
            return this.entityManager.find(entityClass, id);
        } catch (PersistenceException pe) {
            logger.error(pe.getMessage());
            return null;
        }
    }

    @Override
    public T merge(T t) {
        try {
            this.entityManager.merge(t);
            return t;
        } catch (Exception pe) {
            logger.error(pe.getMessage());
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
            t = this.entityManager.merge(t);
            this.entityManager.remove(t);
            return true;
        } catch (Exception pe) {
            logger.error(pe.getMessage());
            return false;
        }
    }

    public List<T> processJPQL(String jpql, String paramName, String paramValue) {
        return this.entityManager.createNamedQuery(jpql)
                .setParameter(paramName, paramValue)
                .getResultList();
    }
}
