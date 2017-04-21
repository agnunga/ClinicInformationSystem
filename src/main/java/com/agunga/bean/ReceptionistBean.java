package com.agunga.bean;

import com.agunga.model.Patient;
import com.agunga.model.Receptionist;
import javax.ejb.Stateless;
import com.agunga.beanI.ReceptionistBeanI;
import com.agunga.dao.ReceptionistDao;
import java.util.List;

/**
 * Created by agunga on 1/18/17.
 */
@Stateless(mappedName = "receptionistBean")
public class ReceptionistBean extends BaseBean implements ReceptionistBeanI {

    @Override
    public Receptionist add(Receptionist receptionist) {
        ReceptionistDao rd = new ReceptionistDao(em);
        return rd.save(receptionist);
    }

    @Override
    public Patient addPatient(Patient patient) {
        return patientBean.add(patient);
    }

    @Override
    public List<Patient> viewPatients() {
        return patientBean.viewAll();
    }

    @Override
    public Patient viewPatient(long id) {
        return patientBean.viewById(id);
    }

    @Override
    public Patient updatePatient(Patient patient) {
        return patientBean.update(patient);
    }

    @Override
    public boolean deletePatient(long id) {
        return patientBean.delete(id);
    }

}
