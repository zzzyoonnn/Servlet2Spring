package org.servlet2spring.servlet2spring.todo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberVO {

  private String mid;
  private String mpw;
  private String mname;
  private String uuid;
}
