package com.thuannd.model;

public class SearchOrderDTO extends SearchDTO {

	private static final long serialVersionUID = 1L;

	private String fromAdd;
	private String toAdd;
	private Long createdBy;

	public SearchOrderDTO() {
		super();
	}

	public String getFromAdd() {
		return fromAdd;
	}

	public void setFromAdd(String fromAdd) {
		this.fromAdd = fromAdd;
	}

	public String getToAdd() {
		return toAdd;
	}

	public void setToAdd(String toAdd) {
		this.toAdd = toAdd;
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

}
