package com.narola.hotelbooking.Hotel.service;

import java.util.List;

import com.narola.hotelbooking.Exception.ApplicationException;
import com.narola.hotelbooking.Hotel.model.Hotel;
import com.narola.hotelbooking.Hotel.model.SearchHotel;
import com.narola.hotelbooking.Hotel.model.SearchHotelCriteria;

public interface ISearchHotelService {
	List<Hotel> getCityWiseHotel(SearchHotelCriteria searchHotelCriteria) throws ApplicationException;

	int calculateDaysParam(String checkin, String checkout) throws ApplicationException;
}
