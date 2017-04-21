package com.agunga.beanI;

import com.agunga.model.Patient;
import java.util.List;
import javax.ejb.Local;

/**
 * Created by agunga on 1/18/17.
 */
@Local
public interface DoctorBeanI {

    public Patient diagnose(Patient p);

    public Patient prescribe(Patient p);

    public List<Patient> viewPatients();

    public Patient viewPatient(long id);
}
