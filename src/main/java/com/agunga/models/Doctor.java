package com.agunga.models;

/**
 * Created by agunga on 1/18/17.
 */
public class Doctor extends Employee {

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
