package com.blog.apis.security;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {
    @Autowired
    private UserDetailsService userDetailsService ;
    @Autowired
    private JwtHelper jwttokenhelper ;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String reqtoken=request.getHeader("token") ;
        String Username =null;
        String token =null ;

        if(reqtoken!=null && reqtoken.startsWith("Bearer"))
        {
            token = reqtoken.substring(7);
            try {
                Username = this.jwttokenhelper.getUsernameFromToken(token);
            } catch (ExpiredJwtException e){
                System.out.println(e.getMessage().toString());
            }catch (IllegalArgumentException e){
                System.out.println(e.getMessage().toString());
            }catch (MalformedJwtException e)
            {
                System.out.println(e.getMessage().toString());
            }
        }else {
            System.out.println("Jwt Does Not began with bearer");
        }


        if(Username != null && SecurityContextHolder.getContext().getAuthentication()==null) {
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(Username);
            if (this.jwttokenhelper.validateToken(token, userDetails)) {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            } else {
                System.out.println("invalid Token");
            }
        }
        filterChain.doFilter(request,response);
    }
}
