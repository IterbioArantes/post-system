package com.project.mongodb.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.mongodb.domain.Post;
import com.project.mongodb.repositories.PostRepository;
import com.project.mongodb.resources.utils.URL;
import com.project.mongodb.services.exception.ObjectNotFoundException;

@Service
public class PostService {
	
	@Autowired
	PostRepository postRepository;
	
	
	public Post findById(String id) {
		
		Optional<Post> post = postRepository.findById(id);
		return post.orElseThrow(() -> new ObjectNotFoundException(id));
	}
	
	public List<Post> findByTitle(String text) {
		
		text = URL.decodeURL(text);
		
		return postRepository.findByTitleContainingIgnoreCase(text);
	}
}