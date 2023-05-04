package com.blog.apis.repo;

import org.springframework.data.repository.CrudRepository;

import com.blog.apis.Enitys.Category;


public interface Categoryrepo extends CrudRepository<Category, Integer> {
	
	

}
