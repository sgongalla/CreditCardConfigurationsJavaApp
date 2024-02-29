package com.sg.creditcard;

import java.util.EnumSet;

public class CreditCard {
    private double creditLimit;
    private String nickname;
    private double latePaymentFee;
    private EnumSet<TransactionType> allowedTransactions;
    private boolean balanceTransferEnabled;
    private double balanceTransferFee;
    private double securityDeposit;
    private double introAPR;
    private int introAPRDuration;
    private double cashAdvanceFee;
    private double currentBalance;

    // Constructor with appropriate default values
    public CreditCard() {
        this.creditLimit = 2500.0;
        this.nickname = "Corporate Card";
        this.latePaymentFee = 50.0;
        this.allowedTransactions = EnumSet.allOf(TransactionType.class);
        this.balanceTransferEnabled = false;
        this.balanceTransferFee = 0.0;
        this.securityDeposit = 0.0;
        this.introAPR = 0.0;
        this.introAPRDuration = 0;
        this.cashAdvanceFee = 0.05;
        this.currentBalance = 0.0;
    }

    public boolean performBalanceTransfer(double amount) {
        if (!balanceTransferEnabled) {
            return false;
        }
        if (amount <= 0 || amount > (creditLimit - currentBalance)) {
            return false;
        }
        if (balanceTransferFee < 0) {
            throw new IllegalArgumentException("Balance transfer fee cannot be negative");
        }

        double feeAmount = Math.abs(amount * balanceTransferFee);

        if (feeAmount > amount * 0.10) { // Fee exceeds 10% of the amount
            return false;
        }

        if (currentBalance + amount + feeAmount > creditLimit) {
            return false;
        }

        currentBalance += amount + feeAmount;
        return true;
    }

    // Getters
    public double getCreditLimit() {
        return creditLimit;
    }

    public String getNickname() {
        return nickname;
    }

    public double getLatePaymentFee() {
        return latePaymentFee;
    }

    public EnumSet<TransactionType> getAllowedTransactions() {
        return allowedTransactions;
    }

    public boolean isBalanceTransferEnabled() {
        return balanceTransferEnabled;
    }

    public double getBalanceTransferFee() {
        return balanceTransferFee;
    }

    public double getSecurityDeposit() {
        return securityDeposit;
    }

    public double getIntroAPR() {
        return introAPR;
    }

    public int getIntroAPRDuration() {
        return introAPRDuration;
    }

    public double getCashAdvanceFee() {
        return cashAdvanceFee;
    }

    public double getCurrentBalance() {
        return this.currentBalance;
    }

    // Setters
    public void setCreditLimit(double creditLimit) {
        if (creditLimit < 0)
            throw new IllegalArgumentException("Credit limit cannot be negative");
        double maxCreditLimit = 100000.0; // Example maximum limit, adjust as needed
        if (creditLimit > maxCreditLimit)
            throw new IllegalArgumentException("Credit limit is unrealistically high");
        this.creditLimit = creditLimit;
    }

    public void setNickname(String nickname) {
        if (nickname == null || nickname.trim().isEmpty())
            throw new IllegalArgumentException("Nickname cannot be null or empty");
        this.nickname = nickname;
    }

    public void setLatePaymentFee(double latePaymentFee) {
        if (latePaymentFee < 0)
            throw new IllegalArgumentException("Late payment fee cannot be negative");
        this.latePaymentFee = latePaymentFee;
    }

    public void setAllowedTransactions(EnumSet<TransactionType> allowedTransactions) {
        if (allowedTransactions == null || allowedTransactions.isEmpty())
            throw new IllegalArgumentException("Allowed transactions cannot be null or empty");
        this.allowedTransactions = allowedTransactions;
    }

    public void setBalanceTransferEnabled(boolean balanceTransferEnabled) {
        this.balanceTransferEnabled = balanceTransferEnabled;
    }

    public void setBalanceTransferFee(double balanceTransferFee) {
        if (balanceTransferFee < 0) {
            throw new IllegalArgumentException("Balance transfer fee cannot be negative");
        }
        this.balanceTransferFee = balanceTransferFee;
    }

    public void setSecurityDeposit(double securityDeposit) {
        if (securityDeposit < 0)
            throw new IllegalArgumentException("Security deposit cannot be negative");
        this.securityDeposit = securityDeposit;
    }

    public void setIntroAPR(double introAPR) {
        if (introAPR < 0)
            throw new IllegalArgumentException("Intro APR cannot be negative");
        if (introAPR > 1.0)
            throw new IllegalArgumentException("Intro APR is unrealistically high"); // Assuming APR is a decimal
        this.introAPR = introAPR;
    }

    public void setIntroAPRDuration(int introAPRDuration) {
        if (introAPRDuration < 0)
            throw new IllegalArgumentException("Intro APR duration cannot be negative");
        this.introAPRDuration = introAPRDuration;
    }

    public void setCashAdvanceFee(double cashAdvanceFee) {
        if (cashAdvanceFee < 0)
            throw new IllegalArgumentException("Cash advance fee cannot be negative");
        this.cashAdvanceFee = cashAdvanceFee;
    }

    public void setCurrentBalance(double currentBalance) {
        if (currentBalance < 0)
            throw new IllegalArgumentException("Current balance cannot be negative");
        this.currentBalance = currentBalance;
    }

}
