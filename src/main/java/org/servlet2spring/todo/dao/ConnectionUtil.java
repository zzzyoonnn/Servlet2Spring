package org.servlet2spring.todo.dao;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;

public enum ConnectionUtil {

  INSTANCE;

  private HikariDataSource dataSource;

  ConnectionUtil() {
    HikariConfig config = new HikariConfig();
    config.setDriverClassName("com.mysql.cj.jdbc.Driver");
    config.setJdbcUrl("jdbc:mysql://localhost:3306/s2s");
    config.setUsername("s2s");
    config.setPassword("s2s");
    config.addDataSourceProperty("cachePrepStmts", "true");
    config.addDataSourceProperty("prepStmtCacheSize", "250");
    config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

    dataSource = new HikariDataSource(config);
  }

  public Connection getConnection() throws Exception {
    return dataSource.getConnection();
  }
}
