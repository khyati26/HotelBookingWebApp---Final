package com.narola.hotelbooking.Room.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.narola.hotelbooking.Exception.ApplicationException;
import com.narola.hotelbooking.Exception.DatabaseException;
import com.narola.hotelbooking.Hotel.model.SearchHotelCriteria;
import com.narola.hotelbooking.Hotel.model.SearchHotelResult;
import com.narola.hotelbooking.Room.service.IRoomService;
import com.narola.hotelbooking.Room.service.Impl.RoomServiceImpl;
import com.narola.hotelbooking.Utility.Constant;
import com.narola.hotelbooking.Utility.ServiceFectory;

public class AvailableRoomServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			HttpSession session = request.getSession();
			IRoomService roomService = ServiceFectory.getInstance().getRoomService();

			SearchHotelCriteria searchHotelCriteria = (SearchHotelCriteria) session.getAttribute("searchdetails");
			searchHotelCriteria.setHotelId(Integer.parseInt(request.getParameter("hotelid")));

			SearchHotelResult searchResult = roomService.searchRooms(searchHotelCriteria);

			request.setAttribute("roomlist", searchResult.getRoomSet());
			request.setAttribute("hotel", searchResult.getHotel());
			RequestDispatcher rd = request.getRequestDispatcher("customerside/Availablerooms.jsp");
			rd.forward(request, response);
			
		} catch (ApplicationException e) {
		
			request.setAttribute(Constant.ERROR_MSG, e.getMessage());
			RequestDispatcher rd = request.getRequestDispatcher("customerside/ErrorPage.jsp");
			rd.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
