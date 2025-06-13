package org.servlet2spring.examples.springBootSample;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Log4j2
@Controller
public class SpringBootSampleController {
  @GetMapping("/helloSpringBoot")
  public void helloSpringBoot(Model model) {
    log.info("hello");
    model.addAttribute("message", "Hello Spring Boot!");
  }
}
