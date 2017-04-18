package com.agunga.beanI;

import com.agunga.model.Patient;
import java.util.ArrayList;
import javax.ejb.Local;

@Local
public interface PatientBeanI{

    public boolean check(String nationalId);

    public boolean add(Patient patient);

    public ArrayList<Patient> view(String patientId);

    public boolean update(Patient patient);

    public boolean delete(String id);

    public void diagnose(Patient patient);

    public void test(Patient patient);

    public void prescribe(Patient patient);

    public void dispatchDrugs(Patient patient);
}
