package StreamsApi.AdvancedMapping;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class Employee {
    private String name;
    private double salary;

    public Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return name + " (" + salary + ")";
    }
}

public class ExtractHighEarners {
    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
            new Employee("Alice", 75000),
            new Employee("Bob", 50000),
            new Employee("Charlie", 90000),
            new Employee("David", 60000),
            new Employee("Eve", 110000)
        );

        // Step 1: Compute average salary across all employees
        double avgSalary = employees.stream()
                .collect(Collectors.averagingDouble(Employee::getSalary));

        System.out.println("Average Salary: " + avgSalary);

        // Step 2: Filter employees with salary > avgSalary and collect their names
        List<String> highEarnerNames = employees.stream()
                .filter(e -> e.getSalary() > avgSalary)
                .map(Employee::getName)
                .collect(Collectors.toList());

        System.out.println("Employees earning more than average salary: " + highEarnerNames);
    }
}
