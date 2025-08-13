package com.example.sms;

import com.example.sms.service.StudentService;

import java.util.Scanner;

public class Main {
  static final Scanner sc = new Scanner(System.in);

  public static void main(String[] args) throws Exception {
    var svc = new StudentService();
    while (true) {
      System.out.println("\n=== Student Management ===");
      System.out.println("1. List All");
      System.out.println("2. Add");
      System.out.println("3. Find by Roll");
      System.out.println("4. Update Email");
      System.out.println("5. Delete by Roll");
      System.out.println("0. Exit");
      System.out.print("Choose: ");
      String ch = sc.nextLine().trim();
      try {
        switch (ch) {
          case "1" -> svc.all().forEach(System.out::println);
          case "2" -> addFlow(svc);
          case "3" -> findFlow(svc);
          case "4" -> updateFlow(svc);
          case "5" -> deleteFlow(svc);
          case "0" -> { System.out.println("Bye!"); return; }
          default -> System.out.println("Invalid choice");
        }
      } catch (Exception e) {
        System.out.println("Error: " + e.getMessage());
      }
    }
  }

  static void addFlow(StudentService svc) throws Exception {
    System.out.print("Roll No: "); String roll = sc.nextLine();
    System.out.print("Name   : "); String name = sc.nextLine();
    System.out.print("Email  : "); String email = sc.nextLine();
    System.out.print("Branch : "); String branch = sc.nextLine();
    System.out.print("Year(1-5): "); Integer year = Integer.parseInt(sc.nextLine());
    int id = svc.add(roll, name, email, branch, year);
    System.out.println("Created id = " + id);
  }

  static void findFlow(StudentService svc) throws Exception {
    System.out.print("Roll No: "); String roll = sc.nextLine();
    var s = svc.byRoll(roll);
    System.out.println(s == null ? "Not found" : s);
  }

  static void updateFlow(StudentService svc) throws Exception {
    System.out.print("Roll No: "); String roll = sc.nextLine();
    System.out.print("New Email: "); String email = sc.nextLine();
    System.out.println(svc.updateEmail(roll, email) ? "Updated" : "No change");
  }

  static void deleteFlow(StudentService svc) throws Exception {
    System.out.print("Roll No: "); String roll = sc.nextLine();
    System.out.println(svc.delete(roll) ? "Deleted" : "Not found");
  }
}

