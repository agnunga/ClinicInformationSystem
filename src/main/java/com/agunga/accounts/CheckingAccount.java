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
 
 
/**
 *
 * @author agunga
 */
public class CheckingAccount extends Account {

 
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
 
        return total;
    }

    public boolean deposit() {
        CheckingAccount ca = new CheckingAccount();

        System.out.println("Enter account Number: ");
        ca.setAccountNo(scanner.next());
        System.out.println("Enter amount");
        ca.setAmount(scanner.nextDouble());

        ca.getNationalID();
 
        return true;
    }

   
    public boolean withdraw() {
        CheckingAccount ca = new CheckingAccount();

        System.out.println("Enter account Number: ");
        ca.setAccountNo(scanner.next());
        System.out.println("Enter amount");
        ca.setAmount(scanner.nextDouble());

        ca.getNationalID();

     
        return true;
    }
}
