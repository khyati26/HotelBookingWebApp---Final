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
import com.narola.hotelbooking.Booking.model.BookingFilterParam;
import com.narola.hotelbooking.Booking.service.IBookingService;
import com.narola.hotelbooking.Booking.service.IBookingServiceFectory;
import com.narola.hotelbooking.Exception.ApplicationException;
import com.narola.hotelbooking.Utility.Constant;
import com.narola.hotelbooking.Utility.ServiceFectory;

/**
 * Servlet implementation class SearchBookingServlet
 */
public class SearchBookingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			IBookingService bookingService = ServiceFectory.getInstance().getBookingService();
			BookingFilterParam filterParam = new BookingFilterParam();

			if (!request.getParameter("bookingid").isEmpty()) {
				filterParam.setBookingId(Integer.parseInt(request.getParameter("bookingid")));
			} else {
				if (!request.getParameter("checkin").isEmpty()) {
					filterParam.setCheckIn(request.getParameter("checkin"));
				}
				if (!request.getParameter("checkout").isEmpty()) {
					filterParam.setCheckOut(request.getParameter("checkout"));
				}
				if (!request.getParameter("bookingstatus").isEmpty()) {
					filterParam.setBookingStatus(request.getParameter("bookingstatus"));

				}
				if (!request.getParameter("paymentstatus").isEmpty()) {
					filterParam.setPaymentStatus(request.getParameter("paymentstatus"));
				}
			}
			List<Booking> books = bookingService.searchBooking(filterParam);

			request.setAttribute("bookingdata", books);

			RequestDispatcher rd = request.getRequestDispatcher("admin/booking/ShowAllBooking.jsp");
			rd.forward(request, response);
		} catch (ApplicationException e) {
			request.setAttribute(Constant.ERROR_MSG, e.getMessage());
			RequestDispatcher rd = request.getRequestDispatcher("admin/ErrorPage.jsp");
			rd.forward(request, response);
		}
	}

}
