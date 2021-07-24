package com.alkemy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.alkemy.entity.Category;
import com.alkemy.entity.Post;


public interface PostRepository extends JpaRepository<Post, Long>{
	
	@Transactional(readOnly = true)
	public List<Post> findByTitulo(String title);
	
	public List<Post> findByCategoria(Category category);
	
	@Transactional
	@Modifying
	@Query("UPDATE Post p SET p.status = false WHERE p.id = :id")
	public void softDeletePost(@Param("id") long id);

}
