/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agunga.bean;

import com.agunga.beanI.EmployeeBeanI;
import com.agunga.beanI.PatientBeanI;
import com.agunga.beanI.PersonBeanI;
import com.agunga.db.ConnectionType;
import com.agunga.db.MyConectivity;
import com.agunga.db.MysqlDbUtil;
import java.sql.Connection;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author agunga
 */
public class BaseBean {

//    @Inject
//    @ConnectionType(ConnectionType.Type.MYSQL)
//    MyConectivity mcon;
    
    @PersistenceContext(unitName = "CISJPA")
    public EntityManager em;

    MyConectivity mcon = new MysqlDbUtil();
    Connection conn = mcon.connectDB();

    @EJB
    PersonBeanI personBean;

    @EJB
    EmployeeBeanI employeeBean;

    @EJB
    PatientBeanI patientBean;

}
