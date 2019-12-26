package com.hi.basic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hi.basic.model.Product;
// @Controller @Service @Repository @Component
@Repository
public interface ProductRepository extends JpaRepository<Product /*���̺��*/ , Long /*���̺��� ������ */> { //extends ���
	public Product findByName(String name); 
//		�ټ��̸� List�� �Է�
	public List<Product> findAllByName(String name); 
	}



