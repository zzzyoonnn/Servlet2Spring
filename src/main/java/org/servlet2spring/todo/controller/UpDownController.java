package org.servlet2spring.todo.controller;

import io.swagger.v3.oas.annotations.Operation;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.servlet2spring.todo.dto.upload.UploadFileDTO;
import org.servlet2spring.todo.util.LocalUploader;
import org.servlet2spring.todo.util.S3Uploader;
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

  // 파일 업로드 - S3 URL 직접 반환
  @Operation(summary = "Upload POST", description = "POST 방식으로 파일 등록")
  @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public List<Map<String, String>> upload(UploadFileDTO uploadFileDTO) {

    MultipartFile[] files = uploadFileDTO.getFiles();
    log.info("Uploaded files: {}", files);

    if (files == null || files.length <= 0) {
      return null;
    }

    List<Map<String, String>> uploadResults = new ArrayList<>();

    for (MultipartFile file : files) {
      // 로컬에 임시 저장
      List<String> uploadedFilePaths = localUploader.uploadLocal(file);

      for (String filePath : uploadedFilePaths) {
        // S3에 업로드하고 URL 받기
        String s3Url = s3Uploader.upload(filePath);

        // 파일명 추출 (uuid_원본파일명)
        String fileName = new File(filePath).getName();
        String uuid = fileName.substring(0, fileName.indexOf("_"));
        String originalName = fileName.substring(fileName.indexOf("_") + 1);

        Map<String, String> fileInfo = new HashMap<>();
        fileInfo.put("uuid", uuid);
        fileInfo.put("fileName", originalName);
        fileInfo.put("link", s3Url);  // S3 presigned URL 직접 사용

        uploadResults.add(fileInfo);
      }
    }

    log.info("Upload results: {}", uploadResults);
    return uploadResults;
  }

  @Operation(summary = "첨부파일 조회", description = "GET 방식으로 첨부파일 조회")
  @GetMapping("/view/{fileName}")
  public ResponseEntity<Void> viewFileGET(@PathVariable String fileName) {
    String fileUrl = s3Uploader.getFileUrl(fileName);
    return ResponseEntity.status(HttpStatus.FOUND)
            .header(HttpHeaders.LOCATION, fileUrl)
            .build();
  }

  // 첨부파일 삭제 - S3에서도 삭제하도록 수정
//  @Operation(summary = "첨부파일 삭제", description = "DELETE 방식으로 파일 삭제")
//  @DeleteMapping("/remove/{fileName}")
//  public Map<String, Boolean> removeFile(@PathVariable String fileName) {
//    Map<String, Boolean> resultMap = new HashMap<>();
//    boolean removed = false;
//
//    try {
//      // S3에서 삭제
//      s3Uploader.removeS3File(fileName);
//
//      // 로컬 파일도 삭제 시도 (이미 삭제되었을 수 있음)
//      Resource resource = new FileSystemResource(
//              localUploader.getUploadPath() + File.separator + fileName);
//      if (resource.exists()) {
//        resource.getFile().delete();
//      }
//
//      removed = true;
//    } catch (Exception e) {
//      log.error("Error removing file: {}", e.getMessage());
//    }
//
//    resultMap.put("result", removed);
//    return resultMap;
//  }

  @Operation(summary = "첨부파일 삭제", description = "DELETE 방식으로 파일 삭제")
  @DeleteMapping("/remove/{fileName}")
  public Map<String, Boolean> removeFile(@PathVariable String fileName) {

    log.info("Delete file request: {}", fileName);

    Map<String, Boolean> resultMap = new HashMap<>();
    boolean removed = false;

    try {
      // S3에서 파일 삭제 - 파일명만 전달 (uuid_originalFileName.jpg)
      s3Uploader.removeS3File(fileName);
      removed = true;
      log.info("Successfully deleted file from S3: {}", fileName);

    } catch (Exception e) {
      log.error("Error deleting file from S3: {}", e.getMessage());
      removed = false;
    }

    resultMap.put("result", removed);

    return resultMap;
  }
}