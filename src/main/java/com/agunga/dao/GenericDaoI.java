package com.agunga.dao;

import java.io.Serializable;
import java.util.List;
import javax.ejb.Local;

/**
 * Created by Administrator on 3/23/2017.
 * @param <T>
 * @param <PK>
 */ 
public interface GenericDaoI<T, PK extends Serializable> {
    T save(T t);
    T findById(PK id);
    T merge(T t);
    List<T> findAll();
    boolean remove(T t);
}
