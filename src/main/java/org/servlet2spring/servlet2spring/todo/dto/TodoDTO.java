package org.servlet2spring.servlet2spring.todo.dto;

import java.time.LocalDate;

public class TodoDTO {
  private Long no;
  private String title;
  private LocalDate dueDate;
  private boolean finished;

  public Long getNo() {
    return no;
  }

  public void setNo(Long no) {
    this.no = no;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public LocalDate getDueDate() {
    return dueDate;
  }

  public void setDueDate(LocalDate dueDate) {
    this.dueDate = dueDate;
  }

  public boolean isFinished() {
    return finished;
  }

  public void setFinished(boolean finished) {
    this.finished = finished;
  }

  @Override
  public String toString() {
    return "TodoDTO [no=" + no + ", title='" + title + '\'' + ", dueDate=" + dueDate + ", finished=" + finished + "]";
  }
}
