package org.servlet2spring.servlet2spring.springSample.exception;

import java.util.Arrays;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@Log4j2
@ControllerAdvice
public class CommonExceptionAdvice {

  @ResponseBody
  @ExceptionHandler(Exception.class)
  public String exceptionCommon(Exception exception) {
    log.error("---------------------------------");
    log.error(exception.getMessage());

    StringBuffer buffer = new StringBuffer("<ul>");

    buffer.append("<li>" + exception.getMessage() + "</li>");

    Arrays.stream(exception.getStackTrace()).forEach(stackTraceElement -> {
      buffer.append("<li>" + stackTraceElement.toString() + "</li>");
    });
    buffer.append("</ul>");

    return buffer.toString();
  }


  @ResponseBody
  @ExceptionHandler(NumberFormatException.class)
  public String exceptionNumber(NumberFormatException numberFormatException) {
    log.error("---------------------------------");
    log.error(numberFormatException.getMessage());

    return "NUMBER FORMAT EXCEPTION";
  }
}