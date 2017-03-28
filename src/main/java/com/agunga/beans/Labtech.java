package com.agunga.beans;

import com.agunga.dao.MysqlDbUtil;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by agunga on 1/18/17.
 */
public class Labtech extends Employee {

    private static final String PERSONS_TABLE = "persons";
    private static final String PATIENTS_TABLE = "patients";

    public void registerLabtech(Connection conn) {
        Labtech labtech = new Labtech();
        registerPerson(labtech, conn);
    }

    public void recordTestResults(Connection conn) {
        Patient patient = new Patient();
        System.out.print("Enter PatientDetail's National ID: ");
        patient.setNationalId(MyUtility.myScanner().next());

        System.out.print("Enter the PatientDetail's Blood type: ");
        patient.setBlood_type(MyUtility.myScanner().next());

        System.out.print("Enter the PatientDetail's weight: ");
        patient.setWeight(MyUtility.myScanner().next());

        String sql_update = "UPDATE " + PATIENTS_TABLE + " "
                + " SET weight = ?, bloodtype = ? "
                + " WHERE nationalid = ?";

        PreparedStatement preparedStatement;
        try {
            preparedStatement = conn.prepareStatement(sql_update);
            preparedStatement.setString(1, patient.getWeight());
            preparedStatement.setString(2, patient.getBlood_type());
            preparedStatement.setString(3, patient.getNationalId());

            if (new MysqlDbUtil().update(sql_update, preparedStatement, conn) > 0) {
                System.out.println("Test results recorded.");
            } else {
                System.out.println("Failed to record test results.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    void work() {

    }

}
