package com.narola.hotelbooking.Room.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.narola.hotelbooking.Exception.ApplicationException;
import com.narola.hotelbooking.Hotel.model.Hotel;
import com.narola.hotelbooking.Hotel.service.IHotelService;
import com.narola.hotelbooking.Hotel.service.IHotelServiceFectory;
import com.narola.hotelbooking.Room.model.Room;
import com.narola.hotelbooking.Room.service.IRoomService;
import com.narola.hotelbooking.Room.service.Impl.RoomServiceImpl;
import com.narola.hotelbooking.Utility.AdminURLConstant;
import com.narola.hotelbooking.Utility.Constant;
import com.narola.hotelbooking.Utility.ServiceFectory;
import com.narola.hotelbooking.Utility.UserURLConstant;

/**
 * Servlet implementation class ListRoomServlet
 */
public class ListRoomServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		IRoomService roomService = ServiceFectory.getInstance().getRoomService();
		IHotelService hotelService = ServiceFectory.getInstance().getHotelService();

		if (request.getRequestURI().equals(request.getContextPath() + AdminURLConstant.DISPLAYALL_ROOMS_URL)) {
			try {
				int hotelId = Integer.parseInt(request.getParameter("hotelid"));
				List<Room> roomList = roomService.getAllRoom(hotelId);
				HttpSession session = request.getSession();
				Hotel hotel = hotelService.getHotel(hotelId);
				session.setAttribute("hotel", hotel);
				request.setAttribute("rooms", roomList);
				RequestDispatcher rd = request.getRequestDispatcher("admin/room/ShowHotelWiseRooms.jsp");
				rd.forward(request, response);
			} catch (ApplicationException e) {
				request.setAttribute(Constant.ERROR_MSG, e.getMessage());
				RequestDispatcher rd = request.getRequestDispatcher("admin/ErrorPage.jsp");
				rd.forward(request, response);
			}
		} else if (request.getRequestURI().equals(request.getContextPath() + AdminURLConstant.VIEW_ROOMPAGE_URL)) {
			try {
				Room room = roomService.getRoom(Integer.parseInt(request.getParameter("id")));
				request.setAttribute("room", room);
				RequestDispatcher rd = request.getRequestDispatcher("admin/room/ViewRoom.jsp");
				rd.forward(request, response);
			} catch (ApplicationException e) {
				request.setAttribute(Constant.ERROR_MSG, e.getMessage());
				RequestDispatcher rd = request.getRequestDispatcher("admin/ErrorPage.jsp");
				rd.forward(request, response);
			}
		} else if (request.getRequestURI().equals(request.getContextPath() + UserURLConstant.ROOM_VIEW_URL)) {
			try {
				Room room = roomService.getRoom(Integer.parseInt(request.getParameter("id")));
				request.setAttribute("room", room);
				RequestDispatcher rd = request.getRequestDispatcher("customerside/Roomview.jsp");
				rd.forward(request, response);
			} catch (ApplicationException e) {

				request.setAttribute(Constant.ERROR_MSG, e.getMessage());
				RequestDispatcher rd = request.getRequestDispatcher("customerside/ErrorPage.jsp");
				rd.forward(request, response);
			}
		}
	}

}
