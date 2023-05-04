package com.blog.apis.Enitys;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "Comment")
public class Comment {
	
	@Id 
	@GeneratedValue(strategy =  GenerationType.AUTO)
	private int commentid  ;
	
	private String  comment;
	private String username;
	
	@ManyToOne
	private Post post  ;

	@ManyToOne
	private User user ;
	
	
}
