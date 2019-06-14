package com.src.main;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;

import com.src.dao.DAOFactory;
import com.src.main.service.AccountService;
import com.src.main.service.TransactionService;
import com.src.main.service.UserService;

public class App {

	public static void main(String[] args) throws Exception {
		// Initialize H2 database
		System.out.println("Application Started ");
		DAOFactory h2DaoFactory = DAOFactory.getDAOFactory(DAOFactory.H2);
		h2DaoFactory.populateTestData();
		System.out.println("Application Initialized");
		//jetty Start
		startService();
	}

	private static void startService() throws Exception {
		Server server = new Server(8080);
		ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
		context.setContextPath("/");
		server.setHandler(context);
		ServletHolder servletHolder = context.addServlet(ServletContainer.class, "/*");
		servletHolder.setInitParameter("jersey.config.server.provider.classnames", UserService.class.getCanonicalName()
				+ "," + AccountService.class.getCanonicalName() + "," + TransactionService.class.getCanonicalName());
		try {
			server.start();
			server.join();
		} finally {
			server.destroy();
		}
	}

}
