package com.narola.hotelbooking.dao;

import com.narola.hotelbooking.Booking.dao.IBookingDAO;
import com.narola.hotelbooking.Booking.dao.IBookingRoomDAO;
import com.narola.hotelbooking.Hotel.dao.CancellationRulesDAOPostgre;
import com.narola.hotelbooking.Hotel.dao.HotelDAOPostgre;
import com.narola.hotelbooking.Hotel.dao.ICancellationRulesDAO;
import com.narola.hotelbooking.Hotel.dao.IHotelDAO;
import com.narola.hotelbooking.Hotel.dao.ISearchHotelDAO;
import com.narola.hotelbooking.Room.dao.IRoomDAO;
import com.narola.hotelbooking.Room.dao.RoomDAOPostgres;
import com.narola.hotelbooking.StateCity.dao.ICityDAO;
import com.narola.hotelbooking.StateCity.dao.IStateDAO;

public class PostgresDaoFactory extends DAOFactory {

	private IRoomDAO roomDAO;
//	private IBookingDAO bookingDAO;
//	private IBookingRoomDAO bookingRoomDAO;
	private IHotelDAO hotelDAO;
//	private ISearchHotelDAO searchHotelDAO;	
	private ICancellationRulesDAO cancellationRulesDAO;
	

	@Override
	public IRoomDAO getRoomDAO() {
		if (roomDAO == null) {
			roomDAO = new RoomDAOPostgres();
		}
		return roomDAO;
	}

	@Override
	public IBookingDAO getBookingDAO() {
//		if (bookingDAO == null) {
//			bookingDAO = new BookingDAO();
//		}
		return null;
	}

	@Override
	public IBookingRoomDAO getBookingRoomDAO() {
//		if (bookingRoomDAO == null) {
//			bookingRoomDAO = new BookingRoomDAO();
//		}
		return  null;
	}

	@Override
	public IHotelDAO getHotelDAO() {
		if (hotelDAO == null) {
			hotelDAO = new HotelDAOPostgre();
		}
		return  hotelDAO;
	}

	@Override
	public ISearchHotelDAO getSearchHotelDAO() {
//		if (searchHotelDAO == null) {
//			searchHotelDAO = new SearchHotelDAO();
//		}
		return  null;
	}

	@Override
	public ICancellationRulesDAO getCancellationRulesDAO() {
		if (cancellationRulesDAO == null) {
			cancellationRulesDAO = new CancellationRulesDAOPostgre();
		}
		return  cancellationRulesDAO;
	}

	@Override
	public IStateDAO getStateDAO() {
		return null;
	}

	@Override
	public ICityDAO getCityDAO() {
		return null;
	}

}
