package com.agunga.beansI;

import com.agunga.models.Labtech;
import com.agunga.models.Patient;
import javax.ejb.Local;

@Local
public interface LabtechBeanI{

    public void add(Labtech labtech);

    public void test(Patient patient);

}
