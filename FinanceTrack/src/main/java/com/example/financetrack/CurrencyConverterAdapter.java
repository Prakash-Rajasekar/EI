package com.example.financetrack;

// Target interface
interface CurrencyConverter {
    double convertToUSD(double amount);
    double convertToEUR(double amount);
    double convertFromUSD(double amount);
    double convertFromEUR(double amount);
}

// Adapter class
public class CurrencyConverterAdapter implements CurrencyConverter {

    // Example conversion rates
    private static final double INR_TO_USD_RATE = 0.012; // 1 INR = 0.012 USD
    private static final double INR_TO_EUR_RATE = 0.011; // 1 INR = 0.011 EUR
    private static final double USD_TO_INR_RATE = 83.33; // 1 USD = 83.33 INR
    private static final double EUR_TO_INR_RATE = 91.00; // 1 EUR = 91.00 INR
    private static final double USD_TO_EUR_RATE = 0.85;  // 1 USD = 0.85 EUR
    private static final double EUR_TO_USD_RATE = 1.18;  // 1 EUR = 1.18 USD

    @Override
    public double convertToUSD(double amount) {
        return amount * INR_TO_USD_RATE;
    }

    @Override
    public double convertToEUR(double amount) {
        return amount * INR_TO_EUR_RATE;
    }

    @Override
    public double convertFromUSD(double amount) {
        return amount * USD_TO_INR_RATE;
    }

    @Override
    public double convertFromEUR(double amount) {
        double amountInUSD = amount * EUR_TO_USD_RATE;
        return convertFromUSD(amountInUSD);
    }
}
