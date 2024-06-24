package com.narola.hotelbooking.Booking.dao;

import java.sql.Connection;
import java.util.List;

import com.narola.hotelbooking.Exception.DatabaseException;
import com.narola.hotelbooking.Room.model.Room;

public interface IBookingRoomDAO {
	int inserData(int bookingid, int roomid, int roomqty) throws DatabaseException;

	int inserData(int bookingid, int roomid, int roomqty, Connection connection) throws DatabaseException;

	List<Room> getBookedRooms(int bookingid)throws DatabaseException;

}
