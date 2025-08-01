package org.servlet2spring.todo.dto.upload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UploadResultDTO {

  private String uuid;
  private String fileName;

  public String getLink() {
    return uuid + "_" + fileName;
  }
}
