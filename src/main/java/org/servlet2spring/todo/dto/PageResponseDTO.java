package org.servlet2spring.todo.dto;

import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
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

  @Builder(builderMethodName = "withAll")
  public PageResponseDTO(PageRequestDTO pageRequestDTO, List<E> dtoList, int total) {

    if (total <= 0) return;

    this.page = pageRequestDTO.getPage();
    this.size = pageRequestDTO.getSize();

    this.total = total;
    this.dtoList = dtoList;

    // 마지막 페이지/시작 페이지 번호 계산
    this.end = (int)(Math.ceil(this.page / 10.0)) * 10;
    this.start = this.end - 9;
    int last = (int)(Math.ceil((total / (double)size)));
    this.end = end > last ? last : end;

    // 이전(prev)/다음(next) 페이지 계산
    this.prev = this.start > 1;
    this.next = total > this.end * this.size;
  }
}
