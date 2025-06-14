package org.servlet2spring.examples.springBootSample;

import java.util.Arrays;
import java.util.List;
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

  @GetMapping("/ex/ex1")
  public void ex1(Model model) {
    List<String> list = Arrays.asList("A", "BB", "CCC");
    model.addAttribute("list", list);
  }
}
