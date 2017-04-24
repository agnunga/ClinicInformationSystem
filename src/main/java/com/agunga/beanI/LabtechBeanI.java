package com.agunga.beanI;

import com.agunga.model.Labtech;
import com.agunga.model.Patient;
import java.util.List;
import javax.ejb.Local;

@Local
public interface LabtechBeanI {

    public Labtech add(Labtech labtech);

    public Patient test(Patient patient);

    public Patient viewPatient(long id);

    public List<Patient> viewPatients();

}
