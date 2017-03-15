/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trials.beans;

import java.io.Serializable;

/**
 *
 * @author agunga
 */
public class TryBean implements Serializable{

    public TryBean() {
    }
    
    public double cube(int s) {
        return Math.pow(s, 3);
    }
}
