/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agunga.dao;

import java.sql.Connection;

/**
 *
 * @author agunga
 */
public class MainStart {

    public static void main(String[] args) {
        Connection conn = new MysqlDbUtil().connectDB();
        if (new MysqlDbUtil().createTables(conn)) {
            System.out.println("Agunga--CONNECTED to CIS database");
        } else {
            System.err.println("Agunga--FAILED to connect to CIS database");
        }
//        String name = "oloo";
//        int age = 39;
//        
//        Lamb tryLamb = () -> "My name is "+name+" age is "+age;
//        System.out.println("tryLamb: "+tryLamb);

    }
}

//interface Lamb{
//String tryLamb();
//}
