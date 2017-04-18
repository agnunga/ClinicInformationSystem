package com.agunga.bean;

import com.agunga.model.Patient;
import com.agunga.model.Receptionist;
import com.agunga.beanI.EmployeeBeanI;
import com.agunga.beanI.PatientBeanI;
import com.agunga.beanI.PersonBeanI;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.ejb.Stateless;
import com.agunga.beanI.ReceptionistBeanI;
import com.agunga.dao.ConnectionType;
import com.agunga.dao.MyConectivity;
import java.sql.Connection;
import java.util.ArrayList;
import javax.ejb.EJB;
import javax.inject.Inject;

/**
 * Created by agunga on 1/18/17.
 */
@Stateless(mappedName = "receptionistBean")
public class ReceptionistBean extends BaseBean implements ReceptionistBeanI {

   
    @Override
    public boolean check(Receptionist receptionist) {

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

    @Override
    public boolean add(Receptionist receptionist) {
        boolean isregistered = false;
        employeeBean.add(receptionist);
        if (check(receptionist)) {

        } else {
            String sql = "INSERT INTO receptionists "
                    + " (employeeno, assignment, dateassigned) "
                    + " VALUES(?, ?, CURRENT_TIMESTAMP )";
            try {
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setString(1, receptionist.getEmployeeNo());
                preparedStatement.setString(2, receptionist.getAssignment());
                if (mcon.insert(preparedStatement, conn) > 0) {
                    isregistered = true;
                } else {
                    System.err.print("Failed to add receptionist. ");
                }
            } catch (SQLException e) {
                System.out.println("Error " + e.getMessage());
            }
        }
        return isregistered;
    }

    @Override
    public boolean addPatient(Patient patient) {
        return patientBean.add(patient);
    }

    /**
     *
     * @param patientId
     * @return
     */
    @Override
    public ArrayList<Patient> viewPatient(String patientId) {
        return patientBean.view(patientId);
    }

    @Override
    public boolean updatePatient(Patient patient) {
        return patientBean.update(patient);
    }

    @Override
    public boolean deletePatient(String id) {
        return patientBean.delete(id);
    }
}
