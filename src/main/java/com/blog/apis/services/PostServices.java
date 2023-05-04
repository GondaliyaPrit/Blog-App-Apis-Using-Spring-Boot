package com.blog.apis.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.blog.apis.playloads.PaginationDto;
import com.blog.apis.playloads.PostDto;

public interface PostServices {

	PostDto createpost(PostDto postDto, Integer catid, Integer userid);

	PostDto updatepost(PostDto postDto, Integer postid);

	void deletepost(Integer postid);

	PostDto getpostbyid(Integer postid);

	List<PostDto> getpostbycategory(Integer categoryid);

	List<PostDto> getpostbyuser(Integer userid);

	List<PostDto> searchpost(String keyword);

	PaginationDto getallpostbypagination(Integer pageno, Integer listsize ,String sortBy);

}
