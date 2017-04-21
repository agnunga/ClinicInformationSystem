package com.agunga.bean;

import com.agunga.beanI.DoctorBeanI;
import com.agunga.dao.DoctorDao;
import com.agunga.model.Patient;
import java.util.List;
import javax.ejb.Stateless;

@Stateless(mappedName = "doctorBean")
public class DoctorBean extends BaseBean implements DoctorBeanI {
    
    @Override
    public Patient diagnose(Patient patient) {
        return patientBean.diagnose(patient);
    }
    
    @Override
    public Patient prescribe(Patient patient) {
        return patientBean.prescribe(patient);
    }
    
    @Override
    public List<Patient> viewPatients() {
        return patientBean.viewAll();
    }
    
    @Override
    public Patient viewPatient(long id) {
        return patientBean.viewById(id);
    }
    
}
