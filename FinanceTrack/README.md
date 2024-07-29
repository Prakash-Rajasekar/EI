```markdown
# FinanceTrack Console Application

## Overview

The FinanceTrack console application is designed to manage and track financial transactions. It includes features for:

- Handling income and expense transactions
- Converting currency amounts between INR, USD, and EUR
- Managing expenses with undoable transactions
- Using various design patterns such as Singleton, Factory, and Observer

This application is built using Maven and Java and provides a command-line interface for interaction.

## Features

- **Expense Tracking**: Track and manage expenses with a user-defined threshold.
- **Currency Conversion**: Convert amounts between INR, USD, and EUR.
- **Undoable Transactions**: Add and undo expense transactions.
- **Design Patterns**: Utilizes Singleton, Factory, Observer, and Composite design patterns.
- **Transaction Management**: Manage income and expense transactions and view their details.

## Requirements

- Java 11 or higher
- Maven 3.6.0 or higher

## Installation

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/yourusername/financetrack.git
   ```

2. **Navigate to the Project Directory**:
   ```
   cd financetrack
   ```

3. **Build the Project**:
   ```
   mvn clean package
   ```
   This command compiles the code and packages it into a JAR file.

## Usage

1. **Run the Application**:
   ```
   java -jar target/financetrack.jar
   ```

2. **Interact with the Application**:
   - Follow the prompts to enter financial data and perform operations.
   - The application will ask for various inputs such as transaction amounts, session IDs, and conversion amounts.

### Example Interaction

```
Enter the significant expense threshold: 100.0
Enter current user session ID: user123
Enter amount for income transaction: 5000.0
Enter amount for expense transaction: 200.0
Enter amount in local currency to convert to USD: 1000.0
```

## Design Patterns Used

- **Singleton**: Ensures a single instance of `UserSessionManager`.
- **Factory**: Creates different types of transactions using `TransactionFactory`.
- **Observer**: Notifies observers (e.g., `EmailNotifier`, `SMSNotifier`) of significant expense changes.
- **Composite**: Manages individual transactions and categories of transactions using `TransactionComponent` and `TransactionCategory`.

## Troubleshooting

- **Invalid Input**: Ensure that you enter numerical values where required. The application will prompt you to correct invalid inputs.
- **Build Issues**: Ensure that you have the correct version of Java and Maven installed.

## Contributing

If you want to contribute to the project, please fork the repository and submit a pull request with your changes.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

## Contact

For any questions or issues, please contact [prakashrajasekar00@gmail.com](mailto:prakashrajasekar00@gmail.com).
