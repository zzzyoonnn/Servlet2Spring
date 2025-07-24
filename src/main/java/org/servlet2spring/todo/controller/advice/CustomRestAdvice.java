package org.servlet2spring.todo.controller.advice;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import lombok.extern.log4j.Log4j2;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Log4j2
@RestControllerAdvice
public class CustomRestAdvice {

  @ExceptionHandler(BindException.class)
  @ResponseStatus(HttpStatus.EXPECTATION_FAILED)
  public ResponseEntity<Map<String, String>> handleBindException(BindException e) {
    log.error(e);
    Map<String, String> errorMap = new HashMap<>();

    if (e.hasErrors()) {
      BindingResult bindingResult = e.getBindingResult();

      bindingResult.getFieldErrors().forEach(fieldError -> {
        errorMap.put(fieldError.getField(), fieldError.getDefaultMessage());
      });

    }
    return ResponseEntity.badRequest().body(errorMap);
  }

  // 데이터 무결성 오류 대응
  @ExceptionHandler(DataIntegrityViolationException.class)
  @ResponseStatus(HttpStatus.EXPECTATION_FAILED)
  public ResponseEntity<Map<String, String>> handleFKException(Exception e) {
    log.error(e);

    Map<String, String> errorMap = new HashMap<>();
    errorMap.put("time", ""+System.currentTimeMillis());
    errorMap.put("message", "constraint fails");

    return ResponseEntity.badRequest().body(errorMap);
  }

  // 댓글이 존재하지 않을 경우
  @ExceptionHandler(NoSuchElementException.class)
  @ResponseStatus(HttpStatus.EXPECTATION_FAILED)
  public ResponseEntity<Map<String, String>> handleNoSuchElement(Exception e) {
    log.error(e);

    Map<String, String> errorMap = new HashMap<>();

    errorMap.put("time", ""+System.currentTimeMillis());
    errorMap.put("message", "No Such Element Exception");

    return ResponseEntity.badRequest().body(errorMap);
  }
}
