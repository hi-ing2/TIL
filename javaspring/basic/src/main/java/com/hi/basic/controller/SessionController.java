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
			HttpSession session) { // HttpSession 세션은 어디서든지 (현재 스프링 서버) 사용가능함
		session.setAttribute("user", user);
		return "redirect:/main"; //지정된주소로 이동(떠넘김)
	}

	@GetMapping("/main")
	public String main() {
		return "main";
	}
}