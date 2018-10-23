package com.thuannd.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.thuannd.entity.UserOrder;
import com.thuannd.model.MyPrincipal;
import com.thuannd.model.OrderDTO;
import com.thuannd.model.ResponseDTO;
import com.thuannd.model.SearchOrderDTO;
import com.thuannd.service.OrderService;

@Controller
public class OrderController {

	@Autowired
	private OrderService orderService;

	@GetMapping("/shop/orders")
	public String viewFindOrder() {
		return "admin/shop/list-order";
	}

	@PostMapping("/shop/orders")
	public ResponseEntity<ResponseDTO<OrderDTO>> findOrderByRole(HttpServletRequest request,
			@RequestBody SearchOrderDTO searchOrderDTO) {
		// get current user
		MyPrincipal currentUser = (MyPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		// find order by role of current user
		searchOrderDTO.setCreatedBy(currentUser.getId());
		ResponseDTO<OrderDTO> responseDTO = new ResponseDTO<OrderDTO>();
		responseDTO.setData(orderService.findOrder(searchOrderDTO));
		responseDTO.setTotalRecords(orderService.countOrder(searchOrderDTO));
		return new ResponseEntity<ResponseDTO<OrderDTO>>(responseDTO, HttpStatus.OK);
	}

	@GetMapping("/shop/add-order")
	public String viewAddOrder(Model model) {
		model.addAttribute("orderDTO", new UserOrder());
		return "admin/shop/add-order";
	}

	@PostMapping("/shop/add-order")
	public String addOrder(@Valid @ModelAttribute("orderDTO") OrderDTO orderDTO, BindingResult result) {
		this.validate(orderDTO, result);
		if (result.hasErrors()) {
			return "admin/shop/add-order";
		} else {
			orderService.addOrder(orderDTO);
			return "redirect:/shop/orders";
		}
	}

	@GetMapping("/shop/delete/order/{id}")
	public ResponseEntity<String> deleteOrder(@PathVariable(name = "id") Long id) {
		orderService.deleteOrder(id);
		return new ResponseEntity<String>("ok", HttpStatus.OK);
	}

	@GetMapping("/orders/all")
	public String viewFindAllOrder() {
		return "client/user/list-all-order";
	}

	@PostMapping("/orders/all")
	public ResponseEntity<ResponseDTO<OrderDTO>> findAllOrder(@RequestBody SearchOrderDTO searchOrderDTO) {
		// find all order
		ResponseDTO<OrderDTO> responseDTO = new ResponseDTO<OrderDTO>();
		responseDTO.setData(orderService.findOrder(searchOrderDTO));
		responseDTO.setTotalRecords(orderService.countOrder(searchOrderDTO));
		System.out.println(orderService.countOrder(searchOrderDTO));
		return new ResponseEntity<ResponseDTO<OrderDTO>>(responseDTO, HttpStatus.OK);
	}

	private void validate(Object object, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fromAdd", "error.msg.empty.fromAdd");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "toAdd", "error.msg.empty.toAdd");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "addvanceMoney", "error.msg.empty.AddvanceMoney");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fee", "error.msg.empty.fee");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "content", "error.msg.empty.content");
	}

}
