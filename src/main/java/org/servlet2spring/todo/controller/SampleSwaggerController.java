package org.servlet2spring.todo.controller;

import io.swagger.v3.oas.annotations.Operation;
import java.util.Arrays;
import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sample")
public class SampleSwaggerController {

  @Operation(summary = "Sample GET doA")
  @PreAuthorize("hasRole('ROLE_USER')")
  @GetMapping("/doA")
  public List<String> doA() {
    return Arrays.asList("AAA", "BBB", "CCC");
  }

  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @GetMapping("/doB")
  public List<String> doB() {
    return Arrays.asList("AdminAAA", "AdminBBB", "AdminCCC");
  }
}
