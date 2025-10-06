package org.servlet2spring.todo.security;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.servlet2spring.todo.domain.Member;
import org.servlet2spring.todo.domain.MemberRole;
import org.servlet2spring.todo.repository.MemberRepository;
import org.servlet2spring.todo.security.dto.MemberSecurityDTO;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

  private final MemberRepository memberRepository;
  private final PasswordEncoder passwordEncoder;

  @Override
  public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
    log.info("userRequest...");
    log.info("{}", userRequest);

    log.info("oauth user --------------------");

    ClientRegistration clientRegistration = userRequest.getClientRegistration();
    String clientName = clientRegistration.getClientName();

    log.info("Name: " + clientName);

    OAuth2User oAuth2User = super.loadUser(userRequest);
    Map<String, Object> paramMap = oAuth2User.getAttributes();

//    paramMap.forEach((key, value) -> {
//      log.info("--------------------");
//      log.info("key: " + key + ", value: " + value);
//    });

    String email = null;

    switch (clientName) {
      case "kakao":
        email = getKakaoEmail(paramMap);
        break;
    }

    log.info("email: " + email);

    return generateDTO(email, paramMap);
  }

  private MemberSecurityDTO generateDTO(String email, Map<String, Object> paramMap) {

    Optional<Member> result = memberRepository.findByEmail(email);

    // 데이터베이스에 해당 이메일 사용자가 없다면
    if (result.isEmpty()) {
      // 회원 추가
      Member member = Member.builder()
              .mid(email)
              .mpw(passwordEncoder.encode("1111"))
              .email(email)
              .social(true)
              .build();

      member.addRole(MemberRole.USER);
      memberRepository.save(member);

      // MemberSecurityDTO 구성 및 반환
      MemberSecurityDTO memberSecurityDTO = new MemberSecurityDTO(email, "1111", email, false, true, Arrays.asList(new SimpleGrantedAuthority("ROLE_USER")));
      memberSecurityDTO.setProps(paramMap);

      return memberSecurityDTO;
    } else {

      Member member = result.get();
      MemberSecurityDTO memberSecurityDTO = new MemberSecurityDTO(
              member.getMid(),
              member.getMpw(),
              member.getEmail(),
              member.isDel(),
              member.isSocial(),
              member.getRoleSet().stream().map(memberRole -> new SimpleGrantedAuthority("ROLE_" + memberRole.name())).collect(
                      Collectors.toList())
      );

      return memberSecurityDTO;
    }
  }

  private String getKakaoEmail(Map<String, Object> paramMap) {
    log.info("Kakao --------------------");

    Object value = paramMap.get("kakao_account");

    log.info(value);

    LinkedHashMap accountMap = (LinkedHashMap) value;
    String email = (String) accountMap.get("email");
    log.info("email: " + email);

    return email;
  }
}