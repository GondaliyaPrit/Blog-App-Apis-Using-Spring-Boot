package com.blog.apis.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.apis.Enitys.Comment;
public interface commentrepo  extends JpaRepository<Comment, Integer>{

}
