package Streams.RealWorldScenarios;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class CartItem {
    private String name;
    private double price;

    public CartItem(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }
}

class CustomerOrder {
    private String customerName;
    private List<CartItem> items;

    public CustomerOrder(String customerName, List<CartItem> items) {
        this.customerName = customerName;
        this.items = items;
    }

    public String getCustomerName() {
        return customerName;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public double getOrderTotal() {
        return items.stream().mapToDouble(CartItem::getPrice).sum();
    }
}

public class CustomerTotalSpend {
    public static void main(String[] args) {
        List<CustomerOrder> orders = Arrays.asList(
            new CustomerOrder("Alice", Arrays.asList(new CartItem("Laptop", 1200.00), new CartItem("Mouse", 25.50))),
            new CustomerOrder("Bob", Arrays.asList(new CartItem("Phone", 650.00), new CartItem("Case", 20.00))),
            new CustomerOrder("Alice", Arrays.asList(new CartItem("Headphones", 150.00))),
            new CustomerOrder("Charlie", Arrays.asList(new CartItem("Monitor", 300.00), new CartItem("HDMI Cable", 15.00))),
            new CustomerOrder("Bob", Arrays.asList(new CartItem("Charger", 30.00)))
        );

        Map<String, Double> totalSpendPerCustomer = orders.stream()
                .collect(Collectors.groupingBy(
                        CustomerOrder::getCustomerName,
                        Collectors.summingDouble(CustomerOrder::getOrderTotal)
                ));

        System.out.println("Total Amount Spent by Each Customer:");
        totalSpendPerCustomer.forEach((customer, total) -> 
            System.out.printf("%s: $%.2f%n", customer, total)
        );
    }
}
