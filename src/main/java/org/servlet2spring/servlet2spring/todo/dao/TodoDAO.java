package org.servlet2spring.servlet2spring.todo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import lombok.Cleanup;

public class TodoDAO {

  public String getTime2() throws Exception {

    @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
    @Cleanup PreparedStatement preparedStatement = connection.prepareStatement("select now()");
    @Cleanup ResultSet resultSet = preparedStatement.executeQuery();

    resultSet.next();

    String now = resultSet.getString(1);

    return now;
  }

  public String getTime() {

    String now = null;

    try(Connection connection = ConnectionUtil.INSTANCE.getConnection();
    PreparedStatement preparedStatement = connection.prepareStatement("select now()");
    ResultSet resultSet = preparedStatement.executeQuery();) {

      resultSet.next();

      now = resultSet.getString(1);
    } catch (Exception e) {
      e.printStackTrace();
    }

    return now;
  }
}
