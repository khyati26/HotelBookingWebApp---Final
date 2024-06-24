package com.narola.hotelbooking.Hotel.controller;

import com.narola.hotelbooking.Exception.ApplicationException;
import com.narola.hotelbooking.Hotel.model.Hotel;
import com.narola.hotelbooking.Hotel.service.IHotelService;
import com.narola.hotelbooking.StateCity.dao.ICityDAO;
import com.narola.hotelbooking.StateCity.dao.IStateDAO;
import com.narola.hotelbooking.Utility.AdminURLConstant;
import com.narola.hotelbooking.Utility.Constant;
import com.narola.hotelbooking.Utility.ServiceFectory;
import com.narola.hotelbooking.dao.DAOFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class HotelServlet1
 */
public class HotelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		IHotelService hotelService = ServiceFectory.getInstance().getHotelService();
		ICityDAO cityDAO = DAOFactory.getDaoFactory().getCityDAO();
		IStateDAO stateDAO = DAOFactory.getDaoFactory().getStateDAO();

		if (request.getRequestURI().equals(request.getContextPath() + AdminURLConstant.DISPLAYALL_HOTELS_URL)) {
			try {

				request.setAttribute("hotels", hotelService.getAllHotel());
				RequestDispatcher rd = request.getRequestDispatcher("admin/hotel/ShowAllHotels.jsp");
				rd.forward(request, response);

			} catch (ApplicationException e) {

				request.setAttribute(Constant.ERROR_MSG, "Something went wrong.");
				RequestDispatcher rd = request.getRequestDispatcher("admin/hotel/ShowAllHotels.jsp");
				rd.forward(request, response);
			}
		} else if (request.getRequestURI().equals(request.getContextPath() + AdminURLConstant.VIEW_HOTELPAGE_URL)) {

			try {

				request.setAttribute("hotel", hotelService.getHotel(Integer.parseInt(request.getParameter("id"))));
				RequestDispatcher rd = request.getRequestDispatcher("admin/hotel/ViewHotel.jsp");
				rd.forward(request, response);
			} catch (ApplicationException e) {

				request.setAttribute(Constant.ERROR_MSG, "Something went wrong.");
				RequestDispatcher rd = request.getRequestDispatcher("admin/hotel/ShowAllHotels.jsp");
				rd.forward(request, response);
			}

		} else if (request.getRequestURI().equals(request.getContextPath() + AdminURLConstant.DELETE_HOTEL_URL)) {
			try {

				hotelService.deleteHotel(Integer.parseInt(request.getParameter("id")));
				response.sendRedirect(request.getContextPath() + AdminURLConstant.DISPLAYALL_HOTELS_URL);
			} catch (ApplicationException e) {

				request.setAttribute(Constant.ERROR_MSG, "Something went wrong.");
				RequestDispatcher rd = request.getRequestDispatcher("admin/hotel/ShowAllHotels.jsp");
				rd.forward(request, response);
			}

		} else if (request.getRequestURI().equals(request.getContextPath() + AdminURLConstant.EDIT_HOTELPAGE_URL)) {

			try {
				Hotel hotel = hotelService.getHotelForUpdate(Integer.parseInt(request.getParameter("id")), request);
				request.setAttribute("hotel", hotel);
				request.setAttribute("states", stateDAO.getStates());
				request.setAttribute("cities", cityDAO.getStateWiseData(hotel.getStateId()));
				RequestDispatcher rd = request.getRequestDispatcher("admin/hotel/UpdateHotel.jsp");
				rd.forward(request, response);
			} catch (ApplicationException e) {

				request.setAttribute(Constant.ERROR_MSG, "Opps....");
				RequestDispatcher rd = request.getRequestDispatcher("admin/hotel/UpdateHotel.jsp");
				rd.forward(request, response);
			}

		} else if (request.getRequestURI().equals(request.getContextPath() + AdminURLConstant.ADD_HOTELPAGE_URL)) {

			request.setAttribute("states", stateDAO.getStates());
			RequestDispatcher rd = request.getRequestDispatcher("admin/hotel/AddHotel.jsp");
			rd.forward(request, response);

		}
	}

}
