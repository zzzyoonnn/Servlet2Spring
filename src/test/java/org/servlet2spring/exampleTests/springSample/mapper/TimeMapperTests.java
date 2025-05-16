package org.servlet2spring.exampleTests.springSample.mapper;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.servlet2spring.servlet2spring.examples.springSample.mapper.TimeMapper;
import org.servlet2spring.servlet2spring.examples.springSample.mapper.TimeMapper2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@Log4j2
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/root-context.xml")
public class TimeMapperTests {

  @Autowired(required = false)
  private TimeMapper2 timeMapper2;

  @Test
  public void testNow() {
    log.info(timeMapper2.getNow());
  }


  @Autowired(required = false)
  private TimeMapper timeMapper;

  @Test
  public void testGetTime() {
    log.info(timeMapper.getTime());
  }
}
