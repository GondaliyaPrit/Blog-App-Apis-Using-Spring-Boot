package com.blog.apis.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @Autowired
    private CustomUserDetailsService userDetailsService ;

    @Autowired
    private JwtAuthFilter jwtAuthFilter ;

    @Autowired
    private jwtauthenterypoint jwtaenterypoint ;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
       http.csrf()
             .disable()
             .authorizeHttpRequests().requestMatchers("/api/v1/auth/**").permitAll()
               .anyRequest().authenticated().
             and().exceptionHandling().authenticationEntryPoint(this.jwtaenterypoint).
             and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) ;
       http.addFilterBefore(this.jwtAuthFilter,UsernamePasswordAuthenticationFilter.class);

       http.authenticationProvider(provider());

        return http.build();

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // return NoOpPasswordEncoder.getInstance();
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }


    @Bean
    public DaoAuthenticationProvider provider()
    {
    DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
    daoAuthenticationProvider.setUserDetailsService(this.userDetailsService);
    daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
    return  daoAuthenticationProvider;
    }
}
