package com.org.register.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

import lombok.Data;


@Entity
@Table(name="register")
@Data

@NamedQueries({@NamedQuery(name = "fetchByEmail", query = "select re from RegisterEntity re where re.email=:email")
,@NamedQuery(name="fetchByEmailAndPassword", query="select re from RegisterEntity re where re.email=:email and re.password=:password")})

//@NamedQuery(name="fetchByEmailAndPassword", query="select re from RegisterEntity re where re.email=:mail and re.password=:pwd")
public class RegisterEntity implements Serializable {
	
	
	@Id
	@GenericGenerator(name = "madhu", strategy = "increment")
	@GeneratedValue(generator="madhu")
	@Column(name="id")
	private int id;
	@Column(name="userName")
	private String userName;
	@Column(name="email")
	private String email;
	@Column(name="phnNum")
	private long phnNum;
	@Column(name="password")
	private String password;
	@Column(name="confirmPass")
	private String confirmPassword;
	
	public RegisterEntity() {
		System.out.println("Created \t"+this.getClass().getSimpleName());
	}


}
