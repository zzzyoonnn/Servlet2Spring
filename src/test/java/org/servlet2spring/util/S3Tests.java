package org.servlet2spring.util;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.servlet2spring.todo.util.S3Uploader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Log4j2
@SpringBootTest
public class S3Tests {

  @Autowired
  private S3Uploader s3Uploader;

  @Test
  public void testUpload() {
    try {
      String filePath = "/Users/jiyoon/Desktop/poster/1.jpeg";

      String uploadName = s3Uploader.upload(filePath);

      log.info(uploadName);
    } catch (Exception e) {
      log.error(e);
    }
  }
}
