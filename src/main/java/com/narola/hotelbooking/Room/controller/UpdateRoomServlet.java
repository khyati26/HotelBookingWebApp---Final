package com.narola.hotelbooking.Room.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.narola.hotelbooking.Exception.ApplicationException;
import com.narola.hotelbooking.Room.model.Room;
import com.narola.hotelbooking.Room.service.IRoomService;
import com.narola.hotelbooking.Room.service.Impl.RoomServiceImpl;
import com.narola.hotelbooking.Utility.AdminURLConstant;
import com.narola.hotelbooking.Utility.Constant;
import com.narola.hotelbooking.Utility.ServiceFectory;

/**
 * Servlet implementation class UpdateRoomServlet
 */
public class UpdateRoomServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			IRoomService roomService = ServiceFectory.getInstance().getRoomService();
			String roomIDStr = request.getParameter("id");
			
			if (roomIDStr == null || roomIDStr.trim().isEmpty()) {
				throw new ApplicationException("");
			}
			Room room = roomService.getRoom(Integer.parseInt(roomIDStr), request);
			request.setAttribute("room", room);
			RequestDispatcher rd = request.getRequestDispatcher("admin/room/UpdateHotelWiseRoom.jsp");
			rd.forward(request, response);
		} catch (ApplicationException e) {
			request.setAttribute(Constant.ERROR_MSG, e.getMessage());
			RequestDispatcher rd = request.getRequestDispatcher("admin/ErrorPage.jsp");
			rd.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			IRoomService roomService = new RoomServiceImpl();
			Room room = new Room();
			room.setId(Integer.parseInt(request.getParameter("id")));
			room.setName(request.getParameter("name"));
			room.setHotelID(Integer.parseInt(request.getParameter("hotelid")));
			room.setPrice(Double.valueOf(request.getParameter("price")));
			room.setQty(Integer.parseInt(request.getParameter("qty")));
			room.setInclusions(request.getParameter("inclusions"));
			room.setMaxcapacity(Integer.parseInt(request.getParameter("maxcapacity")));
			room.setDescription(request.getParameter("description"));
			roomService.updateRoom(room, request);
			response.sendRedirect(
					request.getContextPath() + AdminURLConstant.DISPLAYALL_ROOMS_URL + "?hotelid=" + room.getHotelID());
		} catch (ApplicationException e) {
			request.setAttribute(Constant.ERROR_MSG, e.getMessage());
			RequestDispatcher rd = request.getRequestDispatcher("admin/ErrorPage.jsp");
			rd.forward(request, response);
		}
	}



}
