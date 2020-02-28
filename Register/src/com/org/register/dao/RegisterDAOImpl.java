package com.org.register.dao;

import java.io.Serializable;
import java.util.Objects;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.org.register.entity.RegisterEntity;

@Component
public class RegisterDAOImpl implements RegisterDAO {

	@Autowired
	private SessionFactory factory;

	public void setFactory(SessionFactory factory) {
		this.factory = factory;
	}

	public RegisterDAOImpl() {
		System.out.println("Created \t" + this.getClass().getSimpleName());
	}

	public Serializable save(RegisterEntity entity) {
		System.out.println("Invoking save method");
		Session session = null;
		try {

			System.out.println("session created");
			session = factory.openSession();
			System.out.println("Transaction begun");
			session.beginTransaction();
			System.out.println("Entity saving...");
			session.save(entity);
			System.out.println("Commiting....");
			session.getTransaction().commit();
			System.out.println("Data saved");

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("INFO:" + e.getMessage());
		}

		return entity;

	}

	public RegisterEntity fetchByEmail(String email) {
		System.out.println("Invoked fetchByName() ....");
		Session session = null;
		try {
			session = factory.openSession();
			// session.getTransaction();
			System.out.println("fetching...");
			String hql = "SELECT re from RegisterEntity re where email='" + email + "'";
			Query query = session.createQuery(hql);
			Object result = query.uniqueResult();
			if (Objects.nonNull(result)) {
				System.out.println("Data found");
				RegisterEntity entity = (RegisterEntity) result;
				return entity;
				// return entity;
			} else {
				System.out.println("Data not found");
				return null;
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("INFO:" + e.getMessage());
		}
		// return null;
		finally {
			session.close();

		}
		return null;
	}

}
