package com.spring.todos.MyTodoApplication.security;

import org.springframework.security.config.Customizer;
import java.util.function.Function;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfiguration {

	Function<String, String> passwordEncoder = input -> passwordEncoder().encode(input);

	@Bean
	public InMemoryUserDetailsManager configureUserManager() {
		UserDetails userDetails1 = getUserDetails("in28minutes", "dummy");
		UserDetails userDetails2 = getUserDetails("Ranga", "dummy");
		return new InMemoryUserDetailsManager(userDetails1, userDetails2);
	}

	private UserDetails getUserDetails(String username, String password) {
		UserDetails userDetails = User.builder().passwordEncoder(passwordEncoder).username(username).password(password)
				.roles("USER", "ADMIN").build();
		return userDetails;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(auth -> auth.anyRequest().authenticated());
		http.formLogin(Customizer.withDefaults());
		
		http.csrf(csrf -> csrf.disable());
	    http.headers(header -> header.disable());
		return http.build();
	}
}
