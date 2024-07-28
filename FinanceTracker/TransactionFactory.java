interface Transaction {
    void process();
}

class Income implements Transaction {
    private double amount;

    public Income(double amount) {
        this.amount = amount;
    }

    @Override
    public void process() {
        System.out.println("Processing income: $" + amount);
    }
}

class Expense implements Transaction {
    private double amount;

    public Expense(double amount) {
        this.amount = amount;
    }

    @Override
    public void process() {
        System.out.println("Processing expense: $" + amount);
    }
}

public class TransactionFactory {
    public static Transaction createTransaction(String type, double amount) {
        if ("income".equalsIgnoreCase(type)) {
            return new Income(amount);
        } else if ("expense".equalsIgnoreCase(type)) {
            return new Expense(amount);
        } else {
            throw new IllegalArgumentException("Unknown transaction type");
        }
    }
}
