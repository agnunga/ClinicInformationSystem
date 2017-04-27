package com.agunga.bean;

import com.agunga.model.Patient;
import com.agunga.model.Receptionist;
import javax.ejb.Stateless;
import com.agunga.beanI.ReceptionistBeanI;
import com.agunga.dao.ReceptionistDao;
import java.util.List;
import org.hibernate.Transaction;

/**
 * Created by agunga on 1/18/17.
 */
@Stateless(mappedName = "receptionistBean")
public class ReceptionistBean extends BaseBean implements ReceptionistBeanI {

    @Override
    public Receptionist add(Receptionist receptionist) {
        Transaction t = session.beginTransaction();
        try {
            session.persist(receptionist);
            t.commit();
            return receptionist;
        } catch (Exception e) {
            t.rollback();
            return null;
        }
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
    public List<Patient> viewByPatientId(String pid) {
        return patientBean.viewByPatientId(pid);
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
