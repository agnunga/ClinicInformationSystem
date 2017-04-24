package com.agunga.bean;

import com.agunga.beanI.NurseBeanI;
import com.agunga.model.Nurse;
import com.agunga.model.Patient;
import java.util.List;
import javax.ejb.Stateless;

/**
 * Created by agunga on 1/18/17.
 */
@Stateless(mappedName = "nurseBean")
public class NurseBean extends BaseBean implements NurseBeanI {

    @Override
    public Nurse add(Nurse nurse) {
        return null;
    }

    @Override
    public Patient dispatchDrugs(Patient patient) {
        return patientBean.dispatchDrugs(patient);
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
