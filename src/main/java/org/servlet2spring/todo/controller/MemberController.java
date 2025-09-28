package org.servlet2spring.todo.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.servlet2spring.todo.dto.MemberJoinDTO;
import org.servlet2spring.todo.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Log4j2
@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

  // 의존성 주입
  private final MemberService memberService;

  @GetMapping("/login")
  public void loginGET(String error, String logout) {
    log.info("login get...");
    log.info("logout: " + logout);

    if (logout != null) {
      log.info("user logout...");
    }
  }

  @GetMapping("/join")
  public void joinGET() {
    log.info("join get...");
  }

  @PostMapping("/join")
  public String joinPOST(MemberJoinDTO memberJoinDTO, RedirectAttributes redirectAttributes) {
    log.info("join post...");
    log.info("memberJoinDTO: " + memberJoinDTO);

    try {
      memberService.join(memberJoinDTO);
    } catch (MemberService.MidExistException e) {
      redirectAttributes.addFlashAttribute("error", "mid");

      return "redirect:/member/join";
    }

    redirectAttributes.addFlashAttribute("result", "success");

    return "redirect:/member/login";  // 회원 가입 후 로그인
  }
}
