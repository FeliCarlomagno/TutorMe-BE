package com.tutorMe.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.auth.entity.User;


@Configuration
public class UserConfiguration {
	
	@Bean
	@Scope("prototype")
	public User creaUser() {
		return new User();
	}
	
	@Bean("userCustom")
	@Scope("prototype")
	public User userCustom(String name, String username, String email, String password) {
		User u = new User();
		u.setName(name);
		u.setUsername(username);
		u.setEmail(email);
		u.setPassword(password);
		return u;
	}
}
