package org.servlet2spring.examples.springSample.controller;

import java.time.LocalDate;
import lombok.extern.log4j.Log4j2;
import org.servlet2spring.todo.dto.TodoDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Log4j2
@Controller
public class SampleController {

  @GetMapping("/hello")
  public void hello() {
    log.info("hello...");
  }

  @GetMapping("/ex1")
  @ResponseBody
  public void ex1(@RequestParam(name = "name", defaultValue = "AAA") String name,
                  @RequestParam(name = "age", defaultValue = "20") int age) {
    log.info("ex1...");
    log.info("name:" + name);
    log.info("age:" + age);
  }

  @GetMapping("/ex2")
  public void ex2(@RequestParam("dueDate") LocalDate dueDate) {
    log.info("ex2...");
    log.info("dueDate:" + dueDate);
  }

  @GetMapping("/ex3")
  public void ex3(Model model) {
    log.info("-----------------------------------");

    model.addAttribute("message", "Hello World");
  }

  @GetMapping("/ex4")
  public void ex4(TodoDTO todoDTO, Model model) {
    log.info("ex4...");
  }

  @GetMapping("/ex5")
  public void ex5(@ModelAttribute("dto") TodoDTO todoDTO, Model model) {
    log.info(todoDTO);
  }

  @GetMapping("/ex6")
  public String ex6(RedirectAttributes redirectAttributes) {

    redirectAttributes.addAttribute("name", "ABC");
    redirectAttributes.addFlashAttribute("result", "success");

    return "redirect:/ex7";
  }

  @GetMapping("/ex7")
  public void ex7(@RequestParam("p1") String p1, @RequestParam("p2") int p2) {
    log.info("p1..." + p1);
    log.info("p2..." + p2);
  }

}
