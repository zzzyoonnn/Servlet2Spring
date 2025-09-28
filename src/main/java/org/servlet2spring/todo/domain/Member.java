package org.servlet2spring.todo.domain;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import java.util.HashSet;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "roleSet")
public class Member extends BaseEntity {

  @Id
  private String mid;

  private String mpw;
  private String email;
  private boolean del;

  private boolean social;

  @ElementCollection(fetch = FetchType.LAZY)
  @Builder.Default
  private Set<MemberRole> roleSet = new HashSet<>();

  public void changePassword(String mpw) {
    this.mpw = mpw;
  }

  public void changeEmail(String email) {
    this.email = email;
  }

  public void changeDel(boolean del) {
    this.del = del;
  }

  public void addRole(MemberRole role) {
    this.roleSet.add(role);
  }

  public void clearRoles() {
    this.roleSet.clear();
  }

  public void changeSocial(boolean social) {
    this.social = social;
  }
}
