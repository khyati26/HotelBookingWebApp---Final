package com.narola.hotelbooking.Booking.dao;

import java.sql.Connection;
import java.util.List;
import com.narola.hotelbooking.Booking.model.Booking;
import com.narola.hotelbooking.Booking.model.BookingFilterParam;
import com.narola.hotelbooking.Exception.DatabaseException;
import com.narola.hotelbooking.Room.model.Room;

public interface IBookingDAO {
	List<Booking> searchBookingData(BookingFilterParam filterParam) throws DatabaseException;

	int createBooking(Booking book, List<Room> roomList) throws DatabaseException;

	int inserData(Booking book) throws DatabaseException;

	Booking viewBooking(int bookingId) throws DatabaseException;

	int inserData(Booking book, Connection connection) throws DatabaseException;

	void updateData(Booking booking) throws DatabaseException;

	List<Booking> showallbooking() throws DatabaseException;
}
