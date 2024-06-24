package com.narola.hotelbooking.dao;

import com.narola.hotelbooking.Booking.dao.IBookingDAO;
import com.narola.hotelbooking.Booking.dao.IBookingRoomDAO;
import com.narola.hotelbooking.Hotel.dao.ICancellationRulesDAO;
import com.narola.hotelbooking.Hotel.dao.IHotelDAO;
import com.narola.hotelbooking.Hotel.dao.ISearchHotelDAO;
import com.narola.hotelbooking.Room.dao.IRoomDAO;
import com.narola.hotelbooking.StateCity.dao.ICityDAO;
import com.narola.hotelbooking.StateCity.dao.IStateDAO;

public abstract class DAOFactory {

	public static  String MYSQL = "MYSQL";
	public static  String POSTGRES = "POSTGRES";

	private static DAOFactory daoFactory = null;

	public abstract IRoomDAO getRoomDAO();

	public abstract IBookingDAO getBookingDAO();

	public abstract IBookingRoomDAO getBookingRoomDAO();
	
	public abstract IHotelDAO getHotelDAO();

	public abstract ISearchHotelDAO getSearchHotelDAO();
	
	public abstract ICancellationRulesDAO getCancellationRulesDAO();

	public abstract IStateDAO getStateDAO();

	public abstract ICityDAO getCityDAO();

	
	public static DAOFactory getDaoFactory() {
		return daoFactory;
	}

	public static void init(String daoType) throws Exception {
		if (daoType.equals(MYSQL)) {
			daoFactory = new MySQLDaoFactory();
		} else if (daoType.equals(POSTGRES)) {
			daoFactory = new PostgresDaoFactory();
		} else {
			throw new Exception("Type is not supported yet");
		}
	}

}
