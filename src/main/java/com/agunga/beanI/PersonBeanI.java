package com.agunga.beanI;

import com.agunga.model.Person;
import javax.ejb.Local;

@Local
public interface PersonBeanI {

    Person findById(long id);

    Person add(Person person);

}
