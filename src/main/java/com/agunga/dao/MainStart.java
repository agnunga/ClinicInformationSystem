/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agunga.dao;

import java.sql.Connection;
import javax.inject.Inject;

/**
 *
 * @author agunga
 */
public class MainStart {

    @Inject
    @ConnectionType(ConnectionType.Type.MYSQL)
    private MyConectivity mcon;
    private Connection conn = mcon.connectDB();

    public static void main(String[] args) {

    }
}
