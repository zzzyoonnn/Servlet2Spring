package org.servlet2spring.apprds;

import com.mysql.cj.x.protobuf.MysqlxPrepare.Prepare;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.sql.DataSource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AppRdsApplicationTests {

  @Autowired
  private DataSource dataSource;

  @Test
  void contextLoads() {
    try (Connection connection = dataSource.getConnection();
    PreparedStatement preparedstatement = connection.prepareStatement("select now()");
    ResultSet resultSet = preparedstatement.executeQuery();) {
      resultSet.next();

      System.out.println(resultSet.getString(1));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
