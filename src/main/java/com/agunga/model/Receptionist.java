package com.agunga.model;
 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.ejb.Stateless;

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

}
