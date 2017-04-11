/** number of transactions per month
 * deposit to another a/c
 *
 */
package com.agunga.accounts;

/**
 *
 * @author agunga
 */
public class SavingsAccount extends Account {

//    private String name;
    private String nationalID;
    private double interest;
    private static final double INTEREST_RATE = 7.50 / 100;
    public static final short MAX_MONTHLY_TRANSACTIONS = 4;

    public String getNationalID() {
        return nationalID;
    }

    public void setNationalID(String nationalID) {
        this.nationalID = nationalID;
    }

    public double getInterest() {
        return interest;
    }

    public void setInterest(double interest) {
        this.interest = interest;
    }

    public double calculateCompound(double amount, int currentYear, int years) {
        if (currentYear == years) {
            return amount;
        } else {
            currentYear++;
            return calculateCompound(amount * Math.pow(1 + INTEREST_RATE, 1), currentYear, years);
        }
    }

    @Override
    boolean credit(double amount) {
        boolean isCredited;

        short occurance = 4;
        if (getAmount() > amount && occurance < MAX_MONTHLY_TRANSACTIONS) {
            setAmount(getAmount() - amount);
            isCredited = true;
        } else {
            System.out.println("Insuficient funds or Maximun monthly occurances reached!");
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

        return getAmount();
    }
 
}
