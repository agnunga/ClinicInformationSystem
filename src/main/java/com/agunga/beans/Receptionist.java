package com.agunga.cis;

import com.agunga.db.DbType;
import com.agunga.db.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by agunga on 1/18/17.
 */
public class Receptionist extends Employee {
	private static String persons_table = "persons";
	public static Connection connection = null;

	private String assignment;

	public String getAssignment() {
		return assignment;
	}

	public void setAssignment(String assignment) {
		this.assignment = assignment;
	}

	public boolean checkReceptionist(Receptionist receptionist){
		connection = DbUtil.connectDB(DbType.MYSQL);
		boolean exists = false;
		String sql = "SELECT employeeno, assignment " +
				" FROM receptionists " +
				" WHERE employeeno = '"+receptionist.getEmployeeNo()+"'";
		ResultSet resultSet = DbUtil.select(sql);
		try {
			while (resultSet.next()){
				exists = true;
				receptionist.setEmployeeNo(resultSet.getString(1));
				receptionist.setAssignment(resultSet.getString(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return exists;
	}

	public void registerReceptionist(Receptionist receptionist){
		connection = DbUtil.connectDB(DbType.MYSQL);
		registerEmployee(receptionist);
		if(checkReceptionist(receptionist)){

		}else {
			String sql = "INSERT INTO receptionists " +
					" (employeeno, assignment, dateassigned) " +
					" VALUES(?, ?, CURRENT_TIMESTAMP )";
			try {
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1, receptionist.getEmployeeNo());
				preparedStatement.setString(2, receptionist.getAssignment());
				if(DbUtil.insert(preparedStatement)>0){
				}else {
					System.err.print("Failed to add receptionist. ");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public boolean registerPatient(Patient patient){
		boolean registered = false;
		connection = DbUtil.connectDB(DbType.MYSQL);
		registerPerson(patient);

		if(patient.patientExists(patient.getNationalId())){
		}else{
		}

		String sql_insert_patient = "INSERT INTO patients " +
				" (nationalid, patientid, checkin, addedby) " +
				" VALUES " +
				" (?, ?, CURRENT_TIMESTAMP, ?)";

		PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement(sql_insert_patient);
			preparedStatement.setString(1, patient.getNationalId());
			preparedStatement.setString(2, patient.getPatientId());
			preparedStatement.setString(3, "RF4525");

			if(DbUtil.insert(preparedStatement) > 0){
				registered=true;
			}else{
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			if(connection!=null){
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return registered;
	}

	public Patient viewSinglePatientDetails(String id) {
		Patient patient = new Patient();
		String sql_select = "SELECT " +
				" person.nationalid, patient.patientid, person.name, person.phone, person.dob, person.sex, " +
				" patient.checkin, patient.checkout, patient.addedby " +
				" FROM persons person, patients patient " +
				" WHERE patient.nationalid = person.nationalid " +
				" AND patient.nationalid = "+id+ " "+
				" ORDER BY patient.checkin DESC LIMIT 1 ";

		connection = DbUtil.connectDB(DbType.MYSQL);
		ResultSet resultSet = DbUtil.select(sql_select);

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
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(connection!=null){
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(resultSet!=null){
				try {
					resultSet.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return patient;
	}

	public ArrayList<Patient> viewPatientsDetails() {
		ArrayList<Patient> patients= new ArrayList<Patient>();
		String sql_select = "SELECT " +
				" patient.id, " +
				" person.nationalid, patient.patientid, person.name, person.phone, person.dob, person.sex, " +
				" patient.checkin, patient.checkout, patient.addedby " +
				" FROM persons person, patients patient " +
				" WHERE patient.nationalid = person.nationalid " +
				" ORDER BY patient.checkin DESC LIMIT 500 ";

		connection = DbUtil.connectDB(DbType.MYSQL);
		ResultSet resultSet = DbUtil.select(sql_select);

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
				patient.setCheckout(resultSet.getString(9));
				String addedBy = (resultSet.getString(10));

				patients.add(patient);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(connection!=null){
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(resultSet!=null){
				try {
					resultSet.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return patients;
	}

	public boolean updatePatintDetails(Patient patient){
		connection = DbUtil.connectDB(DbType.MYSQL);
		boolean isUpdated = false;
		String sql_update = "UPDATE "+persons_table+" " +
				" SET name = ?, dob = ?, phone = ?, sex= ? " +
				" WHERE nationalid = ?";

		PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement(sql_update);
			preparedStatement.setString(1, patient.getName());
			preparedStatement.setString(2, patient.getDob());
			preparedStatement.setString(3, patient.getPhone());
			preparedStatement.setString(4, patient.getSex());
			preparedStatement.setString(5, patient.getNationalId());

			isUpdated = (DbUtil.update(sql_update, preparedStatement)>= 0);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isUpdated;
	}

	@Override
	void work() {

	}

}
