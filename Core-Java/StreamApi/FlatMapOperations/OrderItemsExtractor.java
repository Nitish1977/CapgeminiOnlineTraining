package Streams.FlatMapOperations;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class Item {
    private String name;
    private double price;

    public Item(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}

class Order {
    private int orderId;
    private List<Item> items;

    public Order(int orderId, List<Item> items) {
        this.orderId = orderId;
        this.items = items;
    }

    public int getOrderId() {
        return orderId;
    }

    public List<Item> getItems() {
        return items;
    }
}

public class OrderItemsExtractor {
    public static void main(String[] args) {
        Order order1 = new Order(101, Arrays.asList(
            new Item("Book", 15.99),
            new Item("Pen", 2.50)
        ));

        Order order2 = new Order(102, Arrays.asList(
            new Item("Laptop", 999.99),
            new Item("Mouse", 25.00),
            new Item("Keyboard", 45.50)
        ));

        Order order3 = new Order(103, Arrays.asList(
            new Item("Notebook", 4.99)
        ));

        List<Order> orders = Arrays.asList(order1, order2, order3);

        List<String> allItemNames = orders.stream()
                .flatMap(order -> order.getItems().stream())
                .map(Item::getName)
                .collect(Collectors.toList());

        System.out.println("All item names across all orders: " + allItemNames);
    }
}
