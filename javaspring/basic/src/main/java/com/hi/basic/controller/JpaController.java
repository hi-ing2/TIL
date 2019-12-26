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

	@GetMapping("/jpa/product2") // ProductRepository.java / public Product findByName(String name); ����
	public Product product2(String name) {
		Product list = productRepository.findByName(name);

		return list; // �ϳ��� �����͸� ȣ�� (name ��ġ�� ����)
	}
	@GetMapping("/jpa/product3")
	public List<Product> product3(String name) {
		List<Product> list = productRepository.findAllByName(name);
		return list; // �ߺ��� ������ ȣ�� (name ���ĵ� ȣ��)
	}

	@GetMapping("/jpa/product")
	public List<Product> product() {
		List<Product> list = productRepository.findAll();
		return list;
	}

	@PostMapping("/jpa/product")
	public String productPost(@ModelAttribute Product product) {
		Product pro = productRepository.save(product);
		log.error(pro.toString()); // error�� string ��ĸ� ó���� �� �ִ�. �׷��� toString Ŭ������ ��� / �� ��Ҹ� ǥ��
		return "redirect:/jpa/product";
	}

}