package org.servlet2spring.todo.dto;

import java.util.List;
import lombok.Builder;

public class PageResponseDTO<E> {
  private int page;
  private int size;
  private int total;

  // 시작 페이지 번호
  private int start;
  // 끝 페이지 번호
  private int end;

  // 이전 페이지 존재 여부
  private boolean prev;
  // 다음 페이지 존재 여부
  private boolean next;

  private List<E> dtoList;

  @Builder
  public PageResponseDTO(PageRequestDTO pageRequestDTO, List<E> dtoList, int total) {
    this.page = pageRequestDTO.getPage();
    this.size = pageRequestDTO.getSize();

    this.total = total;
    this.dtoList = dtoList;
  }
}
