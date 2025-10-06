package org.servlet2spring.todo.repository;

import jakarta.transaction.Transactional;
import java.util.Optional;
import org.apache.ibatis.annotations.Param;
import org.servlet2spring.todo.domain.Member;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface MemberRepository extends JpaRepository<Member, String> {

  @EntityGraph(attributePaths = "roleSet")
  @Query("select m from Member m where m.mid = :mid and m.social = false")
  Optional<Member> getWithRoles(String mid);

  @EntityGraph(attributePaths = "roleSet")
  Optional<Member> findByEmail(String email);

  @Modifying
  @Transactional
  @Query("update Member m set m.mpw = :mpw where m.mid = :mid")
  void updatePassword(@Param("mpw") String mpw, @Param("mid") String mid);
}
