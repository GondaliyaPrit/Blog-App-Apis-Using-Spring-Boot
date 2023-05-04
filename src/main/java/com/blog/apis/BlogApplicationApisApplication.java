package com.blog.apis;

import java.text.SimpleDateFormat;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.security.crypto.password.PasswordEncoder;


@SpringBootApplication
public class BlogApplicationApisApplication implements  CommandLineRunner {

	@Autowired
	private PasswordEncoder passwordEncoder  ;
	public static void main(String[] args) {
		SpringApplication.run(BlogApplicationApisApplication.class, args);



	}

	
	@Bean
	public ModelMapper modelMapper() {
	 return new ModelMapper();
	}
	
	
	@Bean
	public Jackson2ObjectMapperBuilder jacksonBuilder() {
		Jackson2ObjectMapperBuilder b = new Jackson2ObjectMapperBuilder();
		b.indentOutput(true).dateFormat(new SimpleDateFormat("yyyy-MM-dd"));
		return b;
	}


	@Override
	public void run(String... args) throws Exception {
		System.out.println(this.passwordEncoder.encode("test"));
	}
}


