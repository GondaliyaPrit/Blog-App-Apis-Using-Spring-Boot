package com.blog.apis.Implimatation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.blog.apis.Enitys.Category;
import com.blog.apis.Enitys.Post;
import com.blog.apis.Enitys.User;
import com.blog.apis.exaptions.ResourcenotfoundExaption;
import com.blog.apis.playloads.PaginationDto;
import com.blog.apis.playloads.PostDto;
import com.blog.apis.repo.Categoryrepo;
import com.blog.apis.repo.PostRepo;
import com.blog.apis.repo.UserRepo;
import com.blog.apis.services.PostServices;

@Service
public class PostserviceImpl implements PostServices {

	@Autowired
	private PostRepo postRepo;
	@Autowired
	private ModelMapper mapper;
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private Categoryrepo categoryrepo;

	@Override
	public PostDto createpost(PostDto postDto, Integer catid, Integer userid) {
		// TODO Auto-generated method stub
		User user = this.userRepo.findById(userid)
				.orElseThrow(() -> new ResourcenotfoundExaption("User", "User id ", userid));
		Category category = this.categoryrepo.findById(catid)
				.orElseThrow(() -> new ResourcenotfoundExaption("Category", "Category id", catid));
		Post post = this.mapper.map(postDto, Post.class);
		post.setAdddate(new Date());
		post.setCategory(category);
		post.setUser(user);
		Post createpost = this.postRepo.save(post);
		return this.mapper.map(createpost, postDto.getClass());
	}

	@Override
	public PostDto updatepost(PostDto postDto, Integer postid) {
		Post post = this.postRepo.findById(postid)
				.orElseThrow(() -> new ResourcenotfoundExaption("post", "Post id", postid));
		post.setContext(postDto.getContext());
		post.setImagename(postDto.getImagename());
		post.setAdddate(new Date());
		post.setTittle(postDto.getTittle());
		Post update = this.postRepo.save(post);

		return this.mapper.map(update, postDto.getClass());
	}

	@Override
	public void deletepost(Integer postid) {

		Post post = this.postRepo.findById(postid)
				.orElseThrow(() -> new ResourcenotfoundExaption("Post", "Post Id", postid));
		this.postRepo.delete(post);

	}

	@Override
	public PostDto getpostbyid(Integer postid) {

		Post post = this.postRepo.findById(postid)
				.orElseThrow(() -> new ResourcenotfoundExaption("Post ", "Post Id", postid));

		// TODO Auto-generated method stub
		return this.mapper.map(post, PostDto.class);
	}

	@Override
	public List<PostDto> getpostbycategory(Integer categoryid) {
		Category cat = this.categoryrepo.findById(categoryid)
				.orElseThrow(() -> new ResourcenotfoundExaption("Category", "Category  id ", categoryid));
		List<Post> postlist = this.postRepo.findByCategory(cat);
		List<PostDto> postlists = new ArrayList<>();
		for (Post post : postlist) {
			postlists.add(this.mapper.map(post, PostDto.class));
		}
		return postlists;
	}

	@Override
	public List<PostDto> getpostbyuser(Integer userid) {
		User user = this.userRepo.findById(userid)
				.orElseThrow(() -> new ResourcenotfoundExaption("User", "User id", userid));
		List<Post> postlist = this.postRepo.findByUser(user);
		List<PostDto> postLists = new ArrayList<>();
		for (Post post : postlist) {
			postLists.add(this.mapper.map(post, PostDto.class));
		}
		return postLists;
	}

	@Override
	public List<PostDto> searchpost(String keyword) {
		// TODO Auto-generated method stub
		List<Post> posts = this.postRepo.findByTittleContaining(keyword);
		List<PostDto> postlist = new ArrayList<>();
		for (Post post : posts) {
			postlist.add(this.mapper.map(post, PostDto.class));
		}
		return postlist;
	}

	@Override
	public PaginationDto getallpostbypagination(Integer pageno, Integer pagesize, String sortBy) {

		Pageable pageable = PageRequest.of(pageno, pagesize, Sort.by(sortBy).ascending());
		Page<Post> findAll = this.postRepo.findAll(pageable);
		List<Post> allpost = findAll.getContent();
		List<PostDto> postlists = new ArrayList<>();
		for (Post post : allpost) {
			postlists.add(this.mapper.map(post, PostDto.class));
		}

		PaginationDto dto = new PaginationDto();
		dto.setPostlist(postlists);
		dto.setPagenumber(findAll.getNumber());
		dto.setPagesize(findAll.getSize());
		dto.setTotalelement(findAll.getTotalElements());
		dto.setTotalpages(findAll.getTotalPages());
		dto.setLastpage(findAll.isLast());
		return dto;
	}

}
