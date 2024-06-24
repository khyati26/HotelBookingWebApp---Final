package com.narola.hotelbooking.UserHome;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.narola.hotelbooking.StateCity.dao.CityDAO;
import com.narola.hotelbooking.StateCity.dao.ICityDAO;
import com.narola.hotelbooking.dao.DAOFactory;

public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ICityDAO cityDAO = DAOFactory.getDaoFactory().getCityDAO();

		request.setAttribute("cities", cityDAO.getPopularCities());
		RequestDispatcher rd = request.getRequestDispatcher("customerside/Homepage.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
