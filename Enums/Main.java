package Enums;

public class Main {
    public static void main(String[] args) {

        double convertedValue = Currency.INR.ConvertToUSD(38);
        System.out.println("Currency: "+Currency.INR.getSymbol());
        System.out.println("Amount: "+convertedValue);

    }
}
