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
        PatientDao pd = new PatientDao(em);
        return pd.save(patient);
    }

    @Override
    public List<Patient> viewAll() {
        PatientDao pd = new PatientDao(em);
        return pd.findAll();
    }

    @Override
    public Patient viewById(long id) {
        PatientDao pd = new PatientDao(em);
        return pd.findById(id);
    }

    @Override
    public Patient update(Patient patient) {
        PatientDao pd = new PatientDao(em);
        return pd.merge(patient);
    }

    @Override
    public boolean delete(long id) {
        PatientDao pd = new PatientDao(em);
        Patient patient = pd.findById(id);
        return pd.remove(patient);
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
