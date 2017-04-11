package com.agunga.beansI;

import com.agunga.models.Person;
import javax.ejb.Local;

@Local
public interface PersonBeanI{

    public boolean check(String nationalId);

    boolean add(Person person);

}
