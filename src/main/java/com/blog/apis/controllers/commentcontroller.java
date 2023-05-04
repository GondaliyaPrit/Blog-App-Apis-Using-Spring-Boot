package com.blog.apis.controllers;

import org.aspectj.weaver.tools.Trace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.apis.Enitys.Comment;
import com.blog.apis.playloads.CommentDto;
import com.blog.apis.services.commentservice;
import com.blog.apis.utils.Responcehandler;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api")
public class commentcontroller{
	
		@Autowired(required = true)
		private commentservice commentservice ;
		
		@PostMapping("user/{userid}/post/{postid}/comment")
		public ResponseEntity<Object> comcreate( @Valid @RequestBody CommentDto commentDto , @PathVariable Integer postid,@PathVariable Integer userid)
		{
			CommentDto adcomment = this.commentservice.adcomment(commentDto, postid,userid);
			return Responcehandler.generateResponse("Comment Done", HttpStatus.OK, adcomment) ;
		}
		
		@DeleteMapping("/comment/{commentid}")
		public  ResponseEntity<Object> delcomment(@PathVariable  Integer commentid)
		{
			this.commentservice.deletecomment(commentid);
			return Responcehandler.generateResponse("Comment  Remove successfully", HttpStatus.OK, commentid);
		}
}
