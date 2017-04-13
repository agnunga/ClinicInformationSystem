/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agunga.beans;

import com.agunga.beansI.EmployeeBeanI;
import com.agunga.beansI.PatientBeanI;
import com.agunga.beansI.PersonBeanI;
import com.agunga.dao.ConnectionType;
import com.agunga.dao.MyConectivity;
import com.agunga.dao.MysqlDbUtil;
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
