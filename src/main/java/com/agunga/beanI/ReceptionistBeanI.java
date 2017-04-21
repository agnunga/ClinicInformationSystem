package com.agunga.beanI;

import com.agunga.model.Patient;
import com.agunga.model.Receptionist;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Local;

@Local
public interface ReceptionistBeanI {

    public Receptionist add(Receptionist receptionist);

    public Patient addPatient(Patient patient);

    public List<Patient> viewPatients();

    public Patient viewPatient(long id);

    public Patient updatePatient(Patient patient);

    public boolean deletePatient(long id);
}
