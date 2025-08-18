package org.servlet2spring.todo.controller;

import io.swagger.v3.oas.annotations.Operation;
import java.util.Arrays;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sample")
public class SampleSwaggerController {

  @Operation(summary = "Sample GET doA")
  @GetMapping("/doA")
  public List<String> doA() {
    return Arrays.asList("AAA", "BBB", "CCC");
  }
}
