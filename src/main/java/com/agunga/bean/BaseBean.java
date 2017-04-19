/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agunga.bean;

import com.agunga.beanI.EmployeeBeanI;
import com.agunga.beanI.PatientBeanI;
import com.agunga.beanI.PersonBeanI;
import com.agunga.dbold.ConnectionType;
import com.agunga.dbold.MyConectivity;
import com.agunga.dbold.MysqlDbUtil;
import java.sql.Connection;
import javax.ejb.EJB;
import javax.inject.Inject;

/**
 *
 * @author agunga
 */
public class BaseBean {

//    @Inject
//    @ConnectionType(ConnectionType.Type.MYSQL)
//    MyConectivity mcon;
    
    MyConectivity mcon = new MysqlDbUtil();
    Connection conn = mcon.connectDB();

    @EJB
    PersonBeanI personBean;

    @EJB
    EmployeeBeanI employeeBean;

    @EJB
    PatientBeanI patientBean;

}
