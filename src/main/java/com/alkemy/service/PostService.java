package com.alkemy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alkemy.entity.Post;
import com.alkemy.repository.PostRepository;


@Service
public class PostService {
	
	@Autowired
	private PostRepository postRepository;
	
	public List<Post> getAllPost() throws Exception{
		try {
			return postRepository.findAll();
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	public Post createPost(Post post) throws Exception{
		try {
			return postRepository.save(post);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

}
