package com.alkemy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alkemy.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long>{

}
