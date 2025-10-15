package org.servlet2spring.todo.controller;

import io.swagger.v3.oas.annotations.Operation;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.servlet2spring.todo.dto.upload.UploadFileDTO;
import org.servlet2spring.todo.util.LocalUploader;
import org.servlet2spring.todo.util.S3Uploader;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Log4j2
@RestController
@RequiredArgsConstructor
public class UpDownController {

  private final LocalUploader localUploader;
  private final S3Uploader s3Uploader;

  // 파일 업로드
  @Operation(summary = "Upload POST", description = "POST 방식으로 파일 등록")
  @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public List<String> upload(UploadFileDTO uploadFileDTO) {

    MultipartFile[] files = uploadFileDTO.getFiles();

    log.info("Uploaded files: {}", files);

    if (files == null || files.length <= 0) {
      return null;
    }

    List<String> uploadedFilePaths = new ArrayList<>();

    for (MultipartFile file : files) {
      uploadedFilePaths.addAll(localUploader.uploadLocal(file));
    }

    log.info("--------------------");
    log.info(uploadedFilePaths);

    List<String> s3Paths = uploadedFilePaths.stream().map(fileName -> s3Uploader.upload(fileName))
            .collect(Collectors.toList());

    return s3Paths;
  }

  // 첨부파일 조회
  @Operation(summary = "첨부파일 조회", description = "GET 방식으로 첨부파일 조회")
  @GetMapping("/view/{fileName}")
  public ResponseEntity<Void> viewFileGET(@PathVariable String fileName) {
//    String presignedUrl = 3Uploader.getFileUrl(fileName); // Presigned URL 생성
//
//    HttpHeaders headers = new HttpHeaders();
//    headers.add(HttpHeaders.LOCATION, presignedUrl);
//
//    // 브라우저에게 S3 URL로 다시 요청하라고 명령 (302 Found)
//    return new ResponseEntity<>(headers, HttpStatus.FOUND);
//  }
//  public ResponseEntity<Resource> viewFileGET(@PathVariable String fileName) {
//    Resource resource = new FileSystemResource(s3Uploader.getFileUrl(fileName));
//    log.info("경로: " + resource.getFilename());
//    HttpHeaders headers = new HttpHeaders();
//
//    try {
//      headers.add("Content-Type", Files.probeContentType(resource.getFile().toPath()));
//
//    } catch (IOException e) {
//      return ResponseEntity.internalServerError().build();
//    }
////  }
////
//    return ResponseEntity.ok().headers(headers).body(resource);
//  }
    // 1. S3Uploader를 사용하여 Presigned URL 생성 (String 반환)
    String presignedUrl = s3Uploader.getFileUrl(fileName);

    // 2. HTTP Headers에 Location 설정
    HttpHeaders headers = new HttpHeaders();
    headers.add(HttpHeaders.LOCATION, presignedUrl);

    log.info("S3 Presigned URL: " + presignedUrl);

    return new ResponseEntity<>(headers, HttpStatus.FOUND);
  }

    // 첨부파일 삭제
    @Operation(summary = "첨부파일 삭제", description = "DELETE 방식으로 파일 삭제")
    @DeleteMapping("/remove/{fileName}")
    public Map<String, Boolean> removeFile (@PathVariable String fileName){
      Resource resource = new FileSystemResource(localUploader.getUploadPath() + File.separator + fileName);

      Map<String, Boolean> resultMap = new HashMap<>();
      boolean removed = false;

      try {
        removed = resource.getFile().delete();

      } catch (IOException e) {
        log.error(e.getMessage());
      }

      resultMap.put("result", removed);

      return resultMap;
    }
  }
