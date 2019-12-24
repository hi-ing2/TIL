package com.hi.basic.controller;

import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.hi.basic.model.User;

@Controller
public class SessionController {
	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@PostMapping("/login")
	public String loginPost(
			User user, //@ModelAttribute("user") User user 
			HttpSession session) { // HttpSession ������ ��𼭵��� (���� ������ ����) ��밡����
		session.setAttribute("user", user);
		return "redirect:/main"; //�������ּҷ� �̵�(���ѱ�)
	}

	@GetMapping("/main")
	public String main() {
		return "main";
	}
}