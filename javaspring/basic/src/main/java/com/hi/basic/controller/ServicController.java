package com.hi.basic.controller; //카카오 api에 직접 입력하는 컨트롤러

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ServicController {
	@GetMapping("/kakao") 
	public String kakao() { //메소드 이름
		return "kakao"; //kakao.html
	}

	@GetMapping("/naver")
	public String naver() { //메소드 이름
		return "nave"; //naver.html
	}
}
