package com.narola.hotelbooking.Hotel.dao;

import java.util.List;

import com.narola.hotelbooking.Exception.DatabaseException;
import com.narola.hotelbooking.Hotel.model.Hotel;

public interface IHotelDAO {
	int inserData(Hotel hotel) throws DatabaseException;

	void updateData(Hotel hotel) throws DatabaseException;

	void deleteHotel(int hotelId) throws DatabaseException;

	Hotel viewHotel(int hotelId) throws DatabaseException;

	List<Hotel> showData() throws DatabaseException;

	List<Hotel> getHotelNameandId() throws DatabaseException;

}
