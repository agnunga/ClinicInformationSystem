package com.agunga.bean;

import com.agunga.beanI.DoctorBeanI;
import javax.ejb.Stateless;

@Stateless(mappedName = "doctorBean")
public class DoctorBean extends BaseBean implements DoctorBeanI {

    @Override
    public void diagnose() {

    }

    @Override
    public void prescribe() {

    }

}
