package com.blog.apis.security;

import com.blog.apis.Enitys.User;
import com.blog.apis.exaptions.ApiBadCredexaption;
import com.blog.apis.exaptions.ResourcenotfoundExaption;
import com.blog.apis.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService  implements UserDetailsService {

    @Autowired
    private UserRepo userRepo ;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException , ApiBadCredexaption {
        User useremail = this.userRepo.findByEmail(username).orElseThrow(() -> new ResourcenotfoundExaption("User", "Email " + username, 0));
        return useremail;
    }
}
