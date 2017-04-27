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
//@Table(name = "administrators")
public class Administrator extends Employee implements Serializable {

    private String adminId;

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

}
