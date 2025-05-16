package org.servlet2spring.servlet2spring.examples.springSample.exception;

import java.util.Arrays;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

@Log4j2
@ControllerAdvice
public class CommonExceptionAdvice {

  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ExceptionHandler(NoHandlerFoundException.class)
  public String notFound() {
    return "custom404";
  }

//  @ResponseBody
//  @ExceptionHandler(Exception.class)
//  public String exceptionCommon(Exception exception) {
//    log.error("---------------------------------");
//    log.error(exception.getMessage());
//
//    StringBuffer buffer = new StringBuffer("<ul>");
//
//    buffer.append("<li>" + exception.getMessage() + "</li>");
//
//    Arrays.stream(exception.getStackTrace()).forEach(stackTraceElement -> {
//      buffer.append("<li>" + stackTraceElement.toString() + "</li>");
//    });
//    buffer.append("</ul>");
//
//    return buffer.toString();
//  }


  @ResponseBody
  @ExceptionHandler(NumberFormatException.class)
  public String exceptionNumber(NumberFormatException numberFormatException) {
    log.error("---------------------------------");
    log.error(numberFormatException.getMessage());

    return "NUMBER FORMAT EXCEPTION";
  }
}