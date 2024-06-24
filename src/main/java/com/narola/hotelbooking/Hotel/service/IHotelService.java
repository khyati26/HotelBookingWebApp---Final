package com.narola.hotelbooking.Hotel.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.narola.hotelbooking.Exception.ApplicationException;
import com.narola.hotelbooking.Hotel.model.CancellationRules;
import com.narola.hotelbooking.Hotel.model.Hotel;
import com.narola.hotelbooking.Room.model.Room;

public interface IHotelService {
	int addHotel(Hotel hotel) throws ApplicationException;

	void updateHotel(Hotel hotel) throws ApplicationException;

	
	Hotel addHotelRoomImages(Hotel hotel, Room room,HttpServletRequest request)  throws ApplicationException, IOException, ServletException;

	List<Hotel> getAllHotel() throws ApplicationException;
	
	Hotel getHotel(int HotelID) throws ApplicationException;
	
	void deleteHotel(int HotelID) throws ApplicationException;
	
	Hotel getHotelForUpdate(int HotelId,HttpServletRequest request)throws ApplicationException;
	
	void UpdateHotel(Hotel hotel,String deletedfiles,HttpServletRequest request) throws ApplicationException;
}
