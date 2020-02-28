package com.org.register.service;

import java.util.Objects;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.register.dao.RegisterDAO;
import com.org.register.dto.RegisterDTO;
import com.org.register.entity.RegisterEntity;

@Service
public class RegisterServiceImpl implements RegisterService {

	@Autowired
	private RegisterDAO dao;

	public RegisterServiceImpl() {
		System.out.println("Created \t" + this.getClass().getSimpleName());
	}

	public boolean validateAndSave(RegisterDTO dto) {
		boolean valid = false;
		try {
			System.out.println("save invoked....");

			if (Objects.nonNull(dto)) {
				System.out.println("starting validation for " + dto);
				String Username = dto.getUserName();

				if (Username != null && !Username.isEmpty() && Username.length() >= 5) {
					System.out.println("Username is valid");
					valid = true;
				} else {
					System.out.println("Username is invalid");
					if (Username == null) {
						System.out.println("Username is null");
					}
					if (Username != null && Username.length() < 5) {
						System.out.println("name length is less than 5");
					}
					valid = false;
				}

				String Email = dto.getEmail();

				if (valid && Email != null && !Email.isEmpty() && Email.length() >= 10) {
					System.out.println("Email is valid");
					valid = true;
				} else {
					System.out.println("Email is invalid");
					valid = false;
				}

				String Password = dto.getPassword();

				if (valid && Password != null && !Password.isEmpty() && Password.length() >= 6) {
					System.out.println("Password is valid");
					valid = true;
				} else {

					System.out.println("Password is invalid");
					valid = false;

				}

				String ConfirmPassword = dto.getConfirmPassword();
				if (valid && ConfirmPassword != null && !ConfirmPassword.isEmpty() && ConfirmPassword.length() >= 6) {
					System.out.println("ConfirmPassword is valid");
					valid = true;
				} else {
					System.out.println("ConfirmPassword is invalid becuase its not same");
					valid = false;

				}
			}
//check pwd and confirm pwd
			if (valid) {
				System.out.println("Data is valid ,will convert  to entity");

				RegisterEntity regEntity = new RegisterEntity();
				BeanUtils.copyProperties(dto, regEntity);

				// System.out.println("entity is ready \t " + regEntity);
				// dao.save(regEntity);

				RegisterEntity regEntity1 = dao.fetchByEmail(dto.getEmail());
				RegisterEntity regEntity2 = dao.fetchByEmail(dto.getUserName());
				if (regEntity2 == null && regEntity1 == null) {
					System.out.println("Entity is ready \t" + regEntity);
					dao.save(regEntity);
					System.out.println("Entity is saved");
				} else {
					System.out.println("DATA NOT VALID ,NOT SAVED IN DATABASE");
				}
				return valid;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}