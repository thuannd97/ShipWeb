package com.thuannd.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.thuannd.dao.UserDAO;
import com.thuannd.entity.Role;
import com.thuannd.entity.User;
import com.thuannd.model.SearchUserDTO;
import com.thuannd.model.UserDTO;
import com.thuannd.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService {

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public void addUser(UserDTO userDTO) {
		User user = new User();
		user.setAvatar(userDTO.getAvatar());
		user.setEmail(userDTO.getEmail());
		user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
		user.setRole(new Role(userDTO.getRoleId()));

		userDAO.addUser(user);
		userDTO.setId(user.getId());
	}

	@Override
	public void updateUser(UserDTO userDTO) {
		User user = userDAO.getUserById(userDTO.getId());
		if (user != null) {
			user.setId(userDTO.getId());
			user.setAvatar(userDTO.getAvatar());
			user.setEmail(userDTO.getEmail());
			user.setRole(new Role(userDTO.getRoleId()));

			userDAO.updateUser(user);
		}
	}

	@Override
	public void deleteUser(Long id) {
		User user = userDAO.getUserById(id);
		if (user != null) {
			userDAO.deleteUser(user);
		}
	}

	@Override
	public UserDTO getUserById(Long id) {
		User user = userDAO.getUserById(id);
		if (user != null) {
			UserDTO userDTO = new UserDTO();
			userDTO.setId(user.getId());
			userDTO.setAvatar(user.getAvatar());
			userDTO.setEmail(user.getEmail());
			userDTO.setRoleId(user.getRole().getId());

			return userDTO;
		}
		return null;
	}

	@Override
	public List<UserDTO> findUser(SearchUserDTO searchUserDTO) {
		List<User> users = userDAO.findUser(searchUserDTO);
		List<UserDTO> userDTOs = new ArrayList<UserDTO>();
		users.forEach(user -> {
			UserDTO userDTO = new UserDTO();
			userDTO.setId(user.getId());
			userDTO.setAvatar(user.getAvatar());
			userDTO.setEmail(user.getEmail());
			userDTO.setRoleId(user.getRole().getId());

			userDTOs.add(userDTO);
		});
		return userDTOs;
	}

	@Override
	public Long countUser(SearchUserDTO searchUserDTO) {
		return userDAO.countUser(searchUserDTO);
	}

	@Override
	public UserDTO findByEmail(String email) {
		User user = userDAO.findByEmail(email);
		UserDTO userDTO = new UserDTO();
		userDTO.setEmail(user.getEmail());
		userDTO.setPassword(user.getPassword());
		userDTO.setAvatar(user.getAvatar());
		return userDTO;
	}

	@Override
	public void changePassword(UserDTO userDTO) {
		User user = userDAO.getUserById(userDTO.getId());
		if (user != null) {
			user.setPassword(userDTO.getPassword());

			userDAO.updateUser(user);
		}
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User userLocal = userDAO.findByEmail(email);
		if (userLocal == null) {
			System.out.println("null");
		}

		List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(userLocal.getRole().getName()));

		UserDetails userDetails = new org.springframework.security.core.userdetails.User(userLocal.getEmail(),
				userLocal.getPassword(), true, true, true, true, authorities);
		return userDetails;
	}

}
