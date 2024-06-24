package com.narola.hotelbooking.Booking.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.narola.hotelbooking.Booking.dao.BookingDAOMySQL;
import com.narola.hotelbooking.Booking.dao.IBookingDAO;
import com.narola.hotelbooking.Booking.model.Booking;
import com.narola.hotelbooking.Booking.service.IBookingService;
import com.narola.hotelbooking.Booking.service.IBookingServiceFectory;
import com.narola.hotelbooking.Customer.Customer;
import com.narola.hotelbooking.Exception.ApplicationException;
import com.narola.hotelbooking.Hotel.model.SearchHotel;
import com.narola.hotelbooking.Hotel.model.SearchHotelCriteria;
import com.narola.hotelbooking.RazorpayPaymentGateway.PaymentGatway;
import com.narola.hotelbooking.Room.model.Room;
import com.narola.hotelbooking.Utility.Constant;
import com.narola.hotelbooking.Utility.ServiceFectory;
import com.narola.hotelbooking.Utility.StatusConstants;

/**
 * Servlet implementation class InsertBookingEntryServlet
 */
public class InsertBookingEntryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		try {

			IBookingService bookingService = ServiceFectory.getInstance().getBookingService();

			HttpSession session = request.getSession(true);
			Customer customer = (Customer) session.getAttribute("user");
			SearchHotelCriteria searchdetailCriteria = (SearchHotelCriteria) session.getAttribute("searchdetails");
			List<Room> roomlist = (List<Room>) session.getAttribute("roomlist");

			Booking booking = new Booking();
			booking.setAdults(searchdetailCriteria.getAdult());
			booking.setChildren(searchdetailCriteria.getChildren());
			booking.setCheckIn(searchdetailCriteria.getCheckIn());
			booking.setCheckOut(searchdetailCriteria.getCheckOut());
			booking.setCustomerId(customer.getId());
			booking.setHotelId(Integer.parseInt(request.getParameter("hotelid")));
			booking.setTotalAmount(Double.parseDouble(request.getParameter("totalprice")));
			booking.setPaymentMode(StatusConstants.PAYMENT_MODE_ONLINE);
			booking.setPaymentStatus(StatusConstants.PAYMENT_PENDING);

			int totalroomqty = 0;
			for (Room r : roomlist) {
				totalroomqty = totalroomqty + r.getAvailableroom();
			}
			booking.setTotalRoomQty(totalroomqty);

			int bookingid = bookingService.addBooking(booking, roomlist);
			booking.setId(bookingid);

			if (PaymentGatway.createOrder(booking) == 200) {
				session.setAttribute("booking", booking);
				session.setMaxInactiveInterval(10 * 60);
				request.getRequestDispatcher("/customerside/Razorpayform.jsp").forward(request, response);
			} else {
				throw new ApplicationException("Something went wrong in Payment process..");
			}
		} catch (ApplicationException e) {
			request.setAttribute(Constant.ERROR_MSG, e.getMessage());
			RequestDispatcher rd = request.getRequestDispatcher("customerside/ErrorPage.jsp");
			rd.forward(request, response);
		}

	}
}
