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
		List<MultipartFile> mFiles = mRequest.getFiles("file"); // list ���� ��ȯ
		for (int i = 0; i < mFiles.size(); i++) { // ���� ���� ���ε带 ���� for��
			MultipartFile mFile = mFiles.get(i); // mfile�ȿ� ���ε� �� ���� ������ ����
			String oName = mFile.getOriginalFilename(); //���纻 ���� �̸� �״��~
			result += oName + "\n";
			try {
				mFile.transferTo(new File("e:/dev/" + oName)); //�ٿ�ε� ���
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} // ���� ��ο� ���� ���ϸ����� ���� / ����ó���� ���� try catch�� ��ȯ
		}
		return result;
	}
}