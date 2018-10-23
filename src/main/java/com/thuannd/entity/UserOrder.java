package com.thuannd.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user_order")
public class UserOrder extends Auditable<User> implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "content")
	private String content;
	@Column(name = "from_add")
	private String fromAdd;
	@Column(name = "to_add")
	private String toAdd;
	@Column(name = "addvance_money")
	private Long addvanceMoney;
	@Column(name = "fee")
	private Long fee;
	@Column(name = "status")
	private Integer status;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "shipper")
	private User shipper;

	public UserOrder() {
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public User getShipper() {
		return shipper;
	}

	public void setShipper(User shipper) {
		this.shipper = shipper;
	}

}
