package com.agunga.model;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

/**
 * Created by agunga on 1/18/17.
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "labtechs")
public class Labtech extends Employee {

    private String LabtechId;

    public String getLabtechId() {
        return LabtechId;
    }

    public void setLabtechId(String LabtechId) {
        this.LabtechId = LabtechId;
    }

}
