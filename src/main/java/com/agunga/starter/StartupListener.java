/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agunga.starter;

import com.agunga.db.DbUtil;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Web application lifecycle listener.
 *
 * @author agunga
 */
public class StartupListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
//        if (DbUtil.createTables()) {
//            System.out.println("Agunga--CONNECTED to CIS database");
//        } else {
//            System.err.println("Agunga--FAILED to connect to CIS database");
//        }
        throw new UnsupportedOperationException("It seems ServletContextListener Did Not Work(UnsupportedOperationException)."); //To change body of generated methods, choose Tools | Templates.

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
