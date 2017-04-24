package com.agunga.bean;

import com.agunga.model.Labtech;
import com.agunga.model.Patient;

import javax.ejb.Stateless;
import com.agunga.beanI.LabtechBeanI;
import com.agunga.model.Employee;
import java.util.List;

@Stateless(mappedName = "labtechBean")
public class LabtechBean extends BaseBean implements LabtechBeanI {

    @Override
    public Labtech add(Labtech labtech) {
//        return employeeBean.add(labtech);
        return null;
    }

    @Override
    public Patient test(Patient patient) {
        return patientBean.test(patient);
    }

    @Override
    public Patient viewPatient(long id) {
        return patientBean.viewById(id);
    }

    @Override
    public List<Patient> viewPatients() {
        return patientBean.viewAll();
    }

}
