package com.thuannd.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thuannd.dao.OrderDAO;
import com.thuannd.entity.User;
import com.thuannd.entity.UserOrder;
import com.thuannd.model.OrderDTO;
import com.thuannd.model.SearchOrderDTO;
import com.thuannd.service.OrderService;
import com.thuannd.utils.DateTimeUtils;
import com.thuannd.utils.OrderStatus;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderDAO orderDAO;

	@Override
	public void addOrder(OrderDTO orderDTO) {
		UserOrder order = new UserOrder();
		order.setContent(orderDTO.getContent());
		order.setFromAdd(orderDTO.getFromAdd());
		order.setToAdd(orderDTO.getToAdd());
		order.setAddvanceMoney(orderDTO.getAddvanceMoney());
		order.setFee(orderDTO.getFee());
		order.setStatus(OrderStatus.NEW);

		orderDAO.addOrder(order);
		orderDTO.setId(order.getId());
	}

	@Override
	public void updateOrder(OrderDTO orderDTO) {
		UserOrder order = orderDAO.getOrderById(orderDTO.getId());
		if (order != null) {
			orderDTO.setContent(order.getContent());
			orderDTO.setFromAdd(order.getFromAdd());
			orderDTO.setToAdd(order.getToAdd());
			orderDTO.setAddvanceMoney(order.getAddvanceMoney());
			orderDTO.setFee(order.getFee());
			orderDTO.setShipperId(order.getShipper().getId());

			orderDAO.updateOrder(order);
		}
	}

	@Override
	public void deleteOrder(Long id) {
		UserOrder order = orderDAO.getOrderById(id);
		if (order != null) {
			orderDAO.deleteOrder(order);
		}
	}

	@Override
	public List<OrderDTO> findOrder(SearchOrderDTO searchOrderDTO) {
		List<UserOrder> orders = orderDAO.findOrder(searchOrderDTO);
		List<OrderDTO> orderDTOs = new ArrayList<OrderDTO>();
		orders.forEach(order -> {
			OrderDTO orderDTO = new OrderDTO();
			orderDTO.setId(order.getId());
			orderDTO.setContent(order.getContent());
			orderDTO.setFromAdd(order.getFromAdd());
			orderDTO.setToAdd(order.getToAdd());
			orderDTO.setAddvanceMoney(order.getAddvanceMoney());
			orderDTO.setFee(order.getFee());
			orderDTO.setCreatedDate(DateTimeUtils.formatDate(order.getCreatedDate(), DateTimeUtils.DD_MM_YYYY_HH_MM));
			orderDTO.setStatus(order.getStatus());
			if (order.getShipper() != null) {
				orderDTO.setShipperId(order.getShipper().getId());
			}

			orderDTOs.add(orderDTO);
		});
		return orderDTOs;
	}

	@Override
	public Long countOrder(SearchOrderDTO searchOrderDTO) {
		return orderDAO.countOrder(searchOrderDTO);
	}

	@Override
	public void changeOrderStatus(OrderDTO orderDTO) {
		UserOrder userOrder = orderDAO.getOrderById(orderDTO.getId());
		if (userOrder != null) {
			if (userOrder.getStatus() == OrderStatus.NEW) {
				userOrder.setShipper(new User(orderDTO.getShipperId()));
				userOrder.setStatus(OrderStatus.PICKED_UP);
				orderDAO.updateOrder(userOrder);
			}
		}
	}

	@Override
	public OrderDTO getOrderById(Long id) {
		UserOrder order = orderDAO.getOrderById(id);
		if (order != null) {
			OrderDTO orderDTO = new OrderDTO();
			orderDTO.setId(order.getId());
			orderDTO.setContent(order.getContent());
			orderDTO.setFromAdd(order.getFromAdd());
			orderDTO.setToAdd(order.getToAdd());
			orderDTO.setAddvanceMoney(order.getAddvanceMoney());
			orderDTO.setFee(order.getFee());
			orderDTO.setCreatedDate(DateTimeUtils.formatDate(order.getCreatedDate(), DateTimeUtils.DD_MM_YYYY_HH_MM));
			orderDTO.setStatus(order.getStatus());
			if (order.getShipper() != null) {
				orderDTO.setShipperId(order.getShipper().getId());
			}
			return orderDTO;
		}
		return null;
	}

}
