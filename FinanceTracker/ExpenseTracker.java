import java.util.ArrayList;
import java.util.List;

interface Observer {
    void update(double amount);
}

class EmailNotifier implements Observer {
    @Override
    public void update(double amount) {
        System.out.println("Email Notification: Significant expense added: $" + amount);
    }
}

class SMSNotifier implements Observer {
    @Override
    public void update(double amount) {
        System.out.println("SMS Notification: Significant expense added: $" + amount);
    }
}

class InAppNotifier implements Observer {
    @Override
    public void update(double amount) {
        System.out.println("In-App Notification: Significant expense added: $" + amount);
    }
}

interface Subject {
    void registerObserver(Observer o);
    void removeObserver(Observer o);
    void notifyObservers();
}

public class ExpenseTracker implements Subject {
    private List<Observer> observers;
    private double lastExpense;
    private double significantExpenseThreshold;
    private double totalExpenses = 0.0;

    public ExpenseTracker(double threshold) {
        observers = new ArrayList<>();
        significantExpenseThreshold = threshold;
    }

    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(lastExpense);
        }
    }

    public void addExpense(double amount) {
        lastExpense = amount;
        totalExpenses += amount;
        if (amount > significantExpenseThreshold) {
            notifyObservers();
        }
    }

    public void removeExpense(double amount) {
        totalExpenses -= amount;
        System.out.println("Removed expense: $" + amount + ". Total expenses: $" + totalExpenses);
    }

    public double getTotalExpenses() {
        return totalExpenses;
    }
}
