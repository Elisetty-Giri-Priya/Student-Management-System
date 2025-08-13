package com.example.sms.service;

import com.example.sms.dao.StudentDao;
import com.example.sms.model.Student;

import java.sql.SQLException;
import java.util.List;

public class StudentService {
  private final StudentDao dao = new StudentDao();

  public int add(String roll, String name, String email, String branch, Integer year) throws SQLException {
    return dao.create(new Student(null, roll, name, email, branch, year));
  }
  public List<Student> all() throws SQLException { return dao.listAll(); }
  public Student byRoll(String roll) throws SQLException { return dao.findByRoll(roll); }
  public boolean updateEmail(String roll, String email) throws SQLException { return dao.updateEmail(roll, email); }
  public boolean delete(String roll) throws SQLException { return dao.deleteByRoll(roll); }
}
