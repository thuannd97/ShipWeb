package com.thuannd.model;

import java.io.Serializable;

public class UserDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String email;
	private String password;
	private Integer roleId;
	private String avatar;

	public UserDTO() {
		super();
	}

	public UserDTO(Long id) {
		super();
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

}
