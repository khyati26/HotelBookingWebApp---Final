package com.narola.hotelbooking.Booking.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.narola.hotelbooking.Booking.service.IBookingService;
import com.narola.hotelbooking.Booking.service.IBookingServiceFectory;
import com.narola.hotelbooking.Exception.ApplicationException;
import com.narola.hotelbooking.Hotel.service.IHotelService;
import com.narola.hotelbooking.Hotel.service.IHotelServiceFectory;
import com.narola.hotelbooking.Room.model.Room;
import com.narola.hotelbooking.Room.service.IRoomService;
import com.narola.hotelbooking.Room.service.RoomServiceFectory;
import com.narola.hotelbooking.Room.service.Impl.RoomServiceImpl;
import com.narola.hotelbooking.Utility.Constant;
import com.narola.hotelbooking.Utility.ServiceFectory;

/**
 * Servlet implementation class BookingServlet
 */
public class BookingDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			IHotelService hotelService = ServiceFectory.getInstance().getHotelService();
			IBookingService bookingService = ServiceFectory.getInstance().getBookingService();
			HttpSession session = request.getSession();

			List<Room> roomList = bookingService.setBookingDetails(request.getParameter("roomid"));

			request.setAttribute("hotel", hotelService.getHotel(Integer.parseInt(request.getParameter("hotelid"))));
			request.setAttribute("totalprice", request.getParameter("totprice2"));
			request.setAttribute("roomlist", roomList);
			session.setAttribute("roomlist", roomList);
			RequestDispatcher rd = request.getRequestDispatcher("customerside/Bookingpage.jsp");
			rd.forward(request, response);
			
		} catch (ApplicationException e) {
			request.setAttribute(Constant.ERROR_MSG, e.getMessage());
			RequestDispatcher rd = request.getRequestDispatcher("customerside/ErrorPage.jsp");
			rd.forward(request, response);
		}
	}

}
