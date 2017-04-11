package com.agunga.beansI;

import javax.ejb.Local;

/**
 * Created by agunga on 1/18/17.
 */
@Local
public interface DoctorBeanI {

    public void diagnose();

    public void prescribe();
}
