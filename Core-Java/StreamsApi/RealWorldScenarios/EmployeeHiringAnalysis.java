package StreamsApi.RealWorldScenarios;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class CompanyEmployee {
    private int id;
    private String name;
    private String department;
    private double salary;
    private LocalDate joiningDate;

    public CompanyEmployee(int id, String name, String department, double salary, LocalDate joiningDate) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.salary = salary;
        this.joiningDate = joiningDate;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public double getSalary() {
        return salary;
    }

    public LocalDate getJoiningDate() {
        return joiningDate;
    }
}

public class EmployeeHiringAnalysis {
    public static void main(String[] args) {
        LocalDate now = LocalDate.now();
        LocalDate fiveYearsAgo = now.minusYears(5);

        List<CompanyEmployee> employees = Arrays.asList(
            new CompanyEmployee(1, "Alice", "IT", 90000, LocalDate.of(2023, 5, 10)),
            new CompanyEmployee(2, "Bob", "IT", 75000, LocalDate.of(2022, 3, 15)),
            new CompanyEmployee(3, "Charlie", "IT", 120000, LocalDate.of(2018, 1, 10)), // Older than 5 years
            new CompanyEmployee(4, "David", "HR", 60000, LocalDate.of(2024, 2, 20)),
            new CompanyEmployee(5, "Eve", "HR", 65000, LocalDate.of(2021, 11, 5)),
            new CompanyEmployee(6, "Frank", "Finance", 85000, LocalDate.of(2017, 8, 12)) // Older than 5 years
        );

        Map<String, Double> deptAvgSalaryRecent = employees.stream()
                .filter(emp -> emp.getJoiningDate().isAfter(fiveYearsAgo))
                .collect(Collectors.groupingBy(
                        CompanyEmployee::getDepartment,
                        Collectors.averagingDouble(CompanyEmployee::getSalary)
                ));

        System.out.println("Cutoff Date (5 Years Ago): " + fiveYearsAgo);
        System.out.println("Department-wise Average Salary of Employees Hired in Last 5 Years:");
        deptAvgSalaryRecent.forEach((dept, avgSal) -> 
            System.out.printf(" - %s: $%.2f%n", dept, avgSal)
        );
    }
}
