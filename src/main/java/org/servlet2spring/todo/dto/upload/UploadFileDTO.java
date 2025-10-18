package org.servlet2spring.todo.dto.upload;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class UploadFileDTO {

  private MultipartFile[] files;

}
