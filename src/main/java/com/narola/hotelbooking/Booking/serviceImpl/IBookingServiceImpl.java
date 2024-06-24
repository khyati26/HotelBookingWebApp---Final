package com.narola.hotelbooking.Booking.serviceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import com.google.gson.Gson;
import com.narola.hotelbooking.Booking.model.Booking;
import com.narola.hotelbooking.Booking.model.BookingFilterParam;
import com.narola.hotelbooking.Booking.service.IBookingService;
import com.narola.hotelbooking.Exception.ApplicationException;
import com.narola.hotelbooking.Exception.DatabaseException;
import com.narola.hotelbooking.Room.dao.IRoomDAO;
import com.narola.hotelbooking.Room.model.Room;
import com.narola.hotelbooking.dao.DAOFactory;

public class IBookingServiceImpl implements IBookingService {

	@Override
	public int addBooking(Booking booking, List<Room> roomList) throws ApplicationException {
		try {
			int bookingid = DAOFactory.getDaoFactory().getBookingDAO().createBooking(booking, roomList);
			return bookingid;
		} catch (DatabaseException e) {
			throw new ApplicationException("Opps,Something went wrong.", e);
		} catch (Exception e) {
			throw new ApplicationException("Opps,Something went wrong.", e);
		}

	}

	@Override
	public List<Booking> getAllBooking() throws ApplicationException {
		try {
			List<Booking> bookings = DAOFactory.getDaoFactory().getBookingDAO().showallbooking();
			return bookings;
		} catch (DatabaseException e) {
			throw new ApplicationException("Opps,Something went wrong.", e);
		} catch (Exception e) {
			throw new ApplicationException("Opps,Something went wrong.", e);
		}
	}

	@Override
	public List<Booking> searchBooking(BookingFilterParam filterParam) throws ApplicationException {
		try {
			List<Booking> bookings = DAOFactory.getDaoFactory().getBookingDAO().searchBookingData(filterParam);
			return bookings;
		} catch (DatabaseException e) {
			throw new ApplicationException("Opps,Something went wrong.", e);
		} catch (Exception e) {
			throw new ApplicationException("Opps,Something went wrong.", e);
		}
	}

	@Override
	public List<Room> setBookingDetails(String roomid) throws ApplicationException {
		try {
			Gson g = new Gson();
			HashMap<Double, String> s = g.fromJson((String) roomid, HashMap.class);
			List<Room> roomList = new ArrayList<>();
			IRoomDAO roomDAO = DAOFactory.getDaoFactory().getRoomDAO();

			for (Entry<Double, String> pair : s.entrySet()) {
				Room room = roomDAO.viewRoom(pair.getKey().intValue());
				room.setAvailableroom(Integer.parseInt(pair.getValue()));
				roomList.add(room);
			}
			return roomList;
		} catch (DatabaseException e) {
			throw new ApplicationException("Opps,Something went wrong.", e);
		} catch (Exception e) {
			throw new ApplicationException("Opps,Something went wrong.", e);
		}
	}

}
