package com.narola.hotelbooking.Hotel.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.narola.hotelbooking.Exception.ApplicationException;
import com.narola.hotelbooking.Hotel.model.CancellationRules;
import com.narola.hotelbooking.Hotel.model.Hotel;
import com.narola.hotelbooking.Hotel.service.ICancellationRulesService;
import com.narola.hotelbooking.Hotel.service.IHotelService;
import com.narola.hotelbooking.Hotel.service.IHotelServiceFectory;
import com.narola.hotelbooking.Room.dao.IRoomDAO;
import com.narola.hotelbooking.Room.model.Room;
import com.narola.hotelbooking.Room.service.IRoomService;
import com.narola.hotelbooking.Room.service.Impl.RoomServiceImpl;
import com.narola.hotelbooking.Utility.AdminURLConstant;
import com.narola.hotelbooking.Utility.Constant;
import com.narola.hotelbooking.Utility.ServiceFectory;
import com.narola.hotelbooking.dao.DAOFactory;

/**
 * Servlet implementation class AddHotelServlet
 */
public class AddHotelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			IHotelService hotelService = ServiceFectory.getInstance().getHotelService();
			ICancellationRulesService cancellationService = ServiceFectory.getInstance().getCancellationService();
			IRoomService roomService =  ServiceFectory.getInstance().getRoomService();			

			Hotel hotel = new Hotel();
			hotel.setName(request.getParameter("name"));
			hotel.setCityId(Integer.parseInt(request.getParameter("cityid")));
			hotel.setStateId(Integer.parseInt(request.getParameter("stateid")));
			hotel.setAddress(request.getParameter("address"));
			hotel.setRating(Integer.parseInt(request.getParameter("rating")));
			hotel.setEmailId(request.getParameter("email"));
			hotel.setServices(request.getParameter("service"));
			hotel.setPolicy(request.getParameter("policy"));
			hotel.setImage("");

			hotel.setId(hotelService.addHotel(hotel));

			String[] hoursarray = request.getParameterValues("hours");
			String[] refundarray = request.getParameterValues("refund");

			CancellationRules cancellationrule = new CancellationRules();

			cancellationrule.setHotelid(hotel.getId());
			for (int i = 0; i < hoursarray.length; i++) {

				if (!hoursarray[i].isEmpty() || !refundarray[i].isEmpty()) {
					cancellationrule.setHours(Integer.parseInt(hoursarray[i]));
					cancellationrule.setRefundpercentsge(Integer.parseInt(refundarray[i]));

					cancellationrule.setId(cancellationService.addCancellationRule(cancellationrule));
				}
			}

			Room room = new Room();
			room.setName(request.getParameter("roomname"));
			room.setHotelID(hotel.getId());
			room.setPrice(Double.valueOf(request.getParameter("price")));
			room.setQty(Integer.parseInt(request.getParameter("qty")));
			room.setInclusions(request.getParameter("inclusions"));
			room.setMaxcapacity(Integer.valueOf(request.getParameter("maxcapacity")));
			room.setDescription(request.getParameter("description"));
			room.setImage("");
			room.setId(roomService.addRoom(room));

			hotel = hotelService.addHotelRoomImages(hotel, room, request);

			hotelService.updateHotel(hotel);

			List<Room> rooms = (List<Room>) hotel.getRooms();
			room.setImage(rooms.get(0).getImage());
			roomService.updateRoom(room);
			response.sendRedirect(request.getContextPath() + AdminURLConstant.DISPLAYALL_HOTELS_URL);
		} catch (ApplicationException e) {
			request.setAttribute(Constant.ERROR_MSG, e.getMessage());
			RequestDispatcher rd = request.getRequestDispatcher("admin/ErrorPage.jsp");
			rd.forward(request, response);
		}
	}

}
