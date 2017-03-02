/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */                 
package com.agunga.db;

/**
 *
 * @author agunga
 */
public class MainStart {

    public static void main(String[] args) {
        if (DbUtil.createTables()) {
            System.out.println("Agunga--CONNECTED to CIS database");
        } else {
            System.err.println("Agunga--FAILED to connect to CIS database");
        }
    }
}
