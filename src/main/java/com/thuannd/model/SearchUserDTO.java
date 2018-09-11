package com.thuannd.model;

public class SearchUserDTO extends SearchDTO {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String email;

	public SearchUserDTO() {
		super();
	}

	public SearchUserDTO(String email) {
		super();
		this.email = email;
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

}
