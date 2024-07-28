public class Main {
    public static void main(String[] args) {
        // Observer Pattern
        ExpenseTracker expenseTracker = new ExpenseTracker(1000.0);
        Observer emailNotifier = new EmailNotifier();
        Observer smsNotifier = new SMSNotifier();
        Observer inAppNotifier = new InAppNotifier();
        
        expenseTracker.registerObserver(emailNotifier);
        expenseTracker.registerObserver(smsNotifier);
        expenseTracker.registerObserver(inAppNotifier);
        
        expenseTracker.addExpense(500.0);  // No notification
        expenseTracker.addExpense(1500.0); // Notification sent to all observers

        // Command Pattern
        UndoableTransactions undoableTransactions = new UndoableTransactions();
        Command addExpense1 = new AddExpenseCommand(expenseTracker, 500.0);
        Command addExpense2 = new AddExpenseCommand(expenseTracker, 300.0);

        undoableTransactions.executeCommand(addExpense1);
        undoableTransactions.executeCommand(addExpense2);
        undoableTransactions.undoLastCommand(); // Undo last expense
        undoableTransactions.undoLastCommand(); // Undo last expense

        // Singleton Pattern
        UserSessionManager sessionManager1 = UserSessionManager.getInstance();
        UserSessionManager sessionManager2 = UserSessionManager.getInstance();
        System.out.println(sessionManager1 == sessionManager2); // Output: true

        // Factory Pattern
        Transaction income = TransactionFactory.createTransaction("income", 1000.0);
        Transaction expense = TransactionFactory.createTransaction("expense", 500.0);
        income.process(); // Output: Processing income: $1000.0
        expense.process(); // Output: Processing expense: $500.0

        // Adapter Pattern
        CurrencyConverter converter = new CurrencyConverterAdapter();
        double amountInUSD = converter.convertToUSD(100.0);
        double amountInEUR = converter.convertFromUSD(amountInUSD);
        System.out.println("Amount in USD: $" + amountInUSD);
        System.out.println("Amount in EUR: €" + amountInEUR);

        // Composite Pattern
        TransactionComponent salary = new SingleTransaction("Salary", 3000.0);
        TransactionComponent grocery = new SingleTransaction("Groceries", 200.0);
        
        TransactionCategory incomeCategory = new TransactionCategory("Income");
        TransactionCategory expenseCategory = new TransactionCategory("Expenses");
        
        incomeCategory.addTransaction(salary);
        expenseCategory.addTransaction(grocery);
        
        incomeCategory.showTransactionDetails();
        expenseCategory.showTransactionDetails();
    }
}
