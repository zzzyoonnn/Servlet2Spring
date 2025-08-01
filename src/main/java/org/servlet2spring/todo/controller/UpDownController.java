package org.servlet2spring.todo.controller;

import io.swagger.v3.oas.annotations.Operation;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.extern.log4j.Log4j2;
import org.servlet2spring.todo.dto.upload.UploadResultDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Log4j2
@RestController
public class UpDownController {

  @Value("${org.servlet2spring.upload.path}")
  private String uploadPath;

  @Operation(summary = "Upload POST", description = "POST 방식으로 파일 등록")
  @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public List upload(@RequestPart("files") List<MultipartFile> files) {
    log.info("Uploaded files: {}", files);

    if (files != null) {
      final List<UploadResultDTO> resultList = new ArrayList<>();
      files.forEach(file -> {

        String fileName = file.getOriginalFilename();
        String uuid = UUID.randomUUID().toString();

        Path savePath = Paths.get(uploadPath, uuid + "" + fileName);

        try {
          file.transferTo(new File(String.valueOf(savePath))); // 실제 파일 저장
          log.info(file.getOriginalFilename());

        } catch (IOException e) {
          e.printStackTrace();
        }

        resultList.add(UploadResultDTO.builder()
                .uuid(uuid)
                .fileName(fileName)
                .build());
      });

      return resultList;
    }

    return null;
  }

  @Operation(summary = "첨부파일 조회", description = "GET 방식으로 첨부파일 조회")
  @GetMapping("/view/{fileName}")
  public ResponseEntity<Resource> viewFileGET(@PathVariable String fileName) {
    Resource resource = new FileSystemResource(uploadPath + File.separator + fileName);
    HttpHeaders headers = new HttpHeaders();

    try {
      headers.add("Content-Type", Files.probeContentType(resource.getFile().toPath()));

    } catch (IOException e) {
      return ResponseEntity.internalServerError().build();
    }

    return ResponseEntity.ok().headers(headers).body(resource);
  }
}
