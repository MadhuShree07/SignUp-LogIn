package com.org.register.dto;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class LoginDTO {

	private String email;
	private String password;
	
	public LoginDTO() {
		System.out.println("Created \t"+this.getClass().getSimpleName());
	}

}
