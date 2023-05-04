package com.blog.apis.services;



import com.blog.apis.playloads.CommentDto;

public interface commentservice{
	//create 
	public CommentDto adcomment(CommentDto commentDto , Integer postid,Integer userid);
	//delete 
	public void deletecomment(Integer commentid);
	

}
