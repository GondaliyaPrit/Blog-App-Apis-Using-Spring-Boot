package com.blog.apis.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.blog.apis.config.Constantdata;
import com.blog.apis.playloads.PaginationDto;
import com.blog.apis.playloads.PostDto;
import com.blog.apis.services.Fileservice;
import com.blog.apis.services.PostServices;
import com.blog.apis.utils.Responcehandler;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class Postcontroller {

	@Autowired(required = true)
	private PostServices postServices;
	
	@Autowired(required = true)
	private Fileservice fileservice  ;
	
	@Value("${project.image}")
	private String path ;

	@PostMapping("/user/{userid}/category/{categoryid}/post")
	@ResponseBody
	public ResponseEntity<Object> createpost(@Valid @RequestBody PostDto postDto, @PathVariable Integer categoryid,
			@PathVariable Integer userid) {
		PostDto createpost = this.postServices.createpost(postDto, categoryid, userid);
		return Responcehandler.generateResponse("Post Uploded ", HttpStatus.CREATED, createpost);
	}

	@PutMapping("/user/{userid}/category/{categoryid}/post/{postid}")
	public ResponseEntity<Object> updatepost(@PathVariable Integer userid, @PathVariable Integer categoryid,
			@PathVariable Integer postid, @Valid @RequestBody PostDto postDto) {
		PostDto updatepost = this.postServices.updatepost(postDto, postid);
		return Responcehandler.generateResponse("Post Update Successfully", HttpStatus.OK, updatepost);
	}

	@PostMapping("/post/{postid}")
	public ResponseEntity<Object> getpostbyid(@PathVariable Integer postid) {
		PostDto dto = this.postServices.getpostbyid(postid);
		return Responcehandler.generateResponse("Get post Successfully", HttpStatus.OK, dto);
	}

	@DeleteMapping("/user/{userid}/category/{categoryid}/post/{postid}")
	public ResponseEntity<Object> deletepostbyid(@PathVariable Integer userid, @PathVariable Integer categoryid,
			@PathVariable Integer postid) {
		this.postServices.deletepost(postid);
		return Responcehandler.generateResponse("Post Delete Successfully", HttpStatus.ACCEPTED, postid);
	}

	@PostMapping("/category/{categoryid}/posts")

	public ResponseEntity<Object> getpostbycategory(@PathVariable Integer categoryid) {
		List<PostDto> postlists = this.postServices.getpostbycategory(categoryid);

		return Responcehandler.generateResponse(postlists.size() + " posts", HttpStatus.OK, postlists);
	}

	@PostMapping("/user/{userid}/posts")
	public ResponseEntity<Object> getpostbyuserid(@PathVariable Integer userid) {
		List<PostDto> userposrtlist = this.postServices.getpostbyuser(userid);

		return Responcehandler.generateResponse("Total " + userposrtlist.size() + " Post Found From User " + userid,
				HttpStatus.ACCEPTED, userposrtlist);
	}

	@PostMapping("/post")
	public ResponseEntity<Object> postbypaggination(
			@RequestParam(value = "pageno", required = false, defaultValue = Constantdata.PAGE_NUMBER) Integer pageno,
			@RequestParam(value = "listsize", required = false, defaultValue = Constantdata.PAGE_LIST_SIZE) Integer listsize
			, @RequestParam(value = "sortBy",required = false, defaultValue = Constantdata.SORT_BY) String  sortBy ){
		PaginationDto postlist = this.postServices.getallpostbypagination(pageno, listsize , sortBy);
		return Responcehandler.generateResponse("Get ", HttpStatus.OK, postlist);
	}
	
	@PostMapping("/post/search/{keyword}")
	public ResponseEntity<Object> searching(@PathVariable String keyword)
	{
		List<PostDto> searchpost = this.postServices.searchpost(keyword);
		return Responcehandler.generateResponse("Total "+searchpost.size()+" Found", HttpStatus.OK, searchpost) ;
	}
	
	@PostMapping( value = "/post/image/{postid}"    ) 
	public ResponseEntity<Object> imageuplode(@PathVariable Integer postid , @RequestParam("image")  MultipartFile  file ) throws IOException {
		 

		String filename = this.fileservice.Uplodefile(path, file);
PostDto getpostbyid = this.postServices.getpostbyid(postid);
		getpostbyid.setImagename(filename);
		PostDto updatepost = this.postServices.updatepost(getpostbyid, postid);
		return Responcehandler.generateResponse("Image uplode Successfully", HttpStatus.OK, updatepost);
	}
	


}
