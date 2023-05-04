package com.blog.apis.Implimatation;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.apis.Enitys.User;
import com.blog.apis.exaptions.ResourcenotfoundExaption;
import com.blog.apis.playloads.UserDto;
import com.blog.apis.repo.UserRepo;
import com.blog.apis.services.UserServices;
@Service
public class Userserviceimpl implements UserServices {

	@Autowired
	private UserRepo repo;
	@Autowired
	private ModelMapper modelMapper ;

	@Override
	public UserDto SignUp(UserDto user) {

		return null;
	}

	@Override
	public UserDto Createuser(UserDto user) {
		// TODO Auto-generated method stub
		User user2 = this.userdtouser(user);
		User saveduser = this.repo.save(user2);
		return this.usertodto(saveduser);
	}

	@Override
	public UserDto Updateuser(UserDto userdto, Integer Userid) {
		// TODO Auto-generated method stub
		User user = this.repo.findById(Userid)
				.orElseThrow(() -> new ResourcenotfoundExaption("User", "Userid", Userid));
		user.setName(userdto.getName());
		user.setEmail(userdto.getEmail());
		user.setPassword(userdto.getPassword());
		user.setAbout(userdto.getAbout());
		User updateuser = this.repo.save(user);
		UserDto userDto2 = this.usertodto(updateuser);
		return userDto2;
	}

	@Override
	public UserDto Getuserbyid(Integer userid) {
		// TODO Auto-generated method stub

		User user = this.repo.findById(userid)
				.orElseThrow(() -> new ResourcenotfoundExaption("User", "Userid", userid));
		return this.usertodto(user);
	}

	@Override
	public List<UserDto> Getallusers() {
		// TODO Auto-generated method stub
		List<User> list = (List<User>) this.repo.findAll();
		List<UserDto> userDtos = list.stream().map(user -> this.usertodto(user)).collect(Collectors.toList());
		return userDtos;
	}

	@Override
	public void Deluser(Integer Userid) {
		// TODO Auto-generated method stub
		User user = this.repo.findById(Userid)	
				.orElseThrow(() -> new ResourcenotfoundExaption("User", "Userid", Userid));
			this.repo.delete(user);
	}

	private User userdtouser(UserDto dto) {
			User user = this.modelMapper.map(dto, User.class);
		return user;

	}

	public UserDto usertodto(User users) {
		UserDto userDtos = this.modelMapper.map(users ,UserDto.class);
		return userDtos;
	}

	

}
