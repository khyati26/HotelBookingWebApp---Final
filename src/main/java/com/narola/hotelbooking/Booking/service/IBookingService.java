package com.narola.hotelbooking.Booking.service;

import java.util.List;

import com.narola.hotelbooking.Booking.model.Booking;
import com.narola.hotelbooking.Booking.model.BookingFilterParam;
import com.narola.hotelbooking.Exception.ApplicationException;
import com.narola.hotelbooking.Hotel.model.Hotel;
import com.narola.hotelbooking.Room.model.Room;

public interface IBookingService {
	int addBooking(Booking booking, List<Room> roomList) throws ApplicationException;

	List<Booking> getAllBooking() throws ApplicationException;

	List<Booking> searchBooking(BookingFilterParam filterParam) throws ApplicationException;

	List<Room>  setBookingDetails(String roomid) throws ApplicationException;
}
