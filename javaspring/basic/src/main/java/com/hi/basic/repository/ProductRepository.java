package com.hi.basic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hi.basic.model.Product;
// @Controller @Service @Repository @Component
@Repository
public interface ProductRepository extends JpaRepository<Product /*테이블명*/ , Long /*테이블의 고유값 */> { //extends 상속
	public Product findByName(String name); 
//		다수이면 List로 입력
	public List<Product> findAllByName(String name); 
	}



