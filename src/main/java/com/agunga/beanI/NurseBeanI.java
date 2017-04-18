package com.agunga.beanI;

import com.agunga.model.Patient;
import javax.ejb.Local;

@Local
public interface NurseBeanI{

    public void dispatchDrugs(Patient patient);

}
