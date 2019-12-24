package com.hi.basic.controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.hi.basic.model.Member;

@Controller
public class HtmlController {
	@GetMapping("html/string")
	public String html() { //string 방식을 반환
		return "html/string";
	}

	@GetMapping("html/void")
	public void htmlVoid() { //void 방식을 반환
		}

	@GetMapping("html/map")
	public Map<String, Object> htmlMap(Map<String, Object> map) { //Map 방식을 반환
		Map<String, Object> map2 = new HashMap<String, Object>();
		return map2;
	}

	@GetMapping("html/model")
	public Model htmlModel(Model model) {
		return model;
	}

	@GetMapping("html/model_and_view")
	public ModelAndView htmlModel() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("html/model_and_view");
		return mav;
	}

	@GetMapping("html/object")
	@ResponseBody
	public Member htmlObject() {
		Member member = new Member();
		member.setName("kim");
		return member;
	}
}