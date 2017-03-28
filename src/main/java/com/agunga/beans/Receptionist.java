package com.agunga.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by agunga on 1/18/17.
 */
public class Receptionist extends Employee {

    private static final String PERSONS_TABLE = "persons";
    private String assignment;

    public String getAssignment() {
        return assignment;
    }

    public void setAssignment(String assignment) {
        this.assignment = assignment;
    }

    public boolean checkReceptionist(Receptionist receptionist, Connection conn) {

        boolean exists = false;
        String sql = "SELECT employeeno, assignment "
                + " FROM receptionists "
                + " WHERE employeeno = '" + receptionist.getEmployeeNo() + "'";
        ResultSet resultSet = mcon.select(sql, conn);
        try {
            while (resultSet.next()) {
                exists = true;
                receptionist.setEmployeeNo(resultSet.getString(1));
                receptionist.setAssignment(resultSet.getString(2));
            }
        } catch (SQLException e) {
            System.out.println("Error " + e.getMessage());
        }
        return exists;
    }

    public void registerReceptionist(Receptionist receptionist, Connection conn) {
        registerEmployee(receptionist, conn);
        if (checkReceptionist(receptionist, conn)) {

        } else {
            String sql = "INSERT INTO receptionists "
                    + " (employeeno, assignment, dateassigned) "
                    + " VALUES(?, ?, CURRENT_TIMESTAMP )";
            try {
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setString(1, receptionist.getEmployeeNo());
                preparedStatement.setString(2, receptionist.getAssignment());
                if (mcon.insert(preparedStatement, conn) > 0) {
                } else {
                    System.err.print("Failed to add receptionist. ");
                }
            } catch (SQLException e) {
                System.out.println("Error " + e.getMessage());
            }
        }
    }

    public boolean registerPatient(Patient patient, Connection conn) {
        boolean registered = false;
        registerPerson(patient, conn);

        if (patient.patientExists(patient.getNationalId(), conn)) {
        } else {
        }

        String sql_insert_patient = "INSERT INTO patients "
                + " (nationalid, patientid, checkin, addedby) "
                + " VALUES "
                + " (?, ?, CURRENT_TIMESTAMP, ?)";

        PreparedStatement preparedStatement;
        try {
            preparedStatement = conn.prepareStatement(sql_insert_patient);
            preparedStatement.setString(1, patient.getNationalId());
            preparedStatement.setString(2, patient.getPatientId());
            preparedStatement.setString(3, "RF4525");

            if (mcon.insert(preparedStatement, conn) > 0) {
                registered = true;
            } else {
            }

        } catch (SQLException e) {
            System.out.println("Error " + e.getMessage());
        }
        return registered;
    }

    public Patient viewSinglePatientDetails(String id, Connection conn) {
        Patient patient = new Patient();
        String sql_select = "SELECT "
                + " person.nationalid, patient.patientid, person.name, person.phone, person.dob, person.sex, "
                + " patient.checkin, patient.checkout, patient.addedby "
                + " FROM persons person, patients patient "
                + " WHERE patient.nationalid = person.nationalid "
                + " AND patient.nationalid = " + id + " "
                + " ORDER BY patient.checkin DESC LIMIT 1 ";

        ResultSet resultSet = mcon.select(sql_select, conn);

        try {
            if (resultSet.next()) {
                patient.setNationalId(resultSet.getString(1));
                patient.setPatientId(resultSet.getString(2));
                patient.setName(resultSet.getString(3));
                patient.setPhone(resultSet.getString(4));
                patient.setDob(resultSet.getString(5));
                patient.setSex(resultSet.getString(6));
                patient.setCheckin(resultSet.getString(7));
                patient.setCheckout(resultSet.getString(8));
                String addedBy = (resultSet.getString(9));
            }
        } catch (SQLException e) {
            System.out.println("Error " + e.getMessage());
        }
        return patient;
    }

    public ArrayList<Patient> viewPatientsDetails(Connection conn) {

        if (mcon != null) {
            System.out.println("IN viewPatientsDetails SUCCESS");
        } else {
            System.out.println("IN viewPatientsDetails NULL XXXX");

        }
        ArrayList<Patient> patients = new ArrayList<>();
        String sql_select = "SELECT "
                + " id, "
                + " persons.nationalid, patientid, name, phone, dob, sex, "
                + " checkin, checkout, addedby "
                + " FROM persons INNER JOIN patients "
                + " WHERE patients.nationalid = persons.nationalid ;";
//                + " ORDER BY patient.checkin DESC LIMIT 500 ";

        ResultSet resultSet = mcon.select(sql_select, conn);

        try {
            while (resultSet.next()) {
                Patient patient = new Patient();
                resultSet.getInt(1);
                patient.setNationalId(resultSet.getString(2));
                patient.setPatientId(resultSet.getString(3));
                patient.setName(resultSet.getString(4));
                patient.setPhone(resultSet.getString(5));
                patient.setDob(resultSet.getString(6));
                patient.setSex(resultSet.getString(7));
                patient.setCheckin(resultSet.getString(8));
//                patient.setCheckout(resultSet.getString(9));
                String addedBy = (resultSet.getString(10));

                patients.add(patient);
            }
        } catch (SQLException e) {
            System.out.println("Error " + e.getMessage());
        }
        return patients;
    }

    public boolean updatePatintDetails(Patient patient, Connection conn) {
        boolean isUpdated = false;
        String sql_update = "UPDATE " + PERSONS_TABLE + " "
                + " SET name = ?, dob = ?, phone = ?, sex= ? "
                + " WHERE nationalid = ?";

        PreparedStatement preparedStatement;
        try {
            preparedStatement = conn.prepareStatement(sql_update);
            preparedStatement.setString(1, patient.getName());
            preparedStatement.setString(2, patient.getDob());
            preparedStatement.setString(3, patient.getPhone());
            preparedStatement.setString(4, patient.getSex());
            preparedStatement.setString(5, patient.getNationalId());

            isUpdated = (mcon.update(sql_update, preparedStatement, conn) >= 0);

        } catch (SQLException e) {
            System.out.println("Error " + e.getMessage());
        }
        return isUpdated;
    }

    public boolean deletePatientDetails(Patient patient, int id, Connection conn) {
        boolean isDeleted = false;
        String sql_update = "DELETE FROM " + PERSONS_TABLE + " "
                + " WHERE id = ?";

        PreparedStatement preparedStatement;
        try {
            preparedStatement = conn.prepareStatement(sql_update);
            preparedStatement.setInt(1, id);

            isDeleted = (mcon.update(sql_update, preparedStatement, conn) >= 0);
        } catch (SQLException e) {
            System.out.println("Error " + e.getMessage());
        }
        return isDeleted;
    }

    @Override
    void work() {

    }

}
