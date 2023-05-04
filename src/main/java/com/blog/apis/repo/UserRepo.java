package com.blog.apis.repo;


import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.apis.Enitys.User;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Integer>{

    Optional<User> findByEmail(String email);
}
