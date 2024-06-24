package com.narola.hotelbooking.StateCity.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.narola.hotelbooking.StateCity.dao.ICityDAO;
import com.narola.hotelbooking.StateCity.model.City;
import com.narola.hotelbooking.StateCity.dao.CityDAO;
import com.narola.hotelbooking.StateCity.service.CityServiceFectory;
import com.narola.hotelbooking.StateCity.service.ICityService;
import com.narola.hotelbooking.Utility.AdminURLConstant;
import com.narola.hotelbooking.dao.DAOFactory;

//import com.google.gson.Gson;
//import com.mysql.cj.xdevapi.JsonArray;

/**
 * Servlet implementation class CityServlet
 */
public class CityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		ICityDAO cityDAO = DAOFactory.getDaoFactory().getCityDAO();

		ICityService cityService = CityServiceFectory.getInstance().getcityService();

//		if (request.getRequestURI().equals(request.getContextPath() + AdminURLConstant.DISPLAYALL_CITIES_URL)) {
//
//			request.setAttribute("cities",cityService.getAllCity());
//			RequestDispatcher rd = request.getRequestDispatcher("admin/statecity/City.jsp");
//			rd.forward(request, response);
//
//		} else
			if (request.getRequestURI().equals(request.getContextPath() + AdminURLConstant.POPULAR_CITIES_URL)) {

			if(!request.getParameter("uncheckedids").isEmpty()) {
				cityService.uncheckPopularCity(request.getParameter("uncheckedids"));
//				ArrayList<String> myList2 = new ArrayList<>(Arrays.asList(request.getParameter("uncheckedids").split(",")));
//				if (!myList2.isEmpty()) {
//					for (String integer : myList2) {
//						cityService.uncheckPopularCity(Integer.parseInt(integer));
////						cityDAO.uncheckPopularCity(Integer.parseInt(integer));
//					}
//				}
			}
			if(!request.getParameter("checkedids").isEmpty()) {
				cityService.checkPopularCity(request.getParameter("checkedids"));
//				ArrayList<String> myList = new ArrayList<>(Arrays.asList(request.getParameter("checkedids").split(",")));
//				if (!myList.isEmpty()) {
//					for (String integer : myList) {
////						cityDAO.checkPopularCity(Integer.parseInt(integer));
//						cityService.checkPopularCity(Integer.parseInt(integer));
//					}
//				}
			}
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().write("True");

		}
//			elseif (request.getRequestURI().equals(request.getContextPath() + AdminURLConstant.CITY_STATEWISE_URL)) {
//
////			List<City> citylist = cityDAO.getStateWiseData(Integer.parseInt(request.getParameter("stateid")));
////            Gson json = new Gson();
////            String cityjson =json.toJson(citylist);
//			String cityjson = cityService.getStateWiseCities(Integer.parseInt(request.getParameter("stateid")));
//			response.setContentType("text/html;charset=UTF-8");
//			response.getWriter().write(cityjson);
//		}

	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
