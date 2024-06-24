package com.narola.hotelbooking.dao;

import com.narola.hotelbooking.Booking.dao.BookingDAOMySQL;
import com.narola.hotelbooking.Booking.dao.BookingRoomDAOMysql;
import com.narola.hotelbooking.Booking.dao.IBookingDAO;
import com.narola.hotelbooking.Booking.dao.IBookingRoomDAO;
import com.narola.hotelbooking.Hotel.dao.CancellationRulesDAOMysql;
import com.narola.hotelbooking.Hotel.dao.HotelDAOMysql;
import com.narola.hotelbooking.Hotel.dao.ICancellationRulesDAO;
import com.narola.hotelbooking.Hotel.dao.IHotelDAO;
import com.narola.hotelbooking.Hotel.dao.ISearchHotelDAO;
import com.narola.hotelbooking.Hotel.dao.SearchHotelDAOMysql;
import com.narola.hotelbooking.Room.dao.IRoomDAO;
import com.narola.hotelbooking.Room.dao.RoomDAOMySQL;
import com.narola.hotelbooking.StateCity.dao.CityDAO;
import com.narola.hotelbooking.StateCity.dao.ICityDAO;
import com.narola.hotelbooking.StateCity.dao.IStateDAO;
import com.narola.hotelbooking.StateCity.dao.StateDAO;

public class MySQLDaoFactory extends DAOFactory {

	private IRoomDAO roomDAO;
	private IBookingDAO bookingDAO;
	private IBookingRoomDAO bookingRoomDAO;
	private IHotelDAO hotelDAO;
	private ISearchHotelDAO searchHotelDAO;	
	private ICancellationRulesDAO cancellationRulesDAO;
	private IStateDAO stateDAO;
	private ICityDAO cityDAO;

	@Override
	public IRoomDAO getRoomDAO() {
		if (roomDAO == null) {
			roomDAO = new RoomDAOMySQL();
		}
		return roomDAO;
	}

	@Override
	public IBookingDAO getBookingDAO() {
		if (bookingDAO == null) {
			bookingDAO = new BookingDAOMySQL();
		}
		return bookingDAO;
	}

	@Override
	public IBookingRoomDAO getBookingRoomDAO() {
		if (bookingRoomDAO == null) {
			bookingRoomDAO = new BookingRoomDAOMysql();
		}
		return  bookingRoomDAO;
	}

	@Override
	public IHotelDAO getHotelDAO() {
		if (hotelDAO == null) {
			hotelDAO = new HotelDAOMysql();
		}
		return  hotelDAO;
	}

	@Override
	public ISearchHotelDAO getSearchHotelDAO() {
		if (searchHotelDAO == null) {
			searchHotelDAO = new SearchHotelDAOMysql();
		}
		return  searchHotelDAO;
	}

	@Override
	public ICancellationRulesDAO getCancellationRulesDAO() {
		if (cancellationRulesDAO == null) {
			cancellationRulesDAO = new CancellationRulesDAOMysql();
		}
		return  cancellationRulesDAO;
	}

	@Override
	public IStateDAO getStateDAO() {
		if (stateDAO == null) {
			stateDAO = new StateDAO();
		}
		return  stateDAO;
	}

	@Override
	public ICityDAO getCityDAO() {
		if (cityDAO == null) {
			cityDAO = new CityDAO();
		}
		return  cityDAO;
	}

}
