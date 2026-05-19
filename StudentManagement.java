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
        System.out.println("\n=============================");
        System.out.println("  Student Management System  ");
        System.out.println("  [CREATE - Add Student]     ");
        System.out.println("=============================");
        addStudent();
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
          }
