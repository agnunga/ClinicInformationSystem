package com.agunga.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * Created by agunga on 1/18/17.
 */
//@NamedQueries({
//    @NamedQuery(name = "findPatientById", query = "SELECT p FROM Patient p WHERE p.patientId = :pid")
//})
//
//@Entity
//@Inheritance(strategy = InheritanceType.JOINED)
//@Table(name = "patients")
public class Patient extends Person implements Serializable {

//    @Id
//    @GeneratedValue //(strategy = GenerationType.AUTO);
//    private long id; 
    private String patientId;
    private LocalDateTime checkin;
    private LocalDateTime checkout;
    private String weight;
    private String diagnosis;
    private String bloodType;
    private String prescription;
    private String drugs;
    private String testResults;
    private String addedBy;

    public String getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(String addedBy) {
        this.addedBy = addedBy;
    }

    public String getTestResults() {
        return testResults;
    }

    public void setTestResults(String TestResults) {
        this.testResults = TestResults;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public LocalDateTime getCheckin() {
        return checkin;
    }

    public void setCheckin(LocalDateTime checkin) {
        this.checkin = checkin;
    }

    public LocalDateTime getCheckout() {
        return checkout;
    }

    public void setCheckout(LocalDateTime checkout) {
        this.checkout = checkout;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public String getPrescription() {
        return prescription;
    }

    public void setPrescription(String prescription) {
        this.prescription = prescription;
    }

    public String getDrugs() {
        return drugs;
    }

    public void setDrugs(String drugs) {
        this.drugs = drugs;
    }
}
