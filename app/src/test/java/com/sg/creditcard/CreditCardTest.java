package com.sg.creditcard;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.sg.creditcard.CreditCard;
import com.sg.creditcard.CreditCardFactory;
import com.sg.creditcard.TransactionType;

import java.util.EnumSet;
import static org.junit.jupiter.api.Assertions.*;

public class CreditCardTest {

        private CreditCard defaultCard;
        private CreditCardFactory cardFactory;

        @BeforeEach
        void setUp() {
                // Initialize default card and card factory before each test
                defaultCard = new CreditCard();
                cardFactory = new CreditCardFactory();
        }

        @Test
        @DisplayName("Default Credit Card Creation")
        // Test to verify the default settings of a newly created credit card
        void testDefaultCreditCardCreation() {

                assertAll("Default properties",
                                () -> assertEquals(2500.0, defaultCard.getCreditLimit(),
                                                "Default credit limit should be 2500.0"),
                                () -> assertEquals("Corporate Card", defaultCard.getNickname(),
                                                "Default nickname should be 'Corporate Card'"),
                                () -> assertEquals(50.0, defaultCard.getLatePaymentFee(),
                                                "Default late payment fee should be 50.0"),
                                () -> assertTrue(
                                                defaultCard.getAllowedTransactions()
                                                                .containsAll(EnumSet.allOf(TransactionType.class)),
                                                "Default should allow all transaction types"),
                                () -> assertFalse(defaultCard.isBalanceTransferEnabled(),
                                                "Default should have balance transfer disabled"),
                                () -> assertEquals(0.0, defaultCard.getBalanceTransferFee(),
                                                "Default balance transfer fee should be 0.0"));
        }

        @Test
        @DisplayName("Credit Card Setter Methods")
        // Test to ensure the setter methods correctly update the card's properties
        void testCreditCardSetters() {

                defaultCard.setCreditLimit(5000.0);
                defaultCard.setNickname("Premium Card");
                defaultCard.setLatePaymentFee(35.0);
                defaultCard.setAllowedTransactions(EnumSet.of(TransactionType.PURCHASE));
                defaultCard.setBalanceTransferEnabled(true);
                defaultCard.setBalanceTransferFee(10.0);
                defaultCard.setSecurityDeposit(100.0);
                defaultCard.setIntroAPR(0.01);
                defaultCard.setIntroAPRDuration(12);
                defaultCard.setCashAdvanceFee(0.05);

                assertAll("Setters",
                                () -> assertEquals(5000.0, defaultCard.getCreditLimit(),
                                                "Credit limit should be set to 5000.0"),
                                () -> assertEquals("Premium Card", defaultCard.getNickname(),
                                                "Nickname should be set to 'Premium Card'"),
                                () -> assertEquals(35.0, defaultCard.getLatePaymentFee(),
                                                "Late payment fee should be set to 35.0"),
                                () -> assertEquals(EnumSet.of(TransactionType.PURCHASE),
                                                defaultCard.getAllowedTransactions(),
                                                "Allowed transactions should be set to PURCHASE only"),
                                () -> assertTrue(defaultCard.isBalanceTransferEnabled(),
                                                "Balance transfer should be enabled"),
                                () -> assertEquals(10.0, defaultCard.getBalanceTransferFee(),
                                                "Balance transfer fee should be set to 10.0"),
                                () -> assertEquals(100.0, defaultCard.getSecurityDeposit(),
                                                "Security deposit should be set to 100.0"),
                                () -> assertEquals(0.01, defaultCard.getIntroAPR(), "Intro APR should be set to 0.01"),
                                () -> assertEquals(12, defaultCard.getIntroAPRDuration(),
                                                "Intro APR duration should be set to 12"),
                                () -> assertEquals(0.05, defaultCard.getCashAdvanceFee(),
                                                "Cash advance fee should be set to 0.05"));
        }

        @Test
        @DisplayName("Credit Card Factory Custom Creation")
        // Test the credit card factory's ability to customize and create a new credit
        // card
        void testCreditCardFactoryCustomCreation() {
                CreditCard travelCard = cardFactory
                                .withNickname("Travel Card")
                                .withCreditLimit(8000.0)
                                .withLatePaymentFee(40.0)
                                .withAllowedTransactions(
                                                EnumSet.of(TransactionType.CASH_ADVANCE, TransactionType.PURCHASE))
                                .withBalanceTransferEnabled(true)
                                .withBalanceTransferFee(15.0)
                                .withIntroAPR(0.0)
                                .withIntroAPRDurationMonths(12)
                                .build();

                assertAll("Custom card creation using factory",
                                () -> assertEquals(8000.0, travelCard.getCreditLimit(),
                                                "Custom credit limit should be 8000.0"),
                                () -> assertEquals("Travel Card", travelCard.getNickname(),
                                                "Custom nickname should be 'Travel Card'"),
                                () -> assertEquals(40.0, travelCard.getLatePaymentFee(),
                                                "Custom late payment fee should be 40.0"),
                                () -> assertEquals(EnumSet.of(TransactionType.CASH_ADVANCE, TransactionType.PURCHASE),
                                                travelCard.getAllowedTransactions(),
                                                "Custom allowed transactions should be CASH_ADVANCE and PURCHASE"),
                                () -> assertTrue(travelCard.isBalanceTransferEnabled(),
                                                "Custom card should have balance transfer enabled"),
                                () -> assertEquals(15.0, travelCard.getBalanceTransferFee(),
                                                "Custom balance transfer fee should be 15.0"),
                                () -> assertEquals(0.0, travelCard.getIntroAPR(), "Intro APR should be set to 0.0"),
                                () -> assertEquals(12, travelCard.getIntroAPRDuration(),
                                                "Intro APR duration should be set to 12 months"));
        }

