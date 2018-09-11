package com.thuannd.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.thuannd.entity.User;
import com.thuannd.model.SearchUserDTO;

@Repository
@Transactional
public interface UserDAO {
	void addUser(User user);

	void updateUser(User user);

	void deleteUser(User user);

	User getUserById(Long id);

	User getUserByUsername(String email);

	List<User> findUser(SearchUserDTO searchUserDTO);

	Long countUser(SearchUserDTO searchUserDTO);

	User findByEmail(String email);
}
