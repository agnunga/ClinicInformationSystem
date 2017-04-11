package com.agunga.beansI;

import com.agunga.models.Patient;
import javax.ejb.Local;

@Local
public interface NurseBeanI{

    public void dispatchDrugs(Patient patient);

}