        @Test
        @DisplayName("Credit Card Factory Default Creation")
        // Test to verify the factory creates a default card when no customizations are
        // applied
        void testCreditCardFactoryDefaultCreation() {

                CreditCard factoryDefaultCard = cardFactory.build();

                assertAll("Factory default card creation",
                                () -> assertEquals(1000.0, factoryDefaultCard.getCreditLimit(),
                                                "Factory default credit limit should be 1000.0"),
                                () -> assertEquals("Corporate Card", factoryDefaultCard.getNickname(),
                                                "Factory default nickname should be 'Corporate Card'"));
        }

        @Test
        @DisplayName("Credit Limit Bounds")
        // Tests to validate the behavior of setting credit limits outside acceptable
        // bounds
        void testCreditLimitBounds() {
                assertAll(
                                () -> assertThrows(IllegalArgumentException.class, () -> defaultCard.setCreditLimit(-1),
                                                "Setting a negative credit limit should throw IllegalArgumentException"),
                                () -> {
                                        defaultCard.setCreditLimit(0);
                                        assertEquals(0.0, defaultCard.getCreditLimit(),
                                                        "Setting credit limit to 0 should be valid");
                                },
                                () -> assertThrows(IllegalArgumentException.class,
                                                () -> defaultCard.setCreditLimit(Double.MAX_VALUE),
                                                "Setting an extremely high credit limit should throw IllegalArgumentException"));
        }

        @ParameterizedTest
        @CsvSource({
                        "1000, 500, 0.03, true", // Successful transfer
                        "2500, 3000, 0.03, false", // Exceeds available credit
                        "500, -100, 0.03, false", // Invalid amount
                        "500, 500, 0.0, true", // Boundary case: Transfer up to the limit
                        "5000, 0, 0.03, false", // Zero transfer amount
                        "500, 500, 1.0, false" // Extremely high fee (handled in method logic)
        })
        // Test to validate various scenarios for balance transfers
        void performBalanceTransferParameterized(double creditLimit, double amount, double fee,
                        boolean expectedResult) {
                CreditCard card = new CreditCard();
                card.setCreditLimit(creditLimit);
                card.setBalanceTransferEnabled(true);
                card.setBalanceTransferFee(fee);

                boolean result = card.performBalanceTransfer(amount);
                assertEquals(expectedResult, result,
                                "The performBalanceTransfer method did not return the expected result.");
        }

        @Test
        // Test to ensure setting a negative balance transfer fee throws an exception
        void performBalanceTransferWithNegativeFeeThrowsException() {
                CreditCard card = new CreditCard();
                card.setCreditLimit(1000);
                card.setBalanceTransferEnabled(true);

                // Directly testing setBalanceTransferFee for exception expectation
                assertThrows(IllegalArgumentException.class, () -> card.setBalanceTransferFee(-0.05),
                                "Expected setBalanceTransferFee to throw IllegalArgumentException for negative fee");
        }

        @Test
        @DisplayName("Validate Negative Fee Settings")
        // Test to ensure negative fees are not allowed
        void testNegativeFees() {
                assertThrows(IllegalArgumentException.class, () -> defaultCard.setLatePaymentFee(-10),
                                "Late payment fee validation failed");
                assertThrows(IllegalArgumentException.class, () -> defaultCard.setBalanceTransferFee(-5),
                                "Balance transfer fee validation failed");
                assertThrows(IllegalArgumentException.class, () -> defaultCard.setCashAdvanceFee(-1),
                                "Cash advance fee validation failed");
        }

        @Test
        @DisplayName("IntroAPR Validation Tests")
        // Test to validate Intro APR settings
        void testIntroAPRValidation() {
                // Testing zero Intro APR
                defaultCard.setIntroAPR(0.0);
                assertEquals(0.0, defaultCard.getIntroAPR(), "Intro APR should be set to 0.0 for zero value");

                // Testing negative Intro APR handling
                Exception negativeAPRException = assertThrows(IllegalArgumentException.class, () -> {
                        defaultCard.setIntroAPR(-1.0);
                }, "Should throw IllegalArgumentException for negative intro APR values");
                assertTrue(negativeAPRException.getMessage().contains("Intro APR cannot be negative"),
                                "Exception message should indicate the problem with negative intro APR");

                // Testing extremely high Intro APR handling
                Exception highAPRException = assertThrows(IllegalArgumentException.class, () -> {
                        defaultCard.setIntroAPR(100.0);
                }, "Should throw IllegalArgumentException for extremely high intro APR values");
                assertTrue(highAPRException.getMessage().contains("Intro APR is unrealistically high"),
                                "Exception message should indicate the problem with high intro APR");
        }

        @Test
        @DisplayName("Invalid Parameter Handling")
        //// Test to ensure that setting invalid parameters is properly handled
        void testInvalidParameterHandling() {
                // Adjustments based on actual implementation details of your CreditCard class
                assertThrows(IllegalArgumentException.class, () -> defaultCard.setNickname(null),
                                "Setting nickname to null should throw IllegalArgumentException");
                assertThrows(IllegalArgumentException.class, () -> defaultCard.setAllowedTransactions(null),
                                "Setting allowed transactions to null should throw IllegalArgumentException");
        }

}
