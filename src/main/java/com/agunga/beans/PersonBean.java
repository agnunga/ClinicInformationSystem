package com.agunga.beans;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.ejb.Stateless;
import com.agunga.beansI.PersonBeanI;
import com.agunga.models.Person;

/**
 * Created by agunga on 1/18/17.
 */
@Stateless(mappedName = "personBean")
public class PersonBean extends BaseBean implements PersonBeanI {

    @Override
    public boolean check(String nationalId) {
        boolean exists = false;
        String sql_select = "SELECT "
                + " nationalid, name "
                + " FROM persons"
                + " WHERE nationalid = " + nationalId + "";

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
    public boolean add(Person person) {
        boolean added = false;

        if (check(person.getNationalId())) {
            System.out.print("Person exists. ");
        } else {

            String sql_insert_person = "INSERT INTO persons "
                    + "(nationalid, name, dob, phone, sex) "
                    + "VALUES "
                    + "(?, ?, ?, ?, ?)";

            PreparedStatement preparedStatement = null;
            try {
                preparedStatement = conn.prepareStatement(sql_insert_person);
                preparedStatement.setString(1, person.getNationalId());
                preparedStatement.setString(2, person.getName());
                preparedStatement.setString(3, person.getDob());
                preparedStatement.setString(4, person.getPhone());
                preparedStatement.setString(5, person.getSex());

                if (mcon.insert(preparedStatement, conn) > 0) {
                    System.out.print("Person registered. ");
                    added = true;
                } else {
                    System.err.print("Person registration failed. ");
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return added;
    }

}
