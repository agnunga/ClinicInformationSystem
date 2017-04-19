/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agunga.util;

/**
 *
 * @author agunga
 * @param <T>
 */
public class JLogger<T> {

    public JLogger(T t) {
        System.out.println("Logged: " + t.toString());
    }

    public void e(T e) {
        System.out.println("Logged: " + e);
    }

}
