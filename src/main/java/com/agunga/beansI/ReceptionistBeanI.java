package com.agunga.beansI;

import com.agunga.models.Patient;
import com.agunga.models.Receptionist;
import java.util.ArrayList;
import javax.ejb.Local;

@Local
public interface ReceptionistBeanI{

    public boolean check(Receptionist receptionist);

    public boolean add(Receptionist receptionist);

    public boolean addPatient(Patient patient);

    public ArrayList<Patient> viewPatient(String patientId);

    public boolean updatePatient(Patient patient);

    public boolean deletePatient(String id);
}
