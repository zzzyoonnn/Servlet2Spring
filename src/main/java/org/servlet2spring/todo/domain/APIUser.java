package org.servlet2spring.todo.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class APIUser {

  @Id
  private String mid;
  private String mpw;

  public void changePw(String mpw) {
    this.mpw = mpw;
  }

}
