package com.hi.basic.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hi.basic.model.Member;

@RestController
public class RequestController {
	@GetMapping("req/param1")
	public String param1(@RequestParam("key1") String key1, @RequestParam("key2") String key2) {
		return key1 + ", " + key2;
	} // ������ Ű���� ���� �� ���� (key1, key2���� ���� ����)

	@GetMapping("req/param2")
	public String param2(@RequestParam Map<String, Object> map) {
		return map.toString();
	} // ���ϴ� Ű�� �����ؼ� ���� �� ����
		// ex) http://localhost:8080/req/param2?a=123&b=3r455&c=asdf
		// ����� = {a=123, b=3r455, c=asdf}

	@GetMapping("req/http")
	public String http(HttpServletRequest request) {
		String name = request.getParameter("name");
		String pageNum = request.getParameter("pageNum");
		return name + ", " + pageNum;
	}

	@GetMapping("req/model")
	public String model(@ModelAttribute Member member) {
		return member.toString();
	}
}