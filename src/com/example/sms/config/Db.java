package com.example.sms.config;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Db {
  private static String url, user, pass;

  static {
    Properties p = new Properties();
    try (var in = Db.class.getClassLoader().getResourceAsStream("application.properties")) {
      if (in == null) throw new RuntimeException("application.properties not found on classpath");
      p.load(in);
      url = p.getProperty("db.url");
      user = p.getProperty("db.user");
      pass = p.getProperty("db.password");
      // Optional but fine to keep:
      Class.forName("com.mysql.cj.jdbc.Driver");
    } catch (IOException | ClassNotFoundException e) {
      throw new RuntimeException("DB init failed", e);
    }
  }

  public static Connection get() throws SQLException {
    return DriverManager.getConnection(url, user, pass);
  }
}
