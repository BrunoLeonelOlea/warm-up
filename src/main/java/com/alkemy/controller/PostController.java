package com.alkemy.controller;

import java.util.Map;

import org.hibernate.annotations.OnDelete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alkemy.entity.Post;
import com.alkemy.pojos.PostRequest;
import com.alkemy.service.PostService;

@RestController
@RequestMapping("/posts")
public class PostController {

	@Autowired
	private PostService postService;
	
	@GetMapping
	public ResponseEntity<?> getAllPost() throws Exception{
		try {
			return ResponseEntity.ok(postService.getAllPost());
		} catch (Exception e) {
			return ResponseEntity.status(404).body("{\"error\":\"" + e.getMessage() + "\"}");
		}
	}
	
	@PostMapping
	public ResponseEntity<?> createPost(@RequestBody PostRequest postRequest ){
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(postService.createPost(postRequest));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"" + e.getMessage() + "\"}");
		}
	}
	
	@GetMapping(params = "title")
	public ResponseEntity<?> findPostByTitle(@RequestParam String title){
		try {
			return ResponseEntity.status(200).body(postService.findPostByTitle(title));
		} catch (Exception e) {
			return ResponseEntity.status(404).body("{\"error\":\"" + e.getMessage() + "\"}");
		}
	}
	
	@GetMapping(params = "category")
	public ResponseEntity<?> findPostByCategory(@RequestParam String category){
		try {
			return ResponseEntity.status(200).body(postService.findPostByCategory(category));
		} catch (Exception e) {
			return ResponseEntity.status(404).body("{\"error\":\"" + e.getMessage() + "\"}");
		}
	}
	
	@GetMapping(params = {"title", "category"})
	public ResponseEntity<?> findPostByTitleAndCategory(@RequestParam String title, @RequestParam String category){
		try {
			return ResponseEntity.status(200).body(postService.findPostByTitleAndCategory(title, category));
		} catch (Exception e) {
			return ResponseEntity.status(404).body("{\"error\":\"" + e.getMessage() + "\"}");
		}
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<?> updatePost(@PathVariable long id, @RequestBody Post post){
		try {
			return ResponseEntity.status(204).body(postService.updatePost(id, post));
		} catch (Exception e) {
			return ResponseEntity.status(400).body("{\"error\":\"" + e.getMessage() + "\"}");
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletePost(@PathVariable long id){
		try {
			return ResponseEntity.status(204).body(postService.deletePost(id));
		} catch (Exception e) {
			return ResponseEntity.status(404).body("{\"error\":\"" + e.getMessage() + "\"}");
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> softDeletePost(@PathVariable long id){
		try {
			return ResponseEntity.status(204).body(postService.softDeletePost(id));
		} catch (Exception e) {
			return ResponseEntity.status(404).body("{\"error\":\"" + e.getMessage() + "\"}");
		}
	}
}
