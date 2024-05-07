package com.project.mongodb.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.project.mongodb.domain.User;
import com.project.mongodb.repositories.UserRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner{
	
	@Autowired
	UserRepository userRepository;

	@Override
	public void run(String... args) throws Exception {
		
		userRepository.deleteAll();
		
		User iterbio = new User(null, "Iterbio Arantes", "iterbio7@gmail.com");
		User jessica = new User(null, "Jessica Gondim", "jessica10@gmail.com");
		User nina = new User(null, "Nina Grey", "nina@gmail.com");
		
		userRepository.saveAll(Arrays.asList(iterbio,jessica,nina));
		
	}

}
