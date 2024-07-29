package com.example.financetrack;

import java.util.ArrayList;
import java.util.List;

interface TransactionComponent {
    void showTransactionDetails();
}

class SingleTransaction implements TransactionComponent {
    private String name;
    private double amount;

    public SingleTransaction(String name, double amount) {
        this.name = name;
        this.amount = amount;
    }

    @Override
    public void showTransactionDetails() {
        System.out.println(name + ": $" + amount);
    }
}

class TransactionCategory implements TransactionComponent {
    private String categoryName;
    private List<TransactionComponent> transactions = new ArrayList<>();

    public TransactionCategory(String categoryName) {
        this.categoryName = categoryName;
    }

    public void addTransaction(TransactionComponent transaction) {
        transactions.add(transaction);
    }

    @Override
    public void showTransactionDetails() {
        System.out.println("Category: " + categoryName);
        for (TransactionComponent transaction : transactions) {
            transaction.showTransactionDetails();
        }
    }
}
