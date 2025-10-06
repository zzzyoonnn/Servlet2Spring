package org.servlet2spring.repository;

import java.util.Optional;
import java.util.stream.IntStream;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.servlet2spring.todo.domain.Member;
import org.servlet2spring.todo.domain.MemberRole;
import org.servlet2spring.todo.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Commit;

@Log4j2
@SpringBootTest
public class MemberRepositoryTests {

  @Autowired
  private MemberRepository memberRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Test
  public void insertMembers() {
    IntStream.rangeClosed(1, 100).forEach(i -> {

      Member member = Member.builder()
              .mid("member" + i)
              .mpw(passwordEncoder.encode("1111"))
              .email("email" + i + "@aaa.com")
              .build();

      member.addRole(MemberRole.USER);

      if (i >= 90) {
        member.addRole(MemberRole.ADMIN);
      }

      memberRepository.save(member);
    });
  }

  @Test
  public void testRead() {
    Optional<Member> result = memberRepository.getWithRoles("member100");

    Member member = result.orElseThrow();

    log.info(member);
    log.info(member.getRoleSet());

    member.getRoleSet().forEach(memberRole -> log.info(memberRole.name()));
  }

  @Commit
  @Test
  public void testUpdate() {
    String mid = "cookie_00@naver.com";   // 소셜 로그인으로 추가된 사용자로 현재 DB에 존재하는 이메일
    String mpw = passwordEncoder.encode("1111");

    memberRepository.updatePassword(mpw, mid);
  }
}
