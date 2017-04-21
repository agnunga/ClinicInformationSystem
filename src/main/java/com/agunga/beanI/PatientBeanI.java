package com.agunga.beanI;

import com.agunga.model.Patient;
import java.util.List;
import javax.ejb.Local;

@Local
public interface PatientBeanI {

    public Patient add(Patient patient);

    public List<Patient> viewAll();

    public Patient viewById(long id);

    public Patient update(Patient patient);

    public boolean delete(long id);

    public Patient diagnose(Patient patient);

    public Patient test(Patient patient);

    public Patient prescribe(Patient patient);

    public Patient dispatchDrugs(Patient patient);
}
