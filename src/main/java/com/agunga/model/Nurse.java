package com.agunga.model;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

/**
 * Created by agunga on 1/18/17.
 */
//@Entity
//@Inheritance(strategy = InheritanceType.JOINED)
//@Table(name = "nurses")
public class Nurse extends Employee {

    private String nurseId;

    public String getNurseId() {
        return nurseId;
    }

    public void setNurseId(String nurseId) {
        this.nurseId = nurseId;
    }

}
