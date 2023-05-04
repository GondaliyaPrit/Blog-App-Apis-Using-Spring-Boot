package com.blog.apis.playloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentDto {
	private int commentid;
	private String username;
	@NotEmpty
	@NotNull
	private String comment;
}
