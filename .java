import java.util.ArrayList;
import java.util.Scanner;

class Student {
    int id;
    String name;
    String course;
    String marks;

    Student(int id, String name, String course, String marks) {
        this.id = id;
        this.name = name;
        this.course = course;
        this.marks = marks;
    }

    public String toString() {
        return "ID: " + id + " | Name: " + name
             + " | Course: " + course + " | Marks: " + marks;
    }
}

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
            System.out.println("3. Exit");
            System.out.println("=============================");
            System.out.print("Enter your choice: ");
            choice = Integer.parseInt(sc.nextLine().trim());
            switch (choice) {
                case 1: addStudent();   break;
                case 2: viewStudents(); break;
                case 3: System.out.println("\nGoodbye! See you soon :)"); break;
                default: System.out.println("Invalid choice!");
            }
        } while (choice != 3);
    }

    // ── CREATE ───────────────────────────────────────────────
    static void addStudent() {
        System.out.println("\n-- Add New Student --");
        System.out.print("Enter ID     : ");
        int id = Integer.parseInt(sc.nextLine().trim());
        System.out.print("Enter Name   : ");
        String name = sc.nextLine().trim();
        System.out.print("Enter Course : ");
        String course = sc.nextLine().trim();
        System.out.print("Enter Marks  : ");
        String marks = sc.nextLine().trim();
        students.add(new Student(id, name, course, marks));
        System.out.println("Student added successfully!");
    }

    // ── READ ─────────────────────────────────────────────────
    static void viewStudents() {
        System.out.println("\n-- All Students --");
        if (students.isEmpty()) {
            System.out.println("No students found!");
            return;
        }
        for (Student s : students) System.out.println(s);
    }
              }
