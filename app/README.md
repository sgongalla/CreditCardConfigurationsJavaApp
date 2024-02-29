# Credit Card Configuration and Testing

# Introduction
This Java project implements a customizable credit card system where credit cards can be configured with various attributes such as credit limit, nickname, late payment fee, allowed transaction types, balance transfer capabilities, and fees. The project includes a CreditCard class for creating individual credit card objects with specific configurations, a CreditCardFactory class for generating credit cards with custom settings, and a CreditCardTemplates class for producing predefined card configurations. Additionally, comprehensive JUnit 5 tests validate the allowed configurations and ensure the system's reliability.

## Setup
### Prerequisites
- Java JDK 17
- Gradle 7.x
- IDE - VSCode

### Installation
Clone the repository to your local machine:


### Building the Project
Build the project using Gradle: Run this in the terminal Project's root direcyory `./gradlew clean build`    
If you want to make any changes to gradle you can configure in `build.gradle` in root directory.

## Running Tests
To run all tests, execute the following command in the terminal at the project's root directory: `./gradlew test --tests com.sg.creditcard.CreditCardTest`  (This command runs the CreditCardTest suite, validating various credit card configurations and operational logic.)

## Project Structure
   `/E6/app/src/main/java/com/sg/creditcard/` 
    `com.sg.creditcard` -> Package
    `CreditCard.java`:The core credit card representation with configurable attributes such as credit limit, nickname, and more. Includes methods `performBalanceTransfer` for performing balance transfers and managing card settings  method.
    `CreditCardFactory.java`: Utilizes the Factory design pattern to create `CreditCard` objects with custom configurations. Supports method chaining for setting attributes.
    `CreditCardTemplates.java`: Generates a list of `CreditCard` objects with predefined configurations for different use cases, such as travel rewards or balance transfers.
    `TransactionType.java`: An enum defining possible transaction types allowed on a credit card, such as CASH_ADVANCE, PURCHASE, and FEE.

    ## Code Overview

### Tests
`/E6/app/src/test/java/com/sg/creditcard/` 
- `CreditCardTest`: Contains JUnit 5 tests validating the functionality and configuration constraints of the `CreditCard` class, including parameterized tests for balance transfers and tests for exception handling.

## Test Descriptions

Each test in `CreditCardTest` verifies a specific aspect of the `CreditCard` functionality:

- `testDefaultCreditCardCreation()`: Ensures that a credit card created with default settings has the expected default values.

- `testCreditCardSetters()`: Validates the setter methods of the `CreditCard` class, ensuring they correctly update the card's attributes.

- `testCreditCardFactoryCustomCreation()`: Checks the `CreditCardFactory` class's ability to create custom-configured credit card objects.

- `testCreditCardFactoryDefaultCreation()`: Verifies that the `CreditCardFactory` can produce a credit card with default settings.

- `performBalanceTransferParameterized()`: Uses parameterized tests to verify the logic of performing balance transfers under various conditions.

- `performBalanceTransferWithNegativeFeeThrowsException()`: Confirms that attempting to set a negative balance transfer fee correctly throws an `IllegalArgumentException`.

### Additional Notes

- Exception handling tests ensure that the system robustly handles invalid input, preventing the creation of misconfigured credit cards.

- The parameterized test for balance transfers covers various scenarios, including successful transfers, transfers exceeding the credit limit, and invalid transfer amounts, to ensure comprehensive validation of the balance transfer feature.

### Break-Down Logic
Fee Calculations:
The fee calculation within the performBalanceTransfer method of the CreditCard class is a crucial aspect of the balance transfer functionality. When a balance transfer is requested, the system calculates the fee based on the amount being transferred and the set balance transfer fee rate. Here's how it works:

Fee Calculation Formula: The fee amount is calculated as amount * balanceTransferFee, where amount is the amount of money being transferred, and balanceTransferFee is the percentage rate (expressed as a decimal) for the balance transfer fee.
Example: If a balance transfer of $1,000 is made with a balance transfer fee rate of 3% (0.03), the fee would be $30 (1000 * 0.03).
Validation Safeguards
Several validation checks are performed to ensure that the balance transfer request is valid and does not violate any rules:

Balance Transfer Enabled: The system first checks if the balance transfer feature is enabled for the credit card. If not, the transfer cannot proceed.
Amount Validity: The requested transfer amount must be positive and not exceed the available credit limit minus the current balance. This prevents negative transfers and overdrafts.
Fee Negativity Check: The balance transfer fee rate cannot be negative. This is enforced in the setter method for the balance transfer fee (setBalanceTransferFee), ensuring that only valid fee rates are used.
High Fee Check: While not explicitly included in the provided code snippets, a safeguard could be implemented to prevent excessively high fees (e.g., fees exceeding a certain percentage of the transfer amount), ensuring fairness and preventing errors.
Balance Transfer Process Flow
The balance transfer process follows a structured flow to ensure each transaction is processed correctly:

Check Feature Availability: Confirm that balance transfer is enabled.
Validate Transfer Amount: Ensure the amount is positive and does not exceed the limit.
Calculate Fee: Determine the fee based on the transfer amount and the fee rate.
Check Fee Validity: (If implemented) Verify that the fee does not exceed a predefined threshold to prevent unreasonably high charges.
Check Credit Limit: Ensure that the total of the current balance, the transfer amount, and the fee does not exceed the credit limit.
Update Balance: If all checks pass, the current balance is updated to include both the transfer amount and the calculated fee.
Example Scenario
Let's consider a scenario where a credit card with a $5,000 limit and a current balance of $1,000 requests a balance transfer of $3,000 with a 3% fee:

Enabled Check: Balance transfer is enabled.
Amount Validity: $3,000 is less than the available credit ($4,000), so it's valid.
Fee Calculation: 3% of $3,000 is $90.
Credit Limit Check: The total of the current balance ($1,000), transfer amount ($3,000), and fee ($90) is $4,090, which is within the $5,000 limit.
Update Balance: The new balance would be $4,090.



## Conclusion

This project provides a flexible and testable implementation of a credit card configuration system, demonstrating the application of object-oriented programming principles and software testing practices. The included JUnit tests offer a foundation for ensuring the system's functionality and reliability as it evolves.








