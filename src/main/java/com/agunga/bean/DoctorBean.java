package com.agunga.bean;

import com.agunga.beanI.DoctorBeanI;
import com.agunga.model.Patient;
import javax.ejb.Stateless;

@Stateless(mappedName = "doctorBean")
public class DoctorBean extends BaseBean implements DoctorBeanI {

    @Override
    public Patient diagnose(Patient patient) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Patient prescribe(Patient patient) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
