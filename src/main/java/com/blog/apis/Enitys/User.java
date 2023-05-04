package com.blog.apis.Enitys;

import java.util.*;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


@Entity
@Table(name="users" , uniqueConstraints = {
        @UniqueConstraint(columnNames = "Username"),
        @UniqueConstraint(columnNames = "email")
    })
@NoArgsConstructor
@Getter
@Setter
public class User implements UserDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id ;
	@Column(name="Username", nullable = false )
	private String name;
	private String email ;
	private String password;
	private String about ;
	
	@JsonIgnore
	@OneToMany(mappedBy ="user" ,cascade = CascadeType.ALL)
	private List<Post> posts = new ArrayList<>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "user")
	List<Comment> commentslist = new ArrayList<>();

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "user_role",
			joinColumns= @JoinColumn(name = "user",referencedColumnName ="id" ),inverseJoinColumns =
	@JoinColumn(name = "role", referencedColumnName = "id"))
	private Set<Roles>  roles = new HashSet<>();


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<SimpleGrantedAuthority> authority = this.roles.stream().map((role) -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
		return authority;
	}

	@Override
	public String getUsername() {
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
