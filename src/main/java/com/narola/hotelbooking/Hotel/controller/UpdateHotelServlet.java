package com.narola.hotelbooking.Hotel.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.narola.hotelbooking.Exception.ApplicationException;
import com.narola.hotelbooking.Hotel.dao.ICancellationRulesDAO;
import com.narola.hotelbooking.Hotel.model.CancellationRules;
import com.narola.hotelbooking.Hotel.model.Hotel;
import com.narola.hotelbooking.Hotel.service.ICancellationRulesService;
import com.narola.hotelbooking.Hotel.service.IHotelService;
import com.narola.hotelbooking.Hotel.service.IHotelServiceFectory;
import com.narola.hotelbooking.Utility.AdminURLConstant;
import com.narola.hotelbooking.Utility.Constant;
import com.narola.hotelbooking.Utility.ServiceFectory;
import com.narola.hotelbooking.dao.DAOFactory;

/**
 * Servlet implementation class UpdateHotelServlet
 */
public class UpdateHotelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			IHotelService hotelService = ServiceFectory.getInstance().getHotelService();
			ICancellationRulesService cancellationRulesService = ServiceFectory.getInstance().getCancellationService();

			String[] hoursarray = request.getParameterValues("hours");
			String[] refundarray = request.getParameterValues("refund");
			String[] cancleidarray = request.getParameterValues("cancelid");
			String[] deletecancleidarray = request.getParameterValues("deletecancelid");

			CancellationRules cancellationrules = new CancellationRules();

			cancellationrules.setHotelid(Integer.parseInt(request.getParameter("id")));
			if (hoursarray != null) {
				for (int i = 0; i < hoursarray.length; i++) {
					if (!cancleidarray[i].equals("0")) {
						cancellationrules.setHours(Integer.parseInt(hoursarray[i]));
						cancellationrules.setRefundpercentsge(Integer.parseInt(refundarray[i]));
						cancellationrules.setId(Integer.parseInt(cancleidarray[i]));
						cancellationRulesService.editCancellationRule(cancellationrules);
					} else {
						cancellationrules.setHours(Integer.parseInt(hoursarray[i]));
						cancellationrules.setRefundpercentsge(Integer.parseInt(refundarray[i]));
						cancellationrules.setId(cancellationRulesService.addCancellationRule(cancellationrules));

					}
				}
			}

			if (deletecancleidarray != null && deletecancleidarray.length != 0) {
				for (String id : deletecancleidarray) {
					cancellationRulesService.deleteCancellationRule(Integer.parseInt(id));
				}
			}

			Hotel hotel = new Hotel();
			hotel.setId(Integer.parseInt(request.getParameter("id")));
			hotel.setName(request.getParameter("name"));
			hotel.setCityId(Integer.parseInt(request.getParameter("cityid")));
			hotel.setStateId(Integer.parseInt(request.getParameter("stateid")));
			hotel.setAddress(request.getParameter("address"));
			hotel.setRating(Integer.parseInt(request.getParameter("rating")));
			hotel.setEmailId(request.getParameter("email"));
			hotel.setServices(request.getParameter("service"));
			hotel.setPolicy(request.getParameter("policy"));

			hotelService.UpdateHotel(hotel, request.getParameter("deletedfiles"), request);
			cancellationRulesService.editCancellationRule(cancellationrules);
			response.sendRedirect(request.getContextPath() + AdminURLConstant.DISPLAYALL_HOTELS_URL);
		} catch (ApplicationException e) {
			request.setAttribute(Constant.ERROR_MSG, e.getMessage());
			RequestDispatcher rd = request.getRequestDispatcher("admin/ErrorPage.jsp");
			rd.forward(request, response);
		}

	}



}
