package com.narola.hotelbooking.Room.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.narola.hotelbooking.Exception.ApplicationException;
import com.narola.hotelbooking.Hotel.model.Hotel;
import com.narola.hotelbooking.Room.service.IRoomService;
import com.narola.hotelbooking.Room.service.Impl.RoomServiceImpl;
import com.narola.hotelbooking.Utility.AdminURLConstant;
import com.narola.hotelbooking.Utility.Constant;
import com.narola.hotelbooking.Utility.ServiceFectory;

/**
 * Servlet implementation class DeleteRoomServlet
 */
public class DeleteRoomServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			IRoomService roomService = ServiceFectory.getInstance().getRoomService();
			roomService.deleteRoom(Integer.parseInt(request.getParameter("id")));

			HttpSession session = request.getSession();
			Hotel hotel = (Hotel) session.getAttribute("hotel");

			response.sendRedirect(
					request.getContextPath() + AdminURLConstant.DISPLAYALL_ROOMS_URL + "?hotelid=" + hotel.getId());

		} catch (ApplicationException e) {

			request.setAttribute(Constant.ERROR_MSG, e.getMessage());
			RequestDispatcher rd = request.getRequestDispatcher("admin/ErrorPage.jsp");
			rd.forward(request, response);
		}
	}

}
