package com.agunga.beans;

import com.agunga.models.Labtech;
import com.agunga.models.Patient;
 
import javax.ejb.Stateless;
import com.agunga.beansI.LabtechBeanI;

@Stateless(mappedName = "labtechBean")
public class LabtechBean extends BaseBean implements LabtechBeanI {

    @Override
    public void add(Labtech labtech) { 
    }

    @Override
    public void test(Patient patient) {
    }

}
