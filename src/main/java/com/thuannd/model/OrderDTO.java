package com.thuannd.model;

import java.io.Serializable;

public class OrderDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String content;
	private String fromAdd;
	private String toAdd;
	private Long addvanceMoney;
	private Long fee;
	private int status;
	private String createdDate;
	private Long createdBy;
	private Long shipperId;

	public OrderDTO() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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

	public Long getAddvanceMoney() {
		return addvanceMoney;
	}

	public void setAddvanceMoney(Long addvanceMoney) {
		this.addvanceMoney = addvanceMoney;
	}

	public Long getFee() {
		return fee;
	}

	public void setFee(Long fee) {
		this.fee = fee;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Long getShipperId() {
		return shipperId;
	}

	public void setShipperId(Long shipperId) {
		this.shipperId = shipperId;
	}

}
