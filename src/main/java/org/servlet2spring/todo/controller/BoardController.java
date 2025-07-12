package org.servlet2spring.todo.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.servlet2spring.todo.dto.BoardDTO;
import org.servlet2spring.todo.dto.PageRequestDTO;
import org.servlet2spring.todo.dto.PageResponseDTO;
import org.servlet2spring.todo.service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Log4j2
@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
  private final BoardService boardService;

  @GetMapping("/list")
  public void list(PageRequestDTO pageRequestDTO, Model model) {
    PageResponseDTO<BoardDTO> responseDTO = boardService.list(pageRequestDTO);
    log.info(responseDTO);
    model.addAttribute("responseDTO", responseDTO);
  }
}
