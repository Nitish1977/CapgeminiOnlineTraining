package Streams.ComplexDataStructures;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class StaffMember {
    private String name;
    private String department;
    private double salary;

    public StaffMember(String name, String department, double salary) {
        this.name = name;
        this.department = department;
        this.salary = salary;
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

    public String getSalaryBracket() {
        if (salary < 50000) {
            return "< 50,000";
        } else if (salary <= 100000) {
            return "50,000 - 100,000";
        } else {
            return "> 100,000";
        }
    }

    @Override
    public String toString() {
        return name + " ($" + salary + ")";
    }
}

public class GroupEmployeesByDeptAndSalaryRange {
    public static void main(String[] args) {
        List<StaffMember> staff = Arrays.asList(
            new StaffMember("Alice", "Engineering", 120000),
            new StaffMember("Bob", "Engineering", 85000),
            new StaffMember("Charlie", "Engineering", 45000),
            new StaffMember("David", "HR", 48000),
            new StaffMember("Eve", "HR", 75000),
            new StaffMember("Frank", "Finance", 110000),
            new StaffMember("Grace", "Finance", 95000)
        );

        Map<String, Map<String, List<StaffMember>>> groupedStaff = staff.stream()
                .collect(Collectors.groupingBy(
                        StaffMember::getDepartment,
                        Collectors.groupingBy(StaffMember::getSalaryBracket)
                ));

        System.out.println("=== EMPLOYEES GROUPED BY DEPT & SALARY RANGE ===");
        groupedStaff.forEach((dept, bracketMap) -> {
            System.out.println("Department: " + dept);
            bracketMap.forEach((bracket, members) -> {
                System.out.println("  Bracket [" + bracket + "]: " + members);
            });
        });
    }
}
