package com.spring.todos.MyTodoApplication.login;

import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
	public boolean authenticate(String username, String password) {
		boolean isUsernameValid = username.equalsIgnoreCase("in28minutes");
		boolean isPasswordValid = password.equalsIgnoreCase("passpass");
		
		return isUsernameValid && isPasswordValid;
	}
}
