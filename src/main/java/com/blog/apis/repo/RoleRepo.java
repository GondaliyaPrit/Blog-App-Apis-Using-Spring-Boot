package com.blog.apis.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.apis.Enitys.Roles;

public interface RoleRepo extends JpaRepository<Roles, Integer> {
	

}
