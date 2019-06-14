package com.src.dao;

import java.util.List;

import com.src.main.model.User;

public interface UserDAO {

	List<User> getAllUsers() throws Exception;

	User getUserById(long userId) throws Exception;

	User getUserByName(String userName) throws Exception;

	long insertUser(User user) throws Exception;

	int updateUser(Long userId, User user) throws Exception;

	int deleteUser(long userId) throws Exception;

}
