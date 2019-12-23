package com.hi.basic.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.hi.basic.model.Member;

@Controller
public class Json1Controller {
	//API Server
	@GetMapping("json/string")
	@ResponseBody  //기상청 날씨정보, 서버개발자 (서버에서 일방적 제공을 위한 작업) 일때 사용
	public String json() {
		return "json/string";
	}

	@GetMapping("json/map")
	@ResponseBody
	public Map<String, Object> jsonMap(Map<String, Object> map) {
		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("key1", "value");
		map2.put("key2", 2324);
		map2.put("key3", true);
		return map2;
	}

	@GetMapping("json/object")
	@ResponseBody
	public Member jsonObject() {
		Member member = new Member();
		member.setName("kim");
		return member;
	}

	@GetMapping("json/list")
	@ResponseBody
	public List<String> jsonList() {
		List<String> list = new ArrayList<>();
		list.add("1");
		list.add("2");
		list.add("3");
		return list;
	}
}