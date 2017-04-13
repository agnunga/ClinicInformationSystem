/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agunga.accounts;
 
import java.util.Scanner;

/**
 *
 * @author agunga
 */
public abstract class Account {

    public static Scanner scanner = new Scanner(System.in);

    private String accountName;
    private String accountNo;
//    private String dateOpened;
    private double amount;

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
 
    abstract boolean credit(double amount);

    abstract boolean debit(double amount);

    abstract double checkBalance();
}
