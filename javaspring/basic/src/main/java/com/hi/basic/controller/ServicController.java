package com.hi.basic.controller; //īī�� api�� ���� �Է��ϴ� ��Ʈ�ѷ�

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ServicController {
	@GetMapping("/kakao") 
	public String kakao() { //�޼ҵ� �̸�
		return "kakao"; //kakao.html
	}

	@GetMapping("/naver")
	public String naver() { //�޼ҵ� �̸�
		return "nave"; //naver.html
	}
}
