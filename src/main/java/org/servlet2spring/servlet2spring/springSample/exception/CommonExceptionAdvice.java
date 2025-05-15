package org.servlet2spring.servlet2spring.springSample.exception;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@Log4j2
@ControllerAdvice
public class CommonExceptionAdvice {

  @ResponseBody
  @ExceptionHandler(NumberFormatException.class)
  public String exceptionHandler(NumberFormatException numberFormatException) {
    log.error("---------------------------------");
    log.error(numberFormatException.getMessage());

    return "NUMBER FORMAT EXCEPTION";
  }
}