package com.alkemy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alkemy.entity.Category;
import com.alkemy.entity.Post;
import com.alkemy.entity.User;
import com.alkemy.entity.UserResponse;
import com.alkemy.pojos.PostRequest;
import com.alkemy.repository.CategoryRepository;
import com.alkemy.repository.PostRepository;
import com.alkemy.repository.UserRepository;


@Service
public class PostService {
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	public List<Post> getAllPost() throws Exception{
		try {
			return postRepository.findAll();
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	public Post createPost(PostRequest postRequest) throws Exception{
		try {
			Category category = categoryRepository.findById(postRequest.categoria_id);
			User user = userRepository.findById(postRequest.usuario_id);
			Post post = new Post();
			post.setId(postRequest.id);
			post.setContenido(postRequest.contenido);
			post.setTitulo(postRequest.titulo);
			post.setImagen(postRequest.imagen);
			post.setFechaDeCreacion(postRequest.fechaDeCreacion);
			post.setCategoria(category);
			post.setUser(user);
			
			return postRepository.save(post);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

}
