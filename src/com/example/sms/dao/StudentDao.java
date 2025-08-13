package com.example.sms.dao;

import com.example.sms.config.Db;
import com.example.sms.model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDao {

  public int create(Student s) throws SQLException {
    String sql = "INSERT INTO students(roll_no, name, email, branch, year) VALUES (?,?,?,?,?)";
    try (Connection con = Db.get(); PreparedStatement ps =
         con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
      ps.setString(1, s.getRollNo());
      ps.setString(2, s.getName());
      ps.setString(3, s.getEmail());
      ps.setString(4, s.getBranch());
      ps.setObject(5, s.getYear(), java.sql.Types.INTEGER);
      ps.executeUpdate();
      try (ResultSet rs = ps.getGeneratedKeys()) { if (rs.next()) return rs.getInt(1); }
    }
    return -1;
  }

  public List<Student> listAll() throws SQLException {
    String sql = "SELECT id, roll_no, name, email, branch, year FROM students ORDER BY id";
    List<Student> out = new ArrayList<>();
    try (Connection con = Db.get(); PreparedStatement ps = con.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {
      while (rs.next()) out.add(map(rs));
    }
    return out;
  }

  public Student findByRoll(String roll) throws SQLException {
    String sql = "SELECT id, roll_no, name, email, branch, year FROM students WHERE roll_no=?";
    try (Connection con = Db.get(); PreparedStatement ps = con.prepareStatement(sql)) {
      ps.setString(1, roll);
      try (ResultSet rs = ps.executeQuery()) { if (rs.next()) return map(rs); }
    }
    return null;
  }

  public boolean updateEmail(String roll, String newEmail) throws SQLException {
    String sql = "UPDATE students SET email=? WHERE roll_no=?";
    try (Connection con = Db.get(); PreparedStatement ps = con.prepareStatement(sql)) {
      ps.setString(1, newEmail);
      ps.setString(2, roll);
      return ps.executeUpdate() > 0;
    }
  }

  public boolean deleteByRoll(String roll) throws SQLException {
    String sql = "DELETE FROM students WHERE roll_no=?";
    try (Connection con = Db.get(); PreparedStatement ps = con.prepareStatement(sql)) {
      ps.setString(1, roll);
      return ps.executeUpdate() > 0;
    }
  }

  private Student map(ResultSet rs) throws SQLException {
    return new Student(
      rs.getInt("id"),
      rs.getString("roll_no"),
      rs.getString("name"),
      rs.getString("email"),
      rs.getString("branch"),
      (Integer) rs.getObject("year")
    );
  }
}
