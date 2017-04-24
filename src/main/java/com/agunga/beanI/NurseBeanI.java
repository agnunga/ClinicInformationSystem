package com.agunga.beanI;

import com.agunga.model.Labtech;
import com.agunga.model.Nurse;
import com.agunga.model.Patient;
import java.util.List;
import javax.ejb.Local;

@Local
public interface NurseBeanI {

    public Nurse add(Nurse nurse);

    public Patient dispatchDrugs(Patient patient);

    public Patient viewPatient(long id);

    public List<Patient> viewPatients();

}
