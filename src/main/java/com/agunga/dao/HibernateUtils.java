/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agunga.dao;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.hibernate.HibernateException;
import org.hibernate.Interceptor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author agunga
 */
public final class HibernateUtils {

    private static final Logger logger = Logger.getLogger(HibernateUtils.class.getName());
    private static final SessionFactory sessionFactory;
    private static final ThreadLocal<Session> sessionThread = new ThreadLocal<Session>();
    private static final ThreadLocal<Interceptor> interceptorThread = new ThreadLocal<Interceptor>();

    static {

        try {
//            sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
            sessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (HibernateException e) {
            logger.log(Level.SEVERE, "Error intializing SessionFactory.", e.getLocalizedMessage());
            throw new ExceptionInInitializerError(e);
        }
    }

    private HibernateUtils() {
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static Session getSession() {
        Session session = sessionThread.get();
        if (session == null) {
            Interceptor interceptor = getInterceptor();
            if (interceptor != null) {
                session = getSessionFactory().withOptions().interceptor(interceptor).openSession();
            } else {
                session = getSessionFactory().openSession();
            }
            if (session != null) {
                sessionThread.set(session);
            }
        }
        return session;
    }

    public static void closeSession() {
        Session session = sessionThread.get();
        sessionThread.set(null);
        if (session != null && session.isOpen()) {
            session.close();
        }
    }

    public static void registerInterceptor(Interceptor interceptor) {
        interceptorThread.set(interceptor);
    }

    public static Interceptor getInterceptor() {
        return interceptorThread.get();
    }
}
