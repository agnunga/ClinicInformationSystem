package com.agunga.beans;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.agunga.dao.DbType;
import com.agunga.dao.DbUtil;

/**
 * Created by agunga on 1/18/17.
 */
public class Person {
	public static Connection connection = null;
	private String nationalId;
	private String name;
	private String phone;
	private String sex;
	private String dob;

	public String getNationalId() {
		return nationalId;
	}

	public void setNationalId(String nationalId) {
		this.nationalId = nationalId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public void setPersonDetails(String id, String name, String phone, String sex, String dob){
		setNationalId(id);
		setName(name);
		setPhone(phone);
		setSex(sex);
		setDob(dob);
	}

	public Person() {
		Scanner scanner = new Scanner(System.in);
		/*
//Using Scanner to take the patients details as a person.
        System.out.print("Enter the com.agunga.cis.Person's National ID No: ");
        setNationalId(scanner.nextInt());

        System.out.print("Enter the com.agunga.cis.Person's Name: ");
        setName(scanner.next());

        System.out.print("Enter the com.agunga.cis.Person's Phone Number: ");
        setPhone(scanner.next());

        System.out.print("Enter the com.agunga.cis.Person's sex: ");
        setSex(scanner.next());

        System.out.print("Enter the com.agunga.cis.Person's Date of birth: ");
        setDob(scanner.next());
		 */
	}

	public boolean checkPerson(String nationalId){
		boolean exists= false;
		String sql_select = "SELECT " +
				" nationalid, name " +
				" FROM persons" +
				" WHERE nationalid = "+nationalId+"";

		connection = DbUtil.connectDB(DbType.MYSQL);
		ResultSet resultSet = DbUtil.select(sql_select);
		try {
			while(resultSet.next()){
				exists = true;
				setNationalId(resultSet.getString(1));
				setName(resultSet.getString(2));
				System.out.println();
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return exists;
	}

	public boolean registerPerson(Person person){
		boolean added = false;
		connection = DbUtil.connectDB(DbType.MYSQL);

		if(checkPerson(person.getNationalId())){
			System.out.print("Person exists. ");
		}else {

			String sql_insert_person = "INSERT INTO persons "
					+ "(nationalid, name, dob, phone, sex) "
					+ "VALUES "
					+ "(?, ?, ?, ?, ?)";

			PreparedStatement preparedStatement = null;
			try {
				preparedStatement = connection.prepareStatement(sql_insert_person);
				preparedStatement.setString(1, person.getNationalId());
				preparedStatement.setString(2, person.getName());
				preparedStatement.setString(3, person.getDob());
				preparedStatement.setString(4, person.getPhone());
				preparedStatement.setString(5, person.getSex());

				if (DbUtil.insert(preparedStatement) > 0){
					System.out.print("Person registered. ");
					added =  true;
				}else{
					System.err.print("Person registration failed. ");
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return added;
	}

	public String getPersonDetails(){
		return String.format("ID: %d, Name: %s, Pnone: %s, Sex: %s", getNationalId(), getName(), getPhone(), getSex(), getDob());
	}
}