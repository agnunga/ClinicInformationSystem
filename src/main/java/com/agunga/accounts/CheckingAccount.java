/**
 * overdrafts
 * relevant fees
 * Certificate Of Deposit, maturity date, fixed interest dates
 * deposit funds, account holders can use ATMs, direct deposit and over-the-counter deposits
 *  write checks or use electronic debit or credit cards
 * set up automatic payments of routine monthly expenses with a one-time setup, and they can also use smartphone apps for making deposits or transfers
 *
 */
package com.agunga.accounts;

import static com.agunga.accounts.Accounting.conn;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/**
 *
 * @author agunga
 */
public class CheckingAccount extends Account {

    public static Connection conn = new MysqlDbUtil().connectDB();

//    private String name;
    private String nationalID;
    private double transactionFee;

    public String getNationalID() {
        return nationalID;
    }

    public void setNationalID(String nationalID) {
        this.nationalID = nationalID;
    }

    public double getTransactionFee() {
        return transactionFee;
    }

    public void setTransactionFee(double amount) {
        if (amount <= 100) {
            this.transactionFee = 0;
        } else if (amount > 100 && amount <= 5000) {
            this.transactionFee = 30;
        } else if (amount > 5000 && amount <= 15000) {
            this.transactionFee = 60;
        } else if (amount > 15000 && amount <= 50000) {
            this.transactionFee = 100;
        } else if (amount > 50000) {
            this.transactionFee = 200;
        }
    }

    @Override
    boolean credit(double amount) {
        boolean isCredited;
        setTransactionFee(amount);
        double amountWithdrawable = getAmount() - getTransactionFee();
        if (amountWithdrawable > amount) {
            setAmount(amountWithdrawable - amount);
            isCredited = true;
        } else {
            System.out.println("Insuficient funds");
            isCredited = false;
        }
        return isCredited;
    }

    @Override
    boolean debit(double amount) {
        boolean isDebited;

        amount += amount;

        isDebited = true;

        return isDebited;
    }

    @Override
    double checkBalance() {
        double total = 0.0;
        CheckingAccount ca = new CheckingAccount();

        System.out.println("Enter account Number: ");
        ca.setAccountNo(scanner.next());

        ca.getNationalID();

        String sql = "SELECT amount, transactionFee FROM `transaction` "
                + " WHERE accountNo = '" + ca.getAccountNo() + "'"
                + " AND type = 'cr'";

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, ca.getAccountNo());

            ResultSet rs = new MysqlDbUtil().select(sql, conn);

        } catch (SQLException e) {
            System.out.println("Eroor" + e.getMessage());
        }
        return total;
    }

    public boolean deposit() {
        CheckingAccount ca = new CheckingAccount();

        System.out.println("Enter account Number: ");
        ca.setAccountNo(scanner.next());
        System.out.println("Enter amount");
        ca.setAmount(scanner.nextDouble());

        ca.getNationalID();

        String sql = "INSERT INTO transaction "
                + "(accountNo, amount, transactionFee, type) "
                + "VALUES "
                + "(?,?,?,?)";

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, ca.getAccountNo());
            preparedStatement.setString(2, ca.getAmount() + "");
            preparedStatement.setString(3, "0");
            preparedStatement.setString(4, "dr");

            if (new MysqlDbUtil().insert(preparedStatement, conn) > 0) {

                System.out.print("Success. ");
            } else {
                System.err.print("failed. ");
            }
        } catch (SQLException e) {
            System.out.println("Eroor" + e.getMessage());
        }
        return true;
    }

    @Override
    boolean open() {
        boolean isOpened = false;

        CheckingAccount ca = new CheckingAccount();

        System.out.println("Enter accountName/Owner name: ");
        ca.setAccountName(scanner.next());
        System.out.println("Enter owner National ID: ");
        ca.setNationalID(scanner.next());
        System.out.println("Enter accountNo: ");
        ca.setAccountNo(scanner.next());
        System.out.println("Enter amount");
        ca.setAmount(scanner.nextDouble());

        ca.getNationalID();

        String sql = "INSERT INTO account "
                + "(accountNo, accountName, amount) "
                + "VALUES "
                + "(?,?,?)";

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, ca.getAccountNo());
            preparedStatement.setString(2, ca.getAccountName());
            preparedStatement.setString(3, ca.getAmount() + "");

            if (new MysqlDbUtil().insert(preparedStatement, conn) > 0) {

                System.out.print("Success. ");
            } else {
                System.err.print("failed. ");
            }
        } catch (SQLException e) {
            System.out.println("Eroor" + e.getMessage());
        }
        return isOpened;
    }

    public boolean withdraw() {
        CheckingAccount ca = new CheckingAccount();

        System.out.println("Enter account Number: ");
        ca.setAccountNo(scanner.next());
        System.out.println("Enter amount");
        ca.setAmount(scanner.nextDouble());

        ca.getNationalID();

        String sql = "INSERT INTO transaction "
                + "(accountNo, amount, transactionFee, type) "
                + "VALUES "
                + "(?,?,?,?)";

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, ca.getAccountNo());
            preparedStatement.setString(2, ca.getAmount() + "");
            ca.setTransactionFee(ca.getAmount());
            preparedStatement.setString(3, ca.getTransactionFee() + "");
            preparedStatement.setString(4, "cr");

            if (new MysqlDbUtil().insert(preparedStatement, conn) > 0) {
                System.out.print("Success. ");
            } else {
                System.err.print("failed. ");
            }
        } catch (SQLException e) {
            System.out.println("Eroor" + e.getMessage());
        }
        return true;
    }
}
