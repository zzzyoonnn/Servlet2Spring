package org.servlet2spring.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ConnectTests {

  @Test
  public void testConnection() throws Exception {
    Class.forName("com.mysql.cj.jdbc.Driver");

    Connection connection = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/s2s",
            "s2s",
            "s2s"
    );

    Assertions.assertNotNull(connection);

    connection.close();

  }

  @Test
  public void test() {
    int v1 = 10;
    int v2 = 10;

    Assertions.assertEquals(v1, v2);
  }
}
