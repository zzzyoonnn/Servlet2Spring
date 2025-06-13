package org.servlet2spring.examples.springBootSample;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
public class SpringBootSampleJSONController {
  @GetMapping("/helloArr")
  public String[] helloArr() {
    log.info("helloSpringBootArr");

    return new String[]{"A", "BB", "CCC", "DDDD", "EEEEE"};
  }
}
