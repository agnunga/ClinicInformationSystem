/**
 * check cashing
 */
package com.agunga.accounts;

/**
 *
 * @author agunga
 */
public class BusinessAccount extends Account {

//    private String businessName;
    private String businessPIN;
    private String businessLocation;
    private double monthlyFees;
    private double transactionFee;

    public String getBusinessPIN() {
        return businessPIN;
    }

    public void setBusinessPIN(String businessPIN) {
        this.businessPIN = businessPIN;
    }

    public String getBusinessLocation() {
        return businessLocation;
    }

    public void setBusinessLocation(String businessLocation) {
        this.businessLocation = businessLocation;
    }

    public double getMonthlyFees() {
        return monthlyFees;
    }

    public void setMonthlyFees(double monthlyFees) {
        this.monthlyFees = monthlyFees;
    }

    private void setupAutomaticPayments() {

    }

    public double getTransactionFee() {
        return transactionFee;
    }

    public void setTransactionFee(double transactionFee) {
        this.transactionFee = transactionFee;
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

        return getAmount();
    }
 

}
