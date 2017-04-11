package com.agunga.beans;

import com.agunga.models.Patient;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.ejb.Stateless;
import com.agunga.beansI.PatientBeanI;
import java.sql.PreparedStatement;
import java.util.ArrayList;

@Stateless(mappedName = "patientBean")
public class PatientBean extends BaseBean implements PatientBeanI {

    @Override
    public boolean check(String nationalId) {
        boolean exists = false;
        String sql_select = "SELECT "
                + " patients.nationalid, patients.patientid, persons.name, patients.checkin "
                + " FROM patients JOIN persons"
                + " ON patients.nationalid = persons.nationalid"
                + " WHERE patients.nationalid = " + nationalId + " ORDER BY patients.checkin DESC LIMIT 1";

        ResultSet resultSet = mcon.select(sql_select, conn);
        try {
            while (resultSet.next()) {
                exists = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exists;
    }

    @Override
    public boolean add(Patient patient) {
        boolean registered = false;
        personBean.add(patient);

        if (check(patient.getNationalId())) {
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

    @Override
    public ArrayList<Patient> view(String patientId) {
        String andClause = "";
        if (!"".equals(patientId)) {
            andClause = "AND patients.nationalid = '" + patientId + "'";
        }

        ArrayList<Patient> patients = new ArrayList<>();
        String sql_select = "SELECT "
                + " id, "
                + " persons.nationalid, patientid, name, phone, dob, sex, "
                + " checkin, checkout, addedby "
                + " FROM persons INNER JOIN patients "
                + " WHERE patients.nationalid = persons.nationalid "
                + andClause + ";";
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

    @Override
    public boolean update(Patient patient) {
        boolean isUpdated = false;
        String sql_update = "UPDATE persons "
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

            isUpdated = (mcon.update(preparedStatement, conn) >= 0);

        } catch (SQLException e) {
            System.out.println("Error " + e.getMessage());
        }
        return isUpdated;
    }

    @Override
    public boolean delete(String id) {
        boolean isDeleted = false;
        String sql_update = "DELETE FROM persons "
                + " WHERE id = ?";

        PreparedStatement preparedStatement;
        try {
            preparedStatement = conn.prepareStatement(sql_update);
            preparedStatement.setString(1, id);

            isDeleted = (mcon.update(preparedStatement, conn) >= 0);
        } catch (SQLException e) {
            System.out.println("Error " + e.getMessage());
        }
        return isDeleted;
    }

    @Override
    public void diagnose(Patient patient) {

        String sql_update = "UPDATE patients "
                + " SET diagnosis = ? "
                + " WHERE nationalid = ?";

        PreparedStatement preparedStatement;
        try {
            preparedStatement = conn.prepareStatement(sql_update);
            preparedStatement.setString(1, patient.getDiagnosis());
            preparedStatement.setString(2, patient.getNationalId());

            if (mcon.update(preparedStatement, conn) > 0) {
                System.out.println("Diagnosis recorded.");
            } else {
                System.out.println("Failed to record diagnosis.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void test(Patient patient) {
        String sql_update = "UPDATE patients "
                + " SET weight = ?, bloodtype = ? "
                + " WHERE nationalid = ?";

        PreparedStatement preparedStatement;
        try {
            preparedStatement = conn.prepareStatement(sql_update);
            preparedStatement.setString(1, patient.getWeight());
            preparedStatement.setString(2, patient.getBlood_type());
            preparedStatement.setString(3, patient.getNationalId());

            if (mcon.update(preparedStatement, conn) > 0) {
                System.out.println("Test results recorded.");
            } else {
                System.out.println("Failed to record test results.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void prescribe(Patient patient) {
        String sql_update = "UPDATE patients "
                + " SET prescription = ? "
                + " WHERE nationalid = ?";

        PreparedStatement preparedStatement;
        try {
            preparedStatement = conn.prepareStatement(sql_update);
            preparedStatement.setString(1, patient.getDiagnosis());
            preparedStatement.setString(2, patient.getNationalId());

            if (mcon.update(preparedStatement, conn) > 0) {
                System.out.println("Prescription recorded.");
            } else {
                System.out.println("Failed to record prescription.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dispatchDrugs(Patient patient) {
        String sql_update = "UPDATE patients "
                + " SET drugs = ?, checkout = CURRENT_TIMESTAMP "
                + " WHERE nationalid = ?";

        PreparedStatement preparedStatement;
        try {
            preparedStatement = conn.prepareStatement(sql_update);
            preparedStatement.setString(1, patient.getDiagnosis());
            preparedStatement.setString(2, patient.getNationalId());

            if (mcon.update(preparedStatement, conn) > 0) {
                System.out.println("Dispatched drugs recorded.");
            } else {
                System.out.println("Failed to record dispatched drugs.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
