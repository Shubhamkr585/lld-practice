import java.util.HashMap;
import java.util.*;

 class Student {
    // 1. STATIC: Shared by all objects (Class Level)
    // C++: static string collegeName; (Define outside class)
    // Java: Yahi define aur initialize kar sakte hain.
    private static String collegeName = "BIT Mesra";

    // 2. FINAL: Constant per instance (Once assigned, cannot change)
    // C++: const int rollNo;
    // LLD Use Case: IDs, Immutable attributes
    private final int rollNo;

    private String name;

    // 3. COLLECTIONS: Interface (Map) use karna better hai implementation (HashMap) se.
    // LLD Principle: Code against interfaces.
    private Map<String, Integer> marks;

    // Constructor
    public Student(int rollNo, String name) {
        this.rollNo = rollNo; // 'final' variable must be initialized here
        this.name = name;
        this.marks = new HashMap<>(); // Heap memory allocation
    }

    // Instance Method
    public void addMarks(String subject, int score) {
        marks.put(subject, score); // C++: marks[subject] = score;
    }

    // Static Method (Can only access static variables)
    public static void changeCollege(String newName) {
        collegeName = newName;
    }

    // toString() is like overriding '<<' operator in C++ to print object
    @Override
    public String toString() {
        return "Roll: " + rollNo + ", Name: " + name + ", College: " + collegeName + ", Marks: " + marks;
    }
}

public class Main {
    public static void main(String[] args) {
        Student s1 = new Student(101, "Shubham");
        s1.addMarks("LLD", 90);
        s1.addMarks("DSA", 95);

        Student s2 = new Student(102, "Rahul");
        s2.addMarks("Web Dev", 88);

        // Static change affects everyone
        Student.changeCollege("IIT Bombay");

        System.out.println(s1);
        System.out.println(s2);

    }
}