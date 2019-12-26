package com.hi.basic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

//@Controller //���� �������� �� �ҷ��ͼ� ������ �ʿ���� (�α��θ� �ϸ�Ǳ� ����)
public class LoginController {
	@GetMapping("/login")
	public String login() {
		return "login"; // templates/login.html�� ȣ��
	}
	@PostMapping("/login")
	@ResponseBody 
	public String loginPost( //������ ����� ���
			@RequestParam("id") String id,
			@RequestParam("pw") String pw) {
		
		String dbId = "boot";
		String dbPw = "1234";
		
		if(dbId.equals(id) && dbPw.equals(pw)) {
			return "�α��� ����";
		}
		return "�α��� ����";
	}
}