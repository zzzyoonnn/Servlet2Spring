package org.servlet2spring.todo.repository;

import java.util.Optional;
import org.servlet2spring.todo.domain.Member;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MemberRepository extends JpaRepository<Member, String> {

  @EntityGraph(attributePaths = "roleSet")
  @Query("select m from Member m where m.mid = :mid and m.social = false")
  Optional<Member> getWithRoles(String mid);
}
