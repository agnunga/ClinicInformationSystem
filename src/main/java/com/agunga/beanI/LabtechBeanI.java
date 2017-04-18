package com.agunga.beanI;

import com.agunga.model.Labtech;
import com.agunga.model.Patient;
import javax.ejb.Local;

@Local
public interface LabtechBeanI{

    public void add(Labtech labtech);

    public void test(Patient patient);

}
