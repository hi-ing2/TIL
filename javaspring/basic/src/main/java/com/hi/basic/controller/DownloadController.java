package com.hi.basic.controller;

import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DownloadController {
	@GetMapping("/download")
	public ResponseEntity<Resource> download() throws Exception {
		File file = new File("E:/다운로드/basic.zip"); //예시 경로 및 파일
		InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
		return ResponseEntity.ok() //response code : 200
				
				.header("content-disposition", "filename=" + URLEncoder.encode(file.getName(), "utf-8"))
				.contentLength(file.length()) // 파일 총크기
				// content-type => mime-type
//				.contentType(MediaType.parseMediaType("application/octet-stream")) // 바로 다운로드
				.contentType(MediaType.parseMediaType("image/jpeg")) // jpeg파일 다운로드 후 바로 출력
				.body(resource);
	}
}