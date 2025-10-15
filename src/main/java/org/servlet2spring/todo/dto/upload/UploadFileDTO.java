package org.servlet2spring.todo.dto.upload;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class UploadFileDTO {

//  @Schema(type = "string", format = "binary")
//  private List<MultipartFile> files;

  private MultipartFile[] files;

}
