package com.agunga.bean;
 
import com.agunga.beanI.NurseBeanI;
import com.agunga.model.Patient;
import javax.ejb.Stateless;

/**
 * Created by agunga on 1/18/17.
 */
@Stateless(mappedName = "nurseBean")
public class NurseBean extends BaseBean implements NurseBeanI {

    @Override
    public void dispatchDrugs(Patient patient) {

    }
}
