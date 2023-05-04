package com.blog.apis.playloads;


import java.util.List;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDto {

	private Integer id;
	@NotNull
	@NotEmpty
	@Length(min = 4, message = "Enter Tittle Length at lest 4 Charcters ")
	private String cattittle;
	@NotNull
	@NotEmpty
	private String catdic;

	List<PostDto> posts;

}
