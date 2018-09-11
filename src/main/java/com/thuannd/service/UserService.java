package com.thuannd.service;

import java.util.List;

import com.thuannd.model.SearchUserDTO;
import com.thuannd.model.UserDTO;

public interface UserService {
	void addUser(UserDTO userDTO);

	void updateUser(UserDTO userDTO);

	void deleteUser(Long id);

	UserDTO getUserById(Long id);

	List<UserDTO> findUser(SearchUserDTO searchUserDTO);

	Long countUser(SearchUserDTO searchUserDTO);

	UserDTO findByEmail(String email);

	void changePassword(UserDTO userDTO);
}
