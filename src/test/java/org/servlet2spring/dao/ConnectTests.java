package org.servlet2spring.dao;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ConnectTests {

  @Test
  public void testHikariCP() throws Exception {
    HikariConfig config = new HikariConfig();
    config.setDriverClassName("com.mysql.cj.jdbc.Driver");
    config.setJdbcUrl("jdbc:mysql://localhost:3306/s2s");
    config.setUsername("s2s");
    config.setPassword("s2s");
    config.addDataSourceProperty("cachePrepStmts", "true");
    config.addDataSourceProperty("prepStmtCacheSize", "250");
    config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

    HikariDataSource ds = new HikariDataSource(config);
    Connection conn = ds.getConnection();

    System.out.println(conn);

    conn.close();
  }


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
