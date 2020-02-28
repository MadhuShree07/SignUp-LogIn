package com.org.register.dto;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class RegisterDTO {

	private String userName;
	private String email;
	private long phnNum;
	private String password;
	private String confirmPassword;
	
	public RegisterDTO() {
		System.out.println("Created \t"+this.getClass().getSimpleName());
	}

}
