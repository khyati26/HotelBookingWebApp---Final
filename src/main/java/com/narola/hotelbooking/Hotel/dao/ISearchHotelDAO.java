package com.narola.hotelbooking.Hotel.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import com.narola.hotelbooking.Exception.DatabaseException;
import com.narola.hotelbooking.Hotel.model.Hotel;
import com.narola.hotelbooking.Hotel.model.SearchHotel;
import com.narola.hotelbooking.Hotel.model.SearchHotelCriteria;
import com.narola.hotelbooking.Room.model.Room;

public interface ISearchHotelDAO {
	Set<Room> getAvailableRooms(SearchHotelCriteria searchHotel, int hotelid) throws DatabaseException, SQLException;

	Set<Room> getAvailableRooms(SearchHotel searchHotel, int hotelid) throws DatabaseException, SQLException;

	List<Hotel> getAllHotelsInCity(int cityid) throws DatabaseException;

	List<Hotel> getCityWiseHotels(SearchHotelCriteria searchHotelCriteria) throws DatabaseException;
}
