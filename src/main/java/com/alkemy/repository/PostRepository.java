package com.alkemy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.alkemy.entity.Category;
import com.alkemy.entity.Post;


public interface PostRepository extends JpaRepository<Post, Long>{
	
	@Transactional(readOnly = true)
	List<Post> findByTitulo(String title);
	
	List<Post> findByCategoria(Category category);
	
	

}
