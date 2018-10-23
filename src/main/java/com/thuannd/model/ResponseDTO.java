package com.thuannd.model;

import java.util.List;

public class ResponseDTO<T> {

	private Long totalRecords;
	private List<T> data;

	public Long getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(Long totalRecords) {
		this.totalRecords = totalRecords;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

}
