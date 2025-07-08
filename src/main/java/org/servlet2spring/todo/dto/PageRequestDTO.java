package org.servlet2spring.todo.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.util.Arrays;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

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

  private String type;  // 검색의 종류 : t, c, w, tc, tw, twc
  private String keyword;

  public String[] getTypes() {
    if (type == null || type.isEmpty()) {
      return null;
    }
    return type.split("");
  }

  public Pageable getPageable(String...props) {
    return PageRequest.of(this.page -1, this.size, Sort.by(props).descending());
  }

  private String link;

//  public int getSkip() {
//    return (page - 1) * size;
//  }

  public String getLink() {

    if (link == null) {
      StringBuilder builder = new StringBuilder();
      builder.append("page=" + this.page);
      builder.append("&size=" + this.size);

      if (type != null && type.length() > 0) {
        builder.append("&type=" + type);
      }

      if (keyword != null) {
        try {
          builder.append("&keyword=" + URLEncoder.encode(keyword, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
        }
      }

      link = builder.toString();
    }

    return link;
  }

      /*
    }
    StringBuilder builder = new StringBuilder();
    builder.append("page=" + this.page);
    builder.append("&size=" + this.size);

    if (finished) {
      builder.append("&finished=true");
    }

    if (types != null && types.length > 0) {
      for (int i = 0; i < types.length; i++) {
        builder.append("&types=" + types[i]);
      }
    }

    if (keyword != null) {
      try {
        builder.append("&keyword=" + URLEncoder.encode(keyword, "UTF-8"));
      } catch (UnsupportedEncodingException e) {
        e.printStackTrace();
      }
    }

    if (from != null) {
      builder.append("&from=" + from.toString());
    }

    if (to != null) {
      builder.append("&to=" + to.toString());
    }

    return builder.toString();
  }


  // 검색/필터링 기능에 필요한 변수 추가
  private String[] types;
  private String keyword;
  private boolean finished;
  private LocalDate from;
  private LocalDate to;

  // 제목(title), 작성자(writer)를 편리하게 관리하기 위한 메서드
  public boolean checkType(String type) {
    if (types == null || types.length == 0) {
      return false;
    }
    return Arrays.stream(types).anyMatch(type::equals);
  }
  */
}
