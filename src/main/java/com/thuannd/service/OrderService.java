package com.thuannd.service;

import java.util.List;

import com.thuannd.model.OrderDTO;
import com.thuannd.model.SearchOrderDTO;

public interface OrderService {

	void addOrder(OrderDTO orderDTO);

	void updateOrder(OrderDTO orderDTO);

	void deleteOrder(Long id);

	List<OrderDTO> findOrder(SearchOrderDTO searchOrderDTO);

	Long countOrder(SearchOrderDTO searchOrderDTO);

	void changeOrderStatus(OrderDTO orderDTO);

	OrderDTO getOrderById(Long id);
}
