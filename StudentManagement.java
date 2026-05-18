import java.util.ArrayList;
import java.util.Scanner;

// ── Data Model ──────────────────────────────────────────────
class Student {
    int id;
    String name;
    String course;
    float marks;

    Student(int id, String name, String course, float marks) {
        this.id     = id;
        this.name   = name;
        this.course = course;
        this.marks  = marks;
    }

    public String toString() {
        return "ID: " + id
             + " | Name: "   + name
             + " | Course: " + course
             + " | Marks: "  + marks;
    }
}

// ── Main Application ─────────────────────────────────────────
public class StudentManagement {

    static ArrayList<Student> students = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\n=============================");
            System.out.println("  Student Management System  ");
            System.out.println("=============================");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Exit");
            System.out.println("=============================");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1: addStudent();    break;
                case 2: viewStudents();  break;
                case 3: updateStudent(); break;
                case 4: deleteStudent(); break;
                case 5: System.out.println("\nGoodbye! See you soon :)"); break;
                default: System.out.println("Invalid choice! Please try again.");
            }

        } while (choice != 5);
    }

    // ── CREATE ───────────────────────────────────────────────
    static void addStudent() {
        System.out.println("\n-- Add New Student --");
        System.out.print("Enter ID     : ");
        int id = sc.nextInt();
        sc.nextLine(); // clear buffer
        System.out.print("Enter Name   : ");
        String name = sc.nextLine();
        System.out.print("Enter Course : ");
        String course = sc.nextLine();
        System.out.print("Enter Marks  : ");
        float marks = sc.nextFloat();

        students.add(new Student(id, name, course, marks));
        System.out.println("✔ Student added successfully!");
    }

    // ── READ ─────────────────────────────────────────────────
    static void viewStudents() {
        System.out.println("\n-- All Students --");
        if (students.isEmpty()) {
            System.out.println("No students found!");
            return;
        }
        for (Student s : students) {
            System.out.println(s);
        }
    }

    // ── UPDATE ───────────────────────────────────────────────
    static void updateStudent() {
        System.out.println("\n-- Update Student --");
        System.out.print("Enter Student ID to update: ");
        int id = sc.nextInt();
        sc.nextLine();

        for (Student s : students) {
            if (s.id == id) {
                System.out.print("Enter new Name   : ");
                s.name   = sc.nextLine();
                System.out.print("Enter new Course : ");
                s.course = sc.nextLine();
                System.out.print("Enter new Marks  : ");
                s.marks  = sc.nextFloat();
                System.out.println("✔ Student updated successfully!");
                return;
            }
        }
        System.out.println("✘ Student with ID " + id + " not found!");
    }

    // ── DELETE ───────────────────────────────────────────────
    static void deleteStudent() {
        System.out.println("\n-- Delete Student --");
        System.out.print("Enter Student ID to delete: ");
        int id = sc.nextInt();

        boolean removed = students.removeIf(s -> s.id == id);
        if (removed) {
            System.out.println("✔ Student deleted successfully!");
        } else {
            System.out.println("✘ Student with ID " + id + " not found!");
        }
    }
}
