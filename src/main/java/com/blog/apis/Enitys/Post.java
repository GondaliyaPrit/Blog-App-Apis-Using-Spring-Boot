package com.blog.apis.Enitys;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Posts")
@NoArgsConstructor
@Getter
@Setter
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private  Integer  postid;
	@Column(nullable = false)
	private String tittle;
	@Column
	private String context;
	@Column
	private String imagename;
	private Date adddate;
	
	@ManyToOne
	private Category category;

	@ManyToOne
	private User user;
	
	@JsonIgnore
	@OneToMany(mappedBy = "post")
	private List<Comment>  comments = new ArrayList<>();
	
	
	

}
