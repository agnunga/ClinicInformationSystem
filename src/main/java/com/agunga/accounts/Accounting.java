/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agunga.accounts;

import java.sql.Connection;
import java.util.Scanner;

/**
 *
 * @author agunga
 */
public class Accounting {

    public static Scanner scanner = new Scanner(System.in);
    public static Connection conn = new MysqlDbUtil().connectDB();

    public static void main(String args[]) {
//        MyUtility.myPrintln("PERSON EXIST T/F: "+new Receptionist().personExists(142536));

        if (conn != null) {
            System.out.println(" Welcome");
            controlFlow();
        } else {
            System.out.println(" Welcome. Sorry, No DB connection");
        }
    }

    public static void controlFlow() {
        String x = checkForOption();
        promptOption(x);
    }

    public static String checkForOption() {
        System.out.println("Main menu");
        System.out.println(" 1. Open New account \n 2. Already have account \n 0. to exit");
        String choice = scanner.next();
        return choice;
    }

    public static String openAccountOption() {
        System.out.println("Choose Account to open");
        System.out.println(" 1. Checking Account \n 2. Business Account  \n 3. Savings Account \n 0. to exit");

        String choice = scanner.next();
        return choice;
    }

    public static String transactOption() {
        System.out.println("Choose menu");
        System.out.println(" 1. Deposit  \n 2. Withdraw \n 3. Check Balance \n 0. to exit");

        String choice = scanner.next();
        return choice;
    }

    public static void promptOption(String role) {

        backAndMenu(role);

        switch (role) {
            case "1": {
                String type = openAccountOption();
                switch (type) {
                    case "1": {
                        CheckingAccount ca = new CheckingAccount();
                        ca.open();
                    }
                    case "2": {

                        break;
                    }
                    case "3": {

                        break;
                    }
                    default: {

                        System.out.println("Invalid option");
                        break;
                    }
                }
                break;
            }

            case "2": {
                System.out.println("Transact");
                String type = transactOption();
                switch (type) {
                    case "1": {
                        CheckingAccount ca = new CheckingAccount();
                        ca.deposit();
                        break;
                    }
                    case "2": {
                        CheckingAccount ca = new CheckingAccount();
                        ca.withdraw();
                        break;
                    }
                    case "3": {
                        CheckingAccount ca = new CheckingAccount();
                        ca.checkBalance();
                        break;
                    }
                    default: {
                        System.out.println("Invalid option");
                        break;
                    }
                }
                break;
            }
            default: {
                System.out.println("Invalid option. Try again");
                controlFlow();
            }
        }
    }

    private static void backAndMenu(String task) {
        switch (task) {
            case "9": {
                controlFlow();
                break;
            }
            case "0": {
                System.out.println("Exiting...");
                System.exit(0);
                break;
            }
        }
    }

    private static void openAccount() {

    }

    private static void chooseTransaction() {

    }

}
