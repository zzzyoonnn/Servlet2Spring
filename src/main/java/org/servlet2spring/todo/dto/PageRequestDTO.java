package org.servlet2spring.todo.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageRequestDTO {

  @Builder.Default
  @Min(value = 1)
  @Positive
  private int page = 1;

  @Builder.Default
  @Min(value = 10)
  @Max(value = 100)
  @Positive
  private int size = 10;

  private String link;

  public int getSkip() {
    return (page - 1) * size;
  }

  public String getLink() {
    if (link == null) {
      StringBuilder builder = new StringBuilder();
      builder.append("page=" + this.page);
      builder.append("&size=" + this.size);
      link = builder.toString();
    }

    return link;
  }

  // 검색/필터링 기능에 필요한 변수 추가
  private String[] types;
  private String keyword;
  private boolean finished;
  private LocalDate from;
  private LocalDate to;
}
