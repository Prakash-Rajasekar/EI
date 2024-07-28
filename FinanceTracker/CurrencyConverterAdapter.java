// Existing incompatible interface
class ThirdPartyCurrencyConverter {
    public double convert(String fromCurrency, String toCurrency, double amount) {
        // Simulate a conversion
        if ("USD".equals(fromCurrency) && "EUR".equals(toCurrency)) {
            return amount * 0.85;
        }
        return amount;
    }
}

// Target interface
interface CurrencyConverter {
    double convertToUSD(double amount);
    double convertFromUSD(double amount);
}

// Adapter class
public class CurrencyConverterAdapter implements CurrencyConverter {
    private ThirdPartyCurrencyConverter thirdPartyConverter;

    public CurrencyConverterAdapter() {
        thirdPartyConverter = new ThirdPartyCurrencyConverter();
    }

    @Override
    public double convertToUSD(double amount) {
        return thirdPartyConverter.convert("EUR", "USD", amount);
    }

    @Override
    public double convertFromUSD(double amount) {
        return thirdPartyConverter.convert("USD", "EUR", amount);
    }
}
