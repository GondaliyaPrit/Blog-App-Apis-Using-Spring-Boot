package com.blog.apis.Implimatation;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.apis.Enitys.Comment;
import com.blog.apis.Enitys.Post;
import com.blog.apis.Enitys.User;
import com.blog.apis.exaptions.ResourcenotfoundExaption;
import com.blog.apis.playloads.CommentDto;
import com.blog.apis.repo.PostRepo;
import com.blog.apis.repo.UserRepo;
import com.blog.apis.repo.commentrepo;
import com.blog.apis.services.commentservice;

@Service
public class commentserviceimpl implements commentservice {
	@Autowired
	commentrepo comrepo;

	@Autowired
	ModelMapper mapper;

	@Autowired
	PostRepo postrepo;
	@Autowired
	UserRepo userRepo;

	@Override
	public CommentDto adcomment(CommentDto commentDto, Integer postid, Integer userid) {
		// TODO Auto-generated method stub
		Post byId = this.postrepo.findById(postid)
				.orElseThrow(() -> new ResourcenotfoundExaption("post", "post id", postid));
		User user = this.userRepo.findById(userid)
				.orElseThrow(() -> new ResourcenotfoundExaption("User", "User id", userid));
		Comment comment = new Comment();
		comment.setPost(byId);
		comment.setUser(user);
		comment.setComment(commentDto.getComment());
		comment.setUsername(user.getName());
		Comment savecom = this.comrepo.save(comment);
		return this.mapper.map(savecom, CommentDto.class);
	}

	@Override
	public void deletecomment(Integer commentid) {
		
		 Comment comment = this.comrepo.findById(commentid).orElseThrow(()-> new ResourcenotfoundExaption("comment", "comment id", commentid));
	
			 this.comrepo.delete(comment);
		
		// TODO Auto-generated method stub
	}

}
