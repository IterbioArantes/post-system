package com.project.mongodb.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.project.mongodb.domain.Post;
import com.project.mongodb.domain.User;
import com.project.mongodb.dto.UserDTO;
import com.project.mongodb.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResource {
	
	
	@Autowired
	UserService userService;
	
	@GetMapping
	public ResponseEntity<List<UserDTO>> findAll(){
	
		List<User> list = userService.findAll();
		List<UserDTO> listDTO = list.stream().map(x -> new UserDTO(x)).toList();
		
		return ResponseEntity.ok().body(listDTO);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<UserDTO> findById(@PathVariable String id){
		
		UserDTO userDTO = new UserDTO(userService.findById(id));
		
		return ResponseEntity.ok().body(userDTO);
	}
	
	@PostMapping
	public ResponseEntity<UserDTO> insert(@RequestBody UserDTO userDTO){
		
		User user = userService.insert(UserDTO.fromDTO(userDTO));
		UserDTO userDTOInserted = new UserDTO(user);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
		
		return ResponseEntity.created(uri).body(userDTOInserted);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable String id){
		
		userService.delete(id);
		
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<UserDTO> update(@PathVariable String id, @RequestBody UserDTO userDTO){
		
		User user = UserDTO.fromDTO(userDTO);
		UserDTO userDTOUpdated = new UserDTO(userService.update(id, user));
		
		return ResponseEntity.ok().body(userDTOUpdated);
	}
	
	@GetMapping(value = "/{id}/posts")
	public ResponseEntity<List<Post>> findPostsByUser(@PathVariable String id){
		
		List<Post> listPost =  userService.findById(id).getPosts();
		
		return ResponseEntity.ok().body(listPost);
	}
}