package org.servlet2spring.todo.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReplyDTO {

  private Long rno;

  @NotNull
  private Long bno;

  @NotEmpty
  private String replyText;

  @NotEmpty
  private String replier;

  private LocalDateTime regDate, modDate;
}
