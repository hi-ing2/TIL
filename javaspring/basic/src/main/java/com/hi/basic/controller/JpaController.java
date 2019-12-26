package com.hi.basic.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hi.basic.model.Product; // model/Product.java
import com.hi.basic.repository.ProductRepository; // repository/ProductRepository.java

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class JpaController {
	@Autowired
	ProductRepository productRepository;

	@GetMapping("/jpa/product2") // ProductRepository.java / public Product findByName(String name); 연동
	public Product product2(String name) {
		Product list = productRepository.findByName(name);

		return list; // 하나의 데이터만 호출 (name 겹치면 오류)
	}
	@GetMapping("/jpa/product3")
	public List<Product> product3(String name) {
		List<Product> list = productRepository.findAllByName(name);
		return list; // 중복된 데이터 호출 (name 겹쳐도 호출)
	}

	@GetMapping("/jpa/product")
	public List<Product> product() {
		List<Product> list = productRepository.findAll();
		return list;
	}

	@PostMapping("/jpa/product")
	public String productPost(@ModelAttribute Product product) {
		Product pro = productRepository.save(product);
		log.error(pro.toString()); // error는 string 방식만 처리할 수 있다. 그래서 toString 클래스를 사용 / 들어간 요소를 표시
		return "redirect:/jpa/product";
	}

}