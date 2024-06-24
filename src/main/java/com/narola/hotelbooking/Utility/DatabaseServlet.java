package com.narola.hotelbooking.Utility;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import com.narola.hotelbooking.dao.ConnectDB;
import com.narola.hotelbooking.dao.DAOFactory;

public class DatabaseServlet extends HttpServlet {

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		try {
			String dbType = getServletContext().getInitParameter("DB-IN-USE");
			DAOFactory.init(dbType);
			String dbName = getServletContext().getInitParameter(dbType + "_dbname");
			String dbUrl = getServletContext().getInitParameter(dbType + "_dburl");
			String userName = getServletContext().getInitParameter(dbType + "_username");
			String password = getServletContext().getInitParameter(dbType + "_password");
			ConnectDB.setDbname(dbName);
			ConnectDB.setUrl(dbUrl);
			ConnectDB.setUsername(userName);
			ConnectDB.setPassword(password);
			ConnectDB.initializeConnection();
		} catch (Exception e1) {
			e1.printStackTrace();
		}

	}

}
