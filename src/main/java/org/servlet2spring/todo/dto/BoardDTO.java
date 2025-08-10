package org.servlet2spring.todo.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardDTO {

  private Long bno;

  @NotEmpty
  @Size(min = 3, max = 100)
  private String title;

  @NotEmpty
  private String content;

  @NotEmpty
  private String writer;

  private LocalDateTime regDate;
  private LocalDateTime modDate;

  private List<String> fileNames;
}
