package Enums;


//Enum: Used to define a collection of fixed constants
// variables that do not change
// to prevent from runtime error while passing another value.

// Advantage:
//Type safety, Better Readability,
public enum Currency {
    USD("$", 100),
    INR("R", 90),
    EURO("&", 98);

    private final String symbol;
    private final double exchangeRate;


    Currency(String symbol, double exchangeRate){
        this.symbol=symbol;
        this.exchangeRate=exchangeRate;
    }

    public String getSymbol(){
        return symbol;
    }

    public double ConvertToUSD(double amount){
        return exchangeRate/ amount;
    }



}
