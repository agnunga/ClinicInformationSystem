package com.agunga.beans;



import com.agunga.dao.MysqlDbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by agunga on 1/18/17.
 */
public class Nurse extends Employee {
    private static String persons_table = "persons";
    private static String patients_table = "patients";
    public static Connection connection = null;

    @Override
    void work() {

    }

    public void dispatchDrugs(Connection conn){
        Patient patient = new Patient();
        System.out.print("Enter patient's National ID: ");
        patient.setNationalId(MyUtility.myScanner().next());

        System.out.print("Enter the drugs dispatched to patient: ");
        patient.setDiagnosis(MyUtility.myScanner().nextLine());

        String sql_update = "UPDATE "+patients_table+" " +
                " SET drugs = ?, checkout = CURRENT_TIMESTAMP " +
                " WHERE nationalid = ?";

        PreparedStatement preparedStatement;
        try {
            preparedStatement = conn.prepareStatement(sql_update);
            preparedStatement.setString(1, patient.getDiagnosis());
            preparedStatement.setString(2, patient.getNationalId());

            if(new MysqlDbUtil().update(sql_update, preparedStatement, conn)>0)System.out.println("Dispatched drugs recorded.");
            else System.out.println("Failed to record dispatched drugs.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
