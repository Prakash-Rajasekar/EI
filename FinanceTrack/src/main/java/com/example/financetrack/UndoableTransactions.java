package com.example.financetrack;

import java.util.Stack;

interface Command {
    void execute();
    void undo();
}

class AddExpenseCommand implements Command {
    private ExpenseTracker expenseTracker;
    private double amount;

    public AddExpenseCommand(ExpenseTracker expenseTracker, double amount) {
        this.expenseTracker = expenseTracker;
        this.amount = amount;
    }

    @Override
    public void execute() {
        expenseTracker.addExpense(amount);
    }

    @Override
    public void undo() {
        expenseTracker.removeExpense(amount);
    }
}

public class UndoableTransactions {
    private Stack<Command> commandHistory = new Stack<>();

    public void executeCommand(Command command) {
        command.execute();
        commandHistory.push(command);
    }

    public void undoLastCommand() {
        if (!commandHistory.isEmpty()) {
            Command lastCommand = commandHistory.pop();
            lastCommand.undo();
        } else {
            System.out.println("No commands to undo.");
        }
    }
}
