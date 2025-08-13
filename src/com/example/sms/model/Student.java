package com.example.sms.model;

public class Student {
  private Integer id;
  private String rollNo;
  private String name;
  private String email;
  private String branch;
  private Integer year;

  public Student() {}
  public Student(Integer id, String rollNo, String name, String email, String branch, Integer year) {
    this.id = id; this.rollNo = rollNo; this.name = name; this.email = email; this.branch = branch; this.year = year;
  }

  public Integer getId() { return id; }
  public void setId(Integer id) { this.id = id; }
  public String getRollNo() { return rollNo; }
  public void setRollNo(String rollNo) { this.rollNo = rollNo; }
  public String getName() { return name; }
  public void setName(String name) { this.name = name; }
  public String getEmail() { return email; }
  public void setEmail(String email) { this.email = email; }
  public String getBranch() { return branch; }
  public void setBranch(String branch) { this.branch = branch; }
  public Integer getYear() { return year; }
  public void setYear(Integer year) { this.year = year; }

  @Override public String toString() {
    return "Student{id=%d, rollNo='%s', name='%s', email='%s', branch='%s', year=%d}"
        .formatted(id, rollNo, name, email, branch, year);
  }
}
