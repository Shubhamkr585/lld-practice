// Stream : allows declarative programming by applying operations on collections as stream(intermediate form of data)
//filter(Predicate): Keeps only elements that match a condition.
//map(Function): Transforms each element into something else (e.g., String to Integer).
// sorted(): Reorders the elements.
// collect(Collectors.toList()): Puts the final results back into a List.

import java.util.*;
import java.util.stream.Collectors; // <--- VITAL IMPORT 1
import java.util.stream.Stream;

// 1. Define the Employee Class so Java knows what it is
class Employee {
    private String name;
    private String dept;
    private int salary;

    public Employee(String name, String dept, int salary) {
        this.name = name;
        this.dept = dept;
        this.salary = salary;
    }

    public String getName() { return name; }
    public String getDept() { return dept; }
    public int getSalary() { return salary; }
}

public class StreamDemo {

    public static void main(String[] args) {

        // 2. Create some dummy data
        List<Employee> employees = Arrays.asList(
                new Employee("Alice", "Tech", 60000),
                new Employee("Bob", "HR", 45000),
                new Employee("Charlie", "Tech", 70000),
                new Employee("David", "Ops", 55000)
        );

        // 3. The Stream Pipeline
        List<String> result = employees.stream()
                .filter(e -> e.getDept().equals("Tech"))   // Keep only Tech
                .filter(e -> e.getSalary() > 50000)        // Keep only High Salary
                .map(Employee::getName)                    // Extract Name
                .sorted()                                  // Sort Alphabetically
                .collect(Collectors.toList());             // Collect to List

        System.out.println(result);
        // Output should be: [Alice, Charlie]
    }
}

// differnt ways of making streams
//From Collections (Most Common)
//List<String> list = Arrays.asList("A", "B", "C");
//Stream<String> stream = list.stream();

//from Arrays
//String[] arr = { "x", "y", "z" };
//Stream<String> stream = Arrays.stream(arr);
//
// For primitives (Optimized)
//int[] numbers = { 1, 2, 3 };
//IntStream intStream = Arrays.stream(numbers);

// using Stream.of
//Stream<Integer> stream = Stream.of(1, 2, 3, 4, 5);
//Infinite Streams (For Algorithms/Testing)
//Generates 5 random numbers
//Stream<Double> randoms = Stream.generate(Math::random).limit(5);

//until and unless the terminal operation is applied it does doesn't  actually perform the
//intermdedite steps (lazy operation)

// streams cannot be reused after the terminal operations have been performed
