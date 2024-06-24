package com.narola.hotelbooking.Booking.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.narola.hotelbooking.Booking.dao.BookingDAOMySQL;
import com.narola.hotelbooking.Booking.dao.IBookingDAO;
import com.narola.hotelbooking.Booking.model.Booking;
import com.narola.hotelbooking.Booking.model.SortingColumnOrder;
import com.narola.hotelbooking.Booking.service.IBookingService;
import com.narola.hotelbooking.Booking.service.IBookingServiceFectory;
import com.narola.hotelbooking.Exception.ApplicationException;
import com.narola.hotelbooking.Utility.Constant;
import com.narola.hotelbooking.Utility.ServiceFectory;

/**
 * Servlet implementation class ListBookingServlet
 */
public class ListBookingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public static List<Booking> bookingList = null;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			IBookingService bookingService = ServiceFectory.getInstance().getBookingService();

			bookingList = bookingService.getAllBooking();

			request.setAttribute("bookingdata", bookingList);
			request.setAttribute("ColumnOrder", SortingColumnOrder.columnOrder);

			RequestDispatcher rd = request.getRequestDispatcher("admin/booking/ShowAllBooking.jsp");
			rd.forward(request, response);
		} catch (ApplicationException e) {
			request.setAttribute(Constant.ERROR_MSG, e.getMessage());
			RequestDispatcher rd = request.getRequestDispatcher("admin/ErrorPage.jsp");
			rd.forward(request, response);
		}
	}

}
