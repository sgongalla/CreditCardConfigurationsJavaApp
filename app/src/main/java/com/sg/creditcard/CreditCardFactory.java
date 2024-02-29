package com.sg.creditcard;

import java.util.EnumSet;

public class CreditCardFactory {
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

    // Initialize with default values
    public CreditCardFactory() {
        this.creditLimit = 1000.0;
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

    public CreditCardFactory withCreditLimit(double creditLimit) {
        this.creditLimit = creditLimit;
        return this;
    }

    public CreditCardFactory withNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

    public CreditCardFactory withLatePaymentFee(double latePaymentFee) {
        this.latePaymentFee = latePaymentFee;
        return this;
    }

    public CreditCardFactory withAllowedTransactions(EnumSet<TransactionType> allowedTransactions) {
        this.allowedTransactions = allowedTransactions;
        return this;
    }

    public CreditCardFactory withBalanceTransferEnabled(boolean balanceTransferEnabled) {
        this.balanceTransferEnabled = balanceTransferEnabled;
        return this;
    }

    public CreditCardFactory withBalanceTransferFee(double balanceTransferFee) {
        this.balanceTransferFee = balanceTransferFee;
        return this;
    }

    public CreditCardFactory withSecurityDeposit(double securityDeposit) {
        this.securityDeposit = securityDeposit;
        return this;
    }

    public CreditCardFactory withIntroAPR(double introAPR) {
        this.introAPR = introAPR;
        return this;
    }

    public CreditCardFactory withIntroAPRDurationMonths(int introAPRDuration) {
        this.introAPRDuration = introAPRDuration;
        return this;
    }

    public CreditCardFactory withCashAdvanceFee(double cashAdvanceFee) {
        this.cashAdvanceFee = cashAdvanceFee;
        return this;
    }

    public CreditCardFactory withCurrentBalance(double currentBalance) {
        this.currentBalance = currentBalance;
        return this;
    }

    public CreditCard build() {
        CreditCard card = new CreditCard();
        card.setCreditLimit(creditLimit);
        card.setNickname(nickname);
        card.setLatePaymentFee(latePaymentFee);
        card.setAllowedTransactions(allowedTransactions);
        card.setBalanceTransferEnabled(balanceTransferEnabled);
        card.setBalanceTransferFee(balanceTransferFee);
        card.setSecurityDeposit(securityDeposit);
        card.setIntroAPR(introAPR);
        card.setIntroAPRDuration(introAPRDuration);
        card.setCashAdvanceFee(cashAdvanceFee);
        card.setCurrentBalance(currentBalance);
        return card;
    }
}
