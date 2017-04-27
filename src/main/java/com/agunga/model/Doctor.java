package com.agunga.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

/**
 * Created by agunga on 1/18/17.
 */
//@Entity
//@Inheritance(strategy = InheritanceType.JOINED)
//@Table(name = "doctors")
public class Doctor extends Employee implements Serializable {

    private String licence_no;
    private String speciality;

    public String getLicence_no() {
        return licence_no;
    }

    public void setLicence_no(String licence_no) {
        this.licence_no = licence_no;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }
}
