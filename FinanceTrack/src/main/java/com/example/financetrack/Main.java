package com.example.financetrack;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            // Initialize ExpenseTracker with a user-defined threshold
            double threshold = promptForDouble("Enter the significant expense threshold: ");
            ExpenseTracker expenseTracker = new ExpenseTracker(threshold);

            // Register observers to the ExpenseTracker
            expenseTracker.registerObserver(new EmailNotifier());
            expenseTracker.registerObserver(new SMSNotifier());
            expenseTracker.registerObserver(new InAppNotifier());

            // Initialize UndoableTransactions for command management
            UndoableTransactions undoableTransactions = new UndoableTransactions();

            // Initialize UserSessionManager (Singleton Pattern)
            UserSessionManager sessionManager = UserSessionManager.getInstance();
            String sessionId = promptForString("Enter current user session ID: ");
            sessionManager.setCurrentUserSession(sessionId);

            // Transaction Factory Example
            double incomeAmount = promptForDouble("Enter amount for income transaction: ");
            double expenseAmount = promptForDouble("Enter amount for expense transaction: ");

            Transaction income = TransactionFactory.createTransaction("income", incomeAmount);
            Transaction expense = TransactionFactory.createTransaction("expense", expenseAmount);

            // Process Transactions
            income.process();
            expense.process();

            // Currency Converter Example
            double localAmount = promptForDouble("Enter amount in INR to convert: ");
            CurrencyConverter converter = new CurrencyConverterAdapter();
            double amountInUSD = converter.convertToUSD(localAmount);
            double amountInEUR = converter.convertToEUR(localAmount);
            System.out.println("Amount in USD: $" + amountInUSD);
            System.out.println("Amount in EUR: €" + amountInEUR);

            // Composite Pattern Example
            String transactionName = promptForString("Enter name for single transaction: ");
            double transactionAmount = promptForDouble("Enter amount for single transaction: ");

            TransactionComponent singleTransaction = new SingleTransaction(transactionName, transactionAmount);
            TransactionCategory incomeCategory = new TransactionCategory("Income");
            TransactionCategory expenseCategory = new TransactionCategory("Expenses");

            incomeCategory.addTransaction(singleTransaction);
            expenseCategory.addTransaction(singleTransaction);

            // Display Transactions
            System.out.println("Income Category Transactions:");
            incomeCategory.showTransactionDetails();
            System.out.println("Expense Category Transactions:");
            expenseCategory.showTransactionDetails();

            // Manage Expenses with Undoable Transactions
            Command addExpenseCommand = new AddExpenseCommand(expenseTracker, expenseAmount);
            undoableTransactions.executeCommand(addExpenseCommand);
            undoableTransactions.undoLastCommand(); // Undo the added expense

            // Manage Additional Expenses
            double additionalExpense = promptForDouble("Enter additional expense to add: ");
            expenseTracker.addExpense(additionalExpense);

            // Display Current Expenses
            System.out.println("Total Expenses: $" + expenseTracker.getTotalExpenses());

        } catch (InputMismatchException e) {
            System.err.println("Invalid input. Please enter numerical values where required.");
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }

    private static double promptForDouble(String message) {
        System.out.print(message);
        while (!scanner.hasNextDouble()) {
            System.out.println("Invalid input. Please enter a valid number.");
            scanner.next(); // Clear invalid input
            System.out.print(message);
        }
        double result = scanner.nextDouble();
        scanner.nextLine(); // Clear newline
        return result;
    }

    private static String promptForString(String message) {
        System.out.print(message);
        return scanner.nextLine();
    }
}
