package com.thuannd.dao;

import java.util.List;

import com.thuannd.entity.UserOrder;
import com.thuannd.model.SearchOrderDTO;

public interface OrderDAO {
	void addOrder(UserOrder order);

	void updateOrder(UserOrder order);

	void deleteOrder(UserOrder order);

	UserOrder getOrderById(Long id);

	List<UserOrder> findOrder(SearchOrderDTO searchOrderDTO);

	Long countOrder(SearchOrderDTO searchOrderDTO);
}
