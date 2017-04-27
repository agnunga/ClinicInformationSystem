/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agunga.bean;

import com.agunga.beanI.EmployeeBeanI;
import com.agunga.beanI.PatientBeanI;
import com.agunga.beanI.PersonBeanI;
import com.agunga.dao.HibernateUtils;
import javax.ejb.EJB;
import org.hibernate.Session;

/**
 *
 * @author agunga
 */
public class BaseBean {

    Session session = HibernateUtils.getSession();

    @EJB
    PersonBeanI personBean;

    @EJB
    EmployeeBeanI employeeBean;

    @EJB
    PatientBeanI patientBean;

}
