package com.agunga.bean;

import com.agunga.model.Patient;
import javax.ejb.Stateless;
import com.agunga.beanI.PatientBeanI;
import com.agunga.dao.PatientDao;
import java.util.List;

@Stateless(mappedName = "patientBean")
public class PatientBean extends BaseBean implements PatientBeanI {

    @Override
    public Patient add(Patient patient) {
        return null;
    }

    @Override
    public List<Patient> viewAll() {
        return null;
    }

    @Override
    public Patient viewById(long id) {
        return null;
    }

    @Override
    public List<Patient> viewByPatientId(String pid) {
        return null;
    }

    @Override
    public Patient update(Patient patient) {
        return null;
    }

    @Override
    public boolean delete(long id) {
        session.delete(id);
        return true;
    }

    @Override
    public Patient diagnose(Patient patient) {
        return update(patient);
    }

    @Override
    public Patient test(Patient patient) {
        return update(patient);
    }

    @Override
    public Patient prescribe(Patient patient) {
        return update(patient);
    }

    @Override
    public Patient dispatchDrugs(Patient patient) {
        return update(patient);
    }

}
