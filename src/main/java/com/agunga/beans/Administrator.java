package com.agunga.cis;

import java.sql.Connection;

/**
 * Created by agunga on 1/18/17.
 */
public class Administrator extends Employee {
	public static Connection connection = null;

	private String adminId;

	public String getAdminId() {
		return adminId;
	}

	public void registerSpecificEmployee(Employee employee){
		
	}

	@Override
	void work() {
		// TODO Auto-generated method stub

	}
}
