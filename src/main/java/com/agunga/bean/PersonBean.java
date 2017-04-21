package com.agunga.bean;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.ejb.Stateless;
import com.agunga.beanI.PersonBeanI;
import com.agunga.model.Person;

/**
 * Created by agunga on 1/18/17.
 */
@Stateless(mappedName = "personBean")
public class PersonBean extends BaseBean implements PersonBeanI {

    @Override
    public Person findById(long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Person add(Person person) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
