package com.blog.apis;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


import com.blog.apis.Enitys.Roles;
import com.blog.apis.config.Constantdata;
import com.blog.apis.repo.RoleRepo;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static java.util.stream.Collectors.toList;


@SpringBootApplication
public class BlogApplicationApisApplication implements  CommandLineRunner {

	@Autowired
	private PasswordEncoder passwordEncoder  ;
	@Autowired
	private RoleRepo roleRepo ;

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

		Roles role = new Roles() ;
		role.setId(Constantdata.ADMIN_USER);
		role.setName("ADMIN_USER");

		Roles role1 = new Roles() ;
		role1.setId(Constantdata.NORMAL_USER);
		role1.setName("NORMAL_USER");

		List<Roles> list = new ArrayList<>();
		list.add(role);
		list.add(role1);

		this.roleRepo.saveAll(list);
		for (Roles r:
				list) {
			System.out.println(r.getName());
		}


	}
}


