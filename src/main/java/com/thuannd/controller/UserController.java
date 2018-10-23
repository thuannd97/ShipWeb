package com.thuannd.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.thuannd.model.SearchUserDTO;
import com.thuannd.model.UserDTO;
import com.thuannd.service.UserService;

@Controller
public class UserController {
	@Autowired
	UserService userService;

	@GetMapping("/")
	public String home() {
		return "client/home/index";
	}

	@GetMapping("/dang-nhap")
	public String login(@RequestParam(name = "e", required = false) String error, Model model) {
		if (isLogin()) {
			return "redirect:/";
		}
		if (error != null) {
			model.addAttribute("msg", "login failed");
			System.out.println("login failed!");
		}
		return "client/user/login";
	}

	@GetMapping("/dang-xuat")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/";
	}

	@GetMapping("/dang-ky")
	public String registerForm(Model model) {
		model.addAttribute("userDTO", new UserDTO());
		return "client/user/register";
	}

	@PostMapping("/dang-ky")
	public String register(@Valid @ModelAttribute("userDTO") UserDTO userDTO, BindingResult result, Model model, @RequestParam("file") MultipartFile file) {
		this.validate(userDTO, result);
		if (result.hasErrors()) {
			return "client/user/register";
		} else {
			try {
				final String UPLOAD_FOLDER = "D:\\spring\\ship-web\\src\\main\\resources\\static\\images";
				String avatar = userDTO.getEmail() + "-avatar.jpg";
				Path pathAvatar = Paths.get(UPLOAD_FOLDER + File.separator + avatar);
				Files.write(pathAvatar, file.getBytes());
				
				userDTO.setAvatar(avatar);
				userService.addUser(userDTO);
				model.addAttribute("userDTO", userDTO);
			} catch (IOException e) {
				e.printStackTrace();
				return "client/user/register";
			}
			return "redirect:/";
		}
	}

	private void validate(Object object, Errors errors) {
		UserDTO userDTO = (UserDTO) object;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "error.msg.empty.user.email");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "error.msg.empty.user.password");

		if (userService.findUser(new SearchUserDTO(userDTO.getEmail())).size() > 0) {
			errors.rejectValue("email", "error.msg.available.user.email");
		}
	}

	public List<UserDTO> findUserAvailable(String email) {
		SearchUserDTO searchUserDTO = new SearchUserDTO();
		searchUserDTO.setEmail(email);
		return userService.findUser(searchUserDTO);
	}

	public boolean isLogin() {
		return SecurityContextHolder.getContext().getAuthentication().isAuthenticated()
				&& !(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken);
	}

}
