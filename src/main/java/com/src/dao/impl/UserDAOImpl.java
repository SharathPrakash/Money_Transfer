package com.src.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;

import com.src.dao.H2DAOFactory;
import com.src.dao.UserDAO;
import com.src.main.model.User;

public class UserDAOImpl implements UserDAO {

	private final static String SQL_GET_USER_BY_ID = "SELECT * FROM User WHERE UserId = ? ";
	private final static String SQL_GET_ALL_USERS = "SELECT * FROM User";
	private final static String SQL_GET_USER_BY_NAME = "SELECT * FROM User WHERE UserName = ? ";
	private final static String SQL_INSERT_USER = "INSERT INTO User (UserName, EmailAddress) VALUES (?, ?)";
	private final static String SQL_UPDATE_USER = "UPDATE User SET UserName = ?, EmailAddress = ? WHERE UserId = ? ";
	private final static String SQL_DELETE_USER_BY_ID = "DELETE FROM User WHERE UserId = ? ";

	/**
	 * Find all users
	 */
	public List<User> getAllUsers() throws Exception {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<User> users = new ArrayList<User>();
		try {
			conn = H2DAOFactory.getConnection();
			stmt = conn.prepareStatement(SQL_GET_ALL_USERS);
			rs = stmt.executeQuery();
			while (rs.next()) {
				User u = new User(rs.getLong("UserId"), rs.getString("UserName"), rs.getString("EmailAddress"));
				users.add(u);

				System.out.println("getAllUsers() Retrieve User: " + u);
			}
			return users;
		} catch (SQLException e) {
			throw new Exception("Error reading user data", e);
		} finally {
			DbUtils.closeQuietly(conn, stmt, rs);
		}
	}

	/**
	 * Find user by userId
	 */
	public User getUserById(long userId) throws Exception {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		User u = null;
		try {
			conn = H2DAOFactory.getConnection();
			stmt = conn.prepareStatement(SQL_GET_USER_BY_ID);
			stmt.setLong(1, userId);
			rs = stmt.executeQuery();
			if (rs.next()) {
				u = new User(rs.getLong("UserId"), rs.getString("UserName"), rs.getString("EmailAddress"));

				System.out.println("getUserById(): Retrieve User: " + u);
			}
			return u;
		} catch (SQLException e) {
			throw new Exception("Error reading user data", e);
		} finally {
			DbUtils.closeQuietly(conn, stmt, rs);
		}
	}

	/**
	 * Find user by userName
	 */
	public User getUserByName(String userName) throws Exception {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		User u = null;
		try {
			conn = H2DAOFactory.getConnection();
			stmt = conn.prepareStatement(SQL_GET_USER_BY_NAME);
			stmt.setString(1, userName);
			rs = stmt.executeQuery();
			if (rs.next()) {
				u = new User(rs.getLong("UserId"), rs.getString("UserName"), rs.getString("EmailAddress"));

				System.out.println("Retrieve User: " + u);
			}
			return u;
		} catch (SQLException e) {
			throw new Exception("Error reading user data", e);
		} finally {
			DbUtils.closeQuietly(conn, stmt, rs);
		}
	}

	/**
	 * Save User
	 */
	public long insertUser(User user) throws Exception {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet generatedKeys = null;
		try {
			conn = H2DAOFactory.getConnection();
			stmt = conn.prepareStatement(SQL_INSERT_USER, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, user.getUserName());
			stmt.setString(2, user.getEmailAddress());
			int affectedRows = stmt.executeUpdate();
			if (affectedRows == 0) {
				System.out.println("insertUser(): Creating user failed, no rows affected." + user);
				throw new Exception("Users Cannot be created");
			}
			generatedKeys = stmt.getGeneratedKeys();
			if (generatedKeys.next()) {
				return generatedKeys.getLong(1);
			} else {
				System.out.println("insertUser():  Creating user failed, no ID obtained." + user);
				throw new Exception("Users Cannot be created");
			}
		} catch (SQLException e) {
			System.out.println("Error Inserting User :" + user);
			throw new Exception("Error creating user data", e);
		} finally {
			DbUtils.closeQuietly(conn, stmt, generatedKeys);
		}

	}

	/**
	 * Update User
	 */
	public int updateUser(Long userId, User user) throws Exception {
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = H2DAOFactory.getConnection();
			stmt = conn.prepareStatement(SQL_UPDATE_USER);
			stmt.setString(1, user.getUserName());
			stmt.setString(2, user.getEmailAddress());
			stmt.setLong(3, userId);
			return stmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Error Updating User :" + user);
			throw new Exception("Error update user data", e);
		} finally {
			DbUtils.closeQuietly(conn);
			DbUtils.closeQuietly(stmt);
		}
	}

	/**
	 * Delete User
	 */
	public int deleteUser(long userId) throws Exception {
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = H2DAOFactory.getConnection();
			stmt = conn.prepareStatement(SQL_DELETE_USER_BY_ID);
			stmt.setLong(1, userId);
			return stmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Error Deleting User :" + userId);
			throw new Exception("Error Deleting User ID:" + userId, e);
		} finally {
			DbUtils.closeQuietly(conn);
			DbUtils.closeQuietly(stmt);
		}
	}

}
