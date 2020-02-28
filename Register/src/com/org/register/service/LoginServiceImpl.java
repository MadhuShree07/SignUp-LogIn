package com.org.register.service;

import java.util.Objects;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.register.dao.LoginDAO;
import com.org.register.dao.RegisterDAO;
import com.org.register.dto.LoginDTO;
import com.org.register.entity.RegisterEntity;

@Service
public class LoginServiceImpl implements LoginService {
	@Autowired
	private LoginDAO dao;

	public LoginServiceImpl() {
		System.out.println("Created \t" + this.getClass().getSimpleName());
	}

	public boolean validateLogin(LoginDTO dto) {
		boolean valid = false;
		try {
			System.out.println("invoked login....");

			
			  if (Objects.nonNull(dto)) { 
			  System.out.println("starting validation for " + dto);
			  
//			   String Email = dto.getEmail(); 
//			   if (Email != null && !Email.isEmpty() && Email.length() >= 10) { 
//				   System.out.println("Email is valid"); 
//				   valid = true; 
//				   } else
//			   
//			  { 
//				  System.out.println("Email is invalid"); 
//				  valid = false;
//				} 
//			  }
//			  String Password = dto.getPassword();
//			  if (valid && Password != null &&  !Password.isEmpty() && Password.length() >= 6) { 
//			  System.out.println("Password is valid"); 
//			  valid = true; 
//			  } else {
//			  System.out.println("Password is invalid"); 
//			  valid = false; 
//			  }  
//			  if (valid)
//			  
//			 System.out.println("Data is valid ,will convert  to entity");

			RegisterEntity regEntity = new RegisterEntity();
			BeanUtils.copyProperties(dto, regEntity);

			RegisterEntity regEntity1 = dao.fetchByEmailAndPassword(dto.getEmail(), dto.getPassword());

			if (regEntity1 != null) {
				System.out.println("Entity is ready \t" + regEntity);
				// dao.save(regEntity);
				// System.out.println("Entity is saved");
				dao.fetchByEmailAndPassword(dto.getEmail(), dto.getPassword());
				System.out.println("user details exists");
				return true;
			} else {
				System.out.println("user not exist");
			}
			 return false;
			  }
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return valid;
	}
}
