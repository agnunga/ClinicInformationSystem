/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agunga.dbold;

import com.agunga.model.Person;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author agunga
 */
public class MyPersistance {

    static EntityManagerFactory emf = Persistence.createEntityManagerFactory(
            "jdbc:mysql://localhost:3306/CISJPA;user=root;password="
    );

    static EntityManager em = emf.createEntityManager();

    public static void addPerson(Person p) {
        em.persist(p);
    }
}
