package com.thuannd.entity;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedBy;

@MappedSuperclass
public class Auditable<U> extends TImeAuditable {

	@CreatedBy
	@JoinColumn(name = "created_by")
	@ManyToOne(fetch = FetchType.LAZY)
	private User createdBy;

	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

}
