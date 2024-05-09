package com.project.mongodb.config;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.project.mongodb.domain.Post;
import com.project.mongodb.domain.User;
import com.project.mongodb.dto.AuthorDTO;
import com.project.mongodb.dto.CommentDTO;
import com.project.mongodb.repositories.PostRepository;
import com.project.mongodb.repositories.UserRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner{
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PostRepository postRepository;

	@Override
	public void run(String... args) throws Exception {
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		userRepository.deleteAll();
		postRepository.deleteAll();
		
		User iterbio = new User(null, "Iterbio Arantes", "iterbio7@gmail.com");
		User jessica = new User(null, "Jessica Gondim", "jessica10@gmail.com");
		User nina = new User(null, "Nina Grey", "nina@gmail.com");
		
		userRepository.saveAll(Arrays.asList(iterbio,jessica,nina));
		
		Post post1 = new Post(null, LocalDate.parse("21/03/2018",dtf), "Partiu viagem", "Vou viajar para São Paulo. Abraços!", new AuthorDTO(iterbio));
		Post post2 = new Post(null, LocalDate.parse("23/03/2018",dtf), "Bom dia", "Acordei feliz hoje!", new AuthorDTO(iterbio));
		
		CommentDTO c1 = new CommentDTO("Boa viagem mano!", LocalDate.parse("21/03/2018",dtf),new AuthorDTO(jessica));
		CommentDTO c2 = new CommentDTO("Aproveite!", LocalDate.parse("22/03/2018",dtf),new AuthorDTO(nina));
		CommentDTO c3 = new CommentDTO("Tenha um ótimo dia!", LocalDate.parse("21/03/2018",dtf),new AuthorDTO(jessica));
		
		post1.getcomments().add(c1);
		post1.getcomments().add(c2);
		post2.getcomments().add(c3);
		
		postRepository.saveAll(Arrays.asList(post1,post2));
		
		iterbio.getPosts().addAll(Arrays.asList(post1,post2));
		
		userRepository.save(iterbio);
		
	}

}
