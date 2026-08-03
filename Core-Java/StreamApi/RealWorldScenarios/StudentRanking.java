package Streams.RealWorldScenarios;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

class Student {
    private String name;
    private List<Integer> marks; // 5 subjects, max 100 each

    public Student(String name, List<Integer> marks) {
        this.name = name;
        this.marks = marks;
    }

    public String getName() {
        return name;
    }

    public List<Integer> getMarks() {
        return marks;
    }

    public int getTotalMarks() {
        return marks.stream().mapToInt(Integer::intValue).sum();
    }

    public double getPercentage() {
        return getTotalMarks() / 5.0;
    }
}

public class StudentRanking {
    public static void main(String[] args) {
        List<Student> students = Arrays.asList(
            new Student("Rahul", Arrays.asList(85, 90, 78, 92, 88)),
            new Student("Priya", Arrays.asList(95, 98, 92, 96, 94)),
            new Student("Amit", Arrays.asList(70, 75, 68, 72, 74)),
            new Student("Sneha", Arrays.asList(88, 92, 90, 85, 89))
        );

        System.out.println("=== STUDENT REPORT CARD & RANKINGS ===");

        // Sort students by percentage descending
        List<Student> sortedStudents = students.stream()
                .sorted(Comparator.comparingDouble(Student::getPercentage).reversed())
                .collect(Collectors.toList());

        AtomicInteger rank = new AtomicInteger(1);

        sortedStudents.forEach(student -> {
            int currentRank = rank.getAndIncrement();
            System.out.printf("Rank %d | %-8s | Total: %3d/500 | Percentage: %.2f%%%n",
                    currentRank, student.getName(), student.getTotalMarks(), student.getPercentage());
        });
    }
}
