package StreamsApi.AdvancedMapping;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class DeptEmployee {
    private String name;
    private String department;

    public DeptEmployee(String name, String department) {
        this.name = name;
        this.department = department;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }
}

public class UniqueDepartments {
    public static void main(String[] args) {
        List<DeptEmployee> employees = Arrays.asList(
            new DeptEmployee("Alice", "Engineering"),
            new DeptEmployee("Bob", "HR"),
            new DeptEmployee("Charlie", "Engineering"),
            new DeptEmployee("David", "Finance"),
            new DeptEmployee("Eve", "HR"),
            new DeptEmployee("Frank", "Marketing")
        );

        List<String> uniqueDepartments = employees.stream()
                .map(DeptEmployee::getDepartment)
                .distinct()
                .collect(Collectors.toList());

        System.out.println("Unique Departments: " + uniqueDepartments);
    }
}
