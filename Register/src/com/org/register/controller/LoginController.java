package com.org.register.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.org.register.dto.LoginDTO;
import com.org.register.service.LoginService;

@Controller
@RequestMapping
public class LoginController {

	@Autowired
	private LoginService service;

	public LoginController() {
		System.out.println("Created \t" + this.getClass().getSimpleName());
	}

	@RequestMapping("/login.do")
	public String onLogin(LoginDTO dto, ModelMap map) {

		try {
			System.out.println("Invoked onLogin method");
			System.out.println(dto);

			boolean check = this.service.validateLogin(dto);

			if (check) {
				
				ModelMap email = map.addAttribute("email", dto.getEmail());
				ModelMap email2 = map.addAttribute("password", dto.getPassword());
				return "Success";
				
			} else {
				ModelMap failure = map.addAttribute("failureMessage", "Login unsuccessfull");
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Info:" + e.getMessage());
		}

		return "login";
	}
}
