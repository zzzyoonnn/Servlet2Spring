package org.servlet2spring.exampleTests.springSample;

import java.sql.Connection;
import javax.sql.DataSource;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.servlet2spring.servlet2spring.examples.springSample.SampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@Log4j2
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/root-context.xml")
public class SampleTests {

  @Autowired
  private SampleService sampleService;

  @Autowired
  private DataSource dataSource;

  @Test
  public void testConnection() throws Exception {

    Connection connection = dataSource.getConnection();
    log.info(connection);
    Assertions.assertNotNull(connection);

    connection.close();
  }

  @Test
  public void testService1() {
    log.info(sampleService);
    Assertions.assertNotNull(sampleService);
  }
}
