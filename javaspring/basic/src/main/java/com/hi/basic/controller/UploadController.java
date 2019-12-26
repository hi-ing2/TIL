package com.hi.basic.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Controller
public class UploadController {
	@GetMapping("/upload1")
	public String upload1() {
		return "upload1";
	}

	@PostMapping("/upload1")
	@ResponseBody
	public String upload1Post(MultipartHttpServletRequest mRequest) {
		String result = "";
		List<MultipartFile> mFiles = mRequest.getFiles("file"); // list 형태 반환
		for (int i = 0; i < mFiles.size(); i++) { // 여러 파일 업로드를 위해 for문
			MultipartFile mFile = mFiles.get(i); // mfile안에 업로드 된 파일 정보가 있음
			String oName = mFile.getOriginalFilename(); //복사본 파일 이름 그대로~
			result += oName + "\n";
			try {
				mFile.transferTo(new File("e:/dev/" + oName)); //다운로드 경로
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} // 지정 경로에 지정 파일명으로 저장 / 예외처리를 위해 try catch로 변환
		}
		return result;
	}
}