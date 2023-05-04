package com.blog.apis.services;

import java.util.List;
import java.util.Optional;

import com.blog.apis.Enitys.User;
import com.blog.apis.playloads.UserDto;

public interface UserServices {

	UserDto SignUp(UserDto user);

	UserDto Createuser(UserDto user);

	UserDto Updateuser(UserDto userdto, Integer Userid);

	UserDto Getuserbyid(Integer userid);

	List<UserDto> Getallusers();

	void Deluser(Integer Userid);


}
