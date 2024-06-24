package com.narola.hotelbooking.Hotel.service.Impl;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import com.narola.hotelbooking.Exception.ApplicationException;
import com.narola.hotelbooking.Exception.DatabaseException;
import com.narola.hotelbooking.Hotel.dao.ISearchHotelDAO;
import com.narola.hotelbooking.Hotel.model.Hotel;
import com.narola.hotelbooking.Hotel.model.SearchHotel;
import com.narola.hotelbooking.Hotel.model.SearchHotelCriteria;
import com.narola.hotelbooking.Hotel.service.ISearchHotelService;
import com.narola.hotelbooking.dao.DAOFactory;

public class ISearchHotelServiceImpl implements ISearchHotelService {

	@Override
	public List<Hotel> getCityWiseHotel(SearchHotelCriteria searchHotelCriteria) throws ApplicationException {
		try {
			ISearchHotelDAO searchHotelDAO = DAOFactory.getDaoFactory().getSearchHotelDAO();
			return searchHotelDAO.getCityWiseHotels(searchHotelCriteria);
		} catch (DatabaseException e) {
			throw new ApplicationException("Opps,Something went wrong.", e);
		} catch (Exception e) {
			throw new ApplicationException("Opps,Something went wrong.",e);
		}

	}

	@Override
	public int calculateDaysParam(String checkin , String checkout) throws ApplicationException {
		try {
			LocalDate startdate = LocalDate.parse(checkin);
			LocalDate enddate = LocalDate.parse(checkout);
			return (int) ChronoUnit.DAYS.between(startdate, enddate);
		} catch (Exception e) {
			throw new ApplicationException("Opps,Something went wrong",e);
		}
	}

}
