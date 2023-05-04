package com.blog.apis.playloads;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PaginationDto {

	private List<PostDto> postlist;
	private Integer pagenumber;
	private Integer pagesize;
	private Integer totalpages;
	private Long totalelement;
	private boolean lastpage;

}
