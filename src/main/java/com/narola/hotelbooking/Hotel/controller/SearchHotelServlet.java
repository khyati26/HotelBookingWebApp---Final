package com.narola.hotelbooking.Hotel.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.narola.hotelbooking.Exception.ApplicationException;
import com.narola.hotelbooking.Hotel.dao.SearchHotelDAOMysql;
import com.narola.hotelbooking.Hotel.model.SearchHotel;
import com.narola.hotelbooking.Hotel.model.SearchHotelCriteria;
import com.narola.hotelbooking.Hotel.service.IHotelServiceFectory;
import com.narola.hotelbooking.Hotel.service.ISearchHotelService;
import com.narola.hotelbooking.Hotel.service.Impl.IHotelServiceImpl;
import com.narola.hotelbooking.Utility.Constant;
import com.narola.hotelbooking.Utility.ServiceFectory;

/**
 * Servlet implementation class SearchHotelServlet
 */
public class SearchHotelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			ISearchHotelService hotelServiceImpl = ServiceFectory.getInstance().getSearchHotelService();

			SearchHotelCriteria searchHotelCriteria = new SearchHotelCriteria();
			searchHotelCriteria.setCity(request.getParameter("city"));
			searchHotelCriteria.setCheckIn(request.getParameter("check-in"));
			searchHotelCriteria.setCheckOut(request.getParameter("check-out"));
			searchHotelCriteria.setAdult(Integer.parseInt(request.getParameter("adult")));
			searchHotelCriteria.setChildren(Integer.parseInt(request.getParameter("children")));
			searchHotelCriteria.setRoom(Integer.parseInt(request.getParameter("room")));

			searchHotelCriteria
					.setDays(hotelServiceImpl.calculateDaysParam(searchHotelCriteria.getCheckIn(), searchHotelCriteria.getCheckOut()));

			HttpSession session = request.getSession(true);
			session.setAttribute("searchdetails", searchHotelCriteria);

			request.setAttribute("hotels", hotelServiceImpl.getCityWiseHotel(searchHotelCriteria));

			RequestDispatcher rd = request.getRequestDispatcher("customerside/SearchResult_ListofHotels.jsp");
			rd.forward(request, response);
			
		} catch (ApplicationException e) {
			
			request.setAttribute(Constant.ERROR_MSG, e.getMessage());
			RequestDispatcher rd = request.getRequestDispatcher("customerside/ErrorPage.jsp");
			rd.forward(request, response);
		}
	}

}
