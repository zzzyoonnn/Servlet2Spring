package org.servlet2spring.todo.dto;

import java.util.Collection;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

@Getter
@Setter
@ToString
public class MemberSecurityDTO extends User {

  private String mid;
  private String mpw;
  private String email;
  private boolean del;
  private boolean social;

  public MemberSecurityDTO(String username, String password, String email,
                           boolean del, boolean social,
                           Collection<? extends GrantedAuthority> authorities) {

    super(username, password, authorities);

    this.mid = username;
    this.mpw = password;
    this.email = email;
    this.del = del;
    this.social = social;
  }
}
