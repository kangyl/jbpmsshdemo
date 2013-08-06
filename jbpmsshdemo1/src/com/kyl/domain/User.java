package com.kyl.domain;
// default package

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * User entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="user")
public class User implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 8518020546642369618L;
	private String id;
	private String name;
	private String password;
	private Integer age;

	// Constructors

	/** default constructor */
	public User() {
	}

	/** minimal constructor */
	public User(String id) {
		this.id = id;
	}

	/** full constructor */
	public User(String id, String name, String password, Integer age) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.age = age;
	}

	// Property accessors
	@GenericGenerator(name="generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator="generator")
	@Column(name = "id", unique = true, nullable = false, length = 32)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "name", length = 20)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "password", length = 20)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "age")
	public Integer getAge() {
		return this.age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

}