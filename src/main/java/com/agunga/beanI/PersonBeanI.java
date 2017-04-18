package com.agunga.beanI;

import com.agunga.model.Person;
import javax.ejb.Local;

@Local
public interface PersonBeanI{

    public boolean check(String nationalId);

    boolean add(Person person);

}
