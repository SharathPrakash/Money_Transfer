package com.src.dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.commons.dbutils.DbUtils;
import org.h2.tools.RunScript;

import com.src.dao.impl.AccountDAOImpl;
import com.src.dao.impl.UserDAOImpl;
import com.src.main.utils.Utils;

/**
 * H2 DAO
 */
public class H2DAOFactory extends DAOFactory {
	private static final String h2_driver = Utils.getStringProperty("h2_driver");
	private static final String h2_connection_url = Utils.getStringProperty("h2_connection_url");
	private static final String h2_user = Utils.getStringProperty("h2_user");
	private static final String h2_password = Utils.getStringProperty("h2_password");

	private final UserDAOImpl userDAO = new UserDAOImpl();
	private final AccountDAOImpl accountDAO = new AccountDAOImpl();


	H2DAOFactory() {
		DbUtils.loadDriver(h2_driver);
	}

	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(h2_connection_url, h2_user, h2_password);

	}

	public UserDAO getUserDAO() {
		return userDAO;
	}

	public AccountDAO getAccountDAO() {
		return accountDAO;
	}

	@Override
	public void populateTestData() {
		Connection conn = null;
		try {
			conn = H2DAOFactory.getConnection();
			RunScript.execute(conn, new FileReader("src/main/resources/account.sql"));
		} catch (SQLException e) {
			System.out.println("populateTestData(): Error populating user data: " + e);
			throw new RuntimeException(e);
		} catch (FileNotFoundException e) {
			System.out.println("populateTestData(): Error finding test script file " + e);
			throw new RuntimeException(e);
		} finally {
			DbUtils.closeQuietly(conn);
		}
	}

}
