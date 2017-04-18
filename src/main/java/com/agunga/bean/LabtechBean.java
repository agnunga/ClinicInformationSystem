package com.agunga.bean;

import com.agunga.model.Labtech;
import com.agunga.model.Patient;
 
import javax.ejb.Stateless;
import com.agunga.beanI.LabtechBeanI;

@Stateless(mappedName = "labtechBean")
public class LabtechBean extends BaseBean implements LabtechBeanI {

    @Override
    public void add(Labtech labtech) { 
    }

    @Override
    public void test(Patient patient) {
    }

}
