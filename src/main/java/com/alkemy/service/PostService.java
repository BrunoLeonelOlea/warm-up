package com.alkemy.service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import com.alkemy.dto.PostDto;
import com.alkemy.entity.Category;
import com.alkemy.entity.Post;
import com.alkemy.entity.User;
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
	
	public List<PostDto> getAllPost() throws Exception{
		try {
			List<Post> posts = postRepository.findAll();
			List<PostDto> postDtos = new ArrayList<>();
			ModelMapper modelMapper = new ModelMapper();
			for (Post post : posts) {
				postDtos.add(modelMapper.map(post, PostDto.class));
			}
			return postDtos;
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

	public List<Post> findPostByTitle(String title) throws Exception{
		try {
			List<Post> posts = postRepository.findByTitulo(title);
			return posts;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	public List<Post> findPostByCategory(String category)throws Exception{
		try {
			Category categoria = categoryRepository.findByNombre(category);
			List<Post> posts = postRepository.findByCategoria(categoria);
			
			return posts;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public List<Post> findPostByTitleAndCategory(String title, String category) throws Exception {
		try {
			Category categoria = categoryRepository.findByNombre(category);
			List<Post> posts = postRepository.findByTitulo(title);
			
			List<Post> filterPosts = new ArrayList<Post>();
			for (Post post : posts) {
				
				if (categoria.equals(post.getCategoria())) {
					filterPosts.add(post);
				}
			}
			return filterPosts;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	public Post findById(long id)throws Exception {
		try {
			return postRepository.findById(id).get();
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	public Post updatePost(long id, Post post) throws Exception{
		try {
			Post post1 = postRepository.findById(id).get();
				if (post.getTitulo() != null) {
					post1.setTitulo(post.getTitulo());
				}
				if (post.getImagen() != null) {
					post1.setImagen(post.getImagen());
				}
				if (post.getContenido() != null) {
					post1.setContenido(post.getContenido());
				}
				if (post.getFechaDeCreacion() != null) {
					post1.setFechaDeCreacion(post.getFechaDeCreacion());
				}
				if (post.getCategoria() != null) {
					post1.setCategoria(post.getCategoria());
				}
				
			
			return postRepository.save(post1);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public boolean deletePost(long id) throws Exception{
		try {
			if (postRepository.existsById(id)) {
				postRepository.deleteById(id);
			}
			return true;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
}
