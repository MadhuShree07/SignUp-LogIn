package com.org.register.dao;

import java.util.Objects;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.org.register.entity.RegisterEntity;

@Component
public class LoginDAOImpl implements LoginDAO {

	@Autowired
	private SessionFactory factory;

	public void setFactory(SessionFactory factory) {
		this.factory = factory;
	}

	public LoginDAOImpl() {
		System.out.println("Created \t" + this.getClass().getSimpleName());
	}

	public RegisterEntity fetchByEmailAndPassword(String mail, String pwd) {
		Session session = null;

		try {
			session = factory.openSession();
			System.out.println("invoke program...");

			Query query = session.getNamedQuery("fetchByEmailAndPassword");

			query.setParameter("email", mail);
			query.setParameter("password", pwd);
			Object result = query.uniqueResult();
			if (Objects.nonNull(result)) {
				System.out.println("Entity found" + "\t" + mail + "\t" + pwd);
				RegisterEntity entity = (RegisterEntity) result;
				return entity;
			} else {
				System.out.println("Entity not found");
				return null;
			}

		} catch (HibernateException he) {
			he.printStackTrace();
		} finally {
			if (Objects.nonNull(session))
				session.close();
		}
		return null;
	}
}