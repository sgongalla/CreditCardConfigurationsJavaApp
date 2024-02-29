package com.sg.creditcard;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public class CreditCardTemplates {
        public static List<CreditCard> generateTemplateCards() {
                List<CreditCard> cards = new ArrayList<>();

                // Default
                cards.add(new CreditCardFactory().build());

                // Travel Rewards, Credit Limit
                cards.add(new CreditCardFactory()
                                .withNickname("Travel Card")
                                .withCreditLimit(5000.0)
                                .withAllowedTransactions(EnumSet.allOf(TransactionType.class))
                                .build());

                // Nickname, Custom Fees
                cards.add(new CreditCardFactory()
                                .withNickname("Personal Card")
                                .withLatePaymentFee(25.33)
                                .withAllowedTransactions(
                                                EnumSet.of(TransactionType.PURCHASE, TransactionType.FEE))
                                .build());

                // Add-On Card
                cards.add(new CreditCardFactory()
                                .withNickname("Add-On Card")
                                .withCreditLimit(250.0)
                                .withSecurityDeposit(250.0) // Matching security deposit
                                .build());

                // Balance Transfer Card
                cards.add(new CreditCardFactory()
                                .withNickname("Balance Transfer Card")
                                .withBalanceTransferEnabled(true)
                                .withBalanceTransferFee(0.03) // 3% balance transfer fee
                                .build());

                // API Card
                cards.add(new CreditCardFactory()
                                .withNickname("1% APR Special")
                                .withIntroAPR(0.01) // 1% interest
                                .withIntroAPRDurationMonths(12) // 12 months period
                                .withCreditLimit(3690.99)
                                .build());

                // Cash Advance
                cards.add(new CreditCardFactory()
                                .withNickname("Cash Access")
                                .withAllowedTransactions(EnumSet.of(TransactionType.CASH_ADVANCE)) // Only cash advances
                                .withCashAdvanceFee(0.05) // 5% fee, for example
                                .build());

                return cards;
        }
}