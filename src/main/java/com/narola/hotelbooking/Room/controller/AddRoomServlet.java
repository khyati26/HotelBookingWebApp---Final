package com.narola.hotelbooking.Room.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.narola.hotelbooking.Exception.ApplicationException;
import com.narola.hotelbooking.Room.model.Room;
import com.narola.hotelbooking.Room.service.IRoomService;
import com.narola.hotelbooking.Room.service.Impl.RoomServiceImpl;
import com.narola.hotelbooking.Utility.AdminURLConstant;
import com.narola.hotelbooking.Utility.Constant;
import com.narola.hotelbooking.Utility.ServiceFectory;

/**
 * Servlet implementation class RoomServlet
 */
public class AddRoomServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getRequestURI().equals(request.getContextPath() + AdminURLConstant.ADD_ROOMPAGE_URL)) {
//			request.setAttribute("room", new Room());
			RequestDispatcher rd = request.getRequestDispatcher("admin/room/AddHotelWiseRoom.jsp");
			rd.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
		IRoomService roomService = ServiceFectory.getInstance().getRoomService();
		
		Room room = new Room();
		room.setName(request.getParameter("name"));
		room.setHotelID(Integer.parseInt(request.getParameter("hotelid")));
		room.setPrice(Double.valueOf(request.getParameter("price")));
		room.setQty(Integer.parseInt(request.getParameter("qty")));
		room.setInclusions(request.getParameter("inclusions"));
		room.setMaxcapacity(Integer.valueOf(request.getParameter("maxcapacity")));
		room.setDescription(request.getParameter("description"));
		room.setImage("");

		roomService.addRoom(room, request);

		response.sendRedirect(
				request.getContextPath() + AdminURLConstant.DISPLAYALL_ROOMS_URL + "?hotelid=" + room.getHotelID());
		
		}catch(ApplicationException e) {
		
			request.setAttribute(Constant.ERROR_MSG, e.getMessage());
			RequestDispatcher rd = request.getRequestDispatcher("admin/ErrorPage.jsp");
			rd.forward(request, response);
		}
		
	}
}
