package org.servlet2spring.todo.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.servlet2spring.todo.dto.BoardDTO;
import org.servlet2spring.todo.dto.BoardListReplyCountDTO;
import org.servlet2spring.todo.dto.PageRequestDTO;
import org.servlet2spring.todo.dto.PageResponseDTO;
import org.servlet2spring.todo.service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Log4j2
@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
  private final BoardService boardService;

  // 목록 기능
  @GetMapping("/list")
  public void list(PageRequestDTO pageRequestDTO, Model model) {
    // PageResponseDTO<BoardDTO> responseDTO = boardService.list(pageRequestDTO);
    PageResponseDTO<BoardListReplyCountDTO> responseDTO = boardService.listWithReplyCount(pageRequestDTO);
    log.info(responseDTO);
    model.addAttribute("responseDTO", responseDTO);
  }

  // 등록 기능
  @GetMapping("/register")
  public void registerGet(){

  }

  @ResponseBody
  @PostMapping("/register")
  public String registerPost(@Valid BoardDTO boardDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
    log.info("board POST register");

    if (bindingResult.hasErrors()) {
      log.info("has errors");
      redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());

      return "redirect:/board/register";
    }

    log.info(boardDTO);

    Long bno = boardService.register(boardDTO);
    redirectAttributes.addFlashAttribute("result", bno);

    return "redirect:/board/list";
  }

  // 조회 기능
  @GetMapping({"/read", "/modify"})
  public void read(Long bno, PageRequestDTO pageRequestDTO, Model model) {
    BoardDTO boardDTO = boardService.readOne(bno);
    log.info(boardDTO);
    model.addAttribute("dto", boardDTO);
  }

  // 수정 기능
  @ResponseBody
  @PostMapping("/modify")
  public String modify(PageRequestDTO pageRequestDTO, @Valid BoardDTO boardDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
    log.info("board modify post: " + boardDTO);

    if (bindingResult.hasErrors()) {
      log.info("has errors");

      String link = pageRequestDTO.getLink();
      redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
      redirectAttributes.addAttribute("bno", boardDTO.getBno());
      return "redirect:/board/modify?" + link;
    }

    boardService.modify(boardDTO);
    redirectAttributes.addFlashAttribute("result", "modified");
    redirectAttributes.addAttribute("bno", boardDTO.getBno());
    return "redirect:/board/read";
  }

  // 삭제 기능
  @ResponseBody
  @PostMapping("/remove")
  public String remove(Long bno, RedirectAttributes redirectAttributes) {
    log.info("remove post: " + bno);
    boardService.remove(bno);
    redirectAttributes.addFlashAttribute("result", "removed");
    return "redirect:/board/list";
  }
}
