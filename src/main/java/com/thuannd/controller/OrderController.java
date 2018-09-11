package com.thuannd.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OrderController {
	
	@GetMapping("/danh-sach-nguoi-dung")
	public String findOrder() {
		return "admin/shop/list-order";
	}
}
