package com.blog.apis.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.apis.Enitys.Category;
import com.blog.apis.Enitys.Post;
import com.blog.apis.Enitys.User;


public interface PostRepo extends JpaRepository<Post, Integer> {

		List<Post> findByUser(User user);
		List<Post> findByCategory(Category category);	
		List<Post> findByTittleContaining(String keyword);
		
	
}
