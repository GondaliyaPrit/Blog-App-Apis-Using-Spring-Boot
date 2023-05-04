package com.blog.apis.playloads;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.blog.apis.Enitys.Category;
import com.blog.apis.Enitys.Comment;
import com.blog.apis.Enitys.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class PostDto {

	private Integer postid;

	@NotEmpty
	@NotNull
	private String tittle;

	@NotEmpty
	@NotNull
	private String context;

	@NotEmpty
	@NotNull
	private String imagename;
	private Date adddate;

	private Category category;
	
	private User user;
	
	private List<CommentDto>  comments = new ArrayList<>();
	
	
	
	
	
	

	

}
