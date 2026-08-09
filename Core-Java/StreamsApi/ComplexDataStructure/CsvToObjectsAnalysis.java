package StreamsApi.ComplexDataStructure;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class CsvTransaction {
    private int transactionId;
    private String category;
    private double amount;

    public CsvTransaction(int transactionId, String category, double amount) {
        this.transactionId = transactionId;
        this.category = category;
        this.amount = amount;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public String getCategory() {
        return category;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return String.format("Txn#%d [%s: $%.2f]", transactionId, category, amount);
    }
}

public class CsvToObjectsAnalysis {
    public static void main(String[] args) {
        // Raw CSV data representation (List of String arrays)
        List<String[]> csvRows = Arrays.asList(
            new String[]{"101", "Electronics", "299.99"},
            new String[]{"102", "Groceries", "45.50"},
            new String[]{"103", "Electronics", "899.00"},
            new String[]{"104", "Clothing", "120.00"},
            new String[]{"105", "Groceries", "85.25"},
            new String[]{"106", "Clothing", "60.00"}
        );

        // 1. Convert CSV rows (String[]) into CsvTransaction objects
        List<CsvTransaction> transactions = csvRows.stream()
                .map(row -> new CsvTransaction(
                        Integer.parseInt(row[0]),
                        row[1],
                        Double.parseDouble(row[2])
                ))
                .collect(Collectors.toList());

        System.out.println("Parsed Transactions Object List:");
        transactions.forEach(System.out::println);

        // 2. Perform Analysis: Calculate Total Sales per Category
        Map<String, Double> totalSalesByCategory = transactions.stream()
                .collect(Collectors.groupingBy(
                        CsvTransaction::getCategory,
                        Collectors.summingDouble(CsvTransaction::getAmount)
                ));

        System.out.println("\nTotal Sales by Category:");
        totalSalesByCategory.forEach((cat, total) ->
            System.out.printf(" - %-12s: $%.2f%n", cat, total)
        );

        // 3. Find highest single transaction amount
        CsvTransaction highestTxn = transactions.stream()
                .max((t1, t2) -> Double.compare(t1.getAmount(), t2.getAmount()))
                .orElseThrow();

        System.out.println("\nHighest Transaction: " + highestTxn);
    }
}
