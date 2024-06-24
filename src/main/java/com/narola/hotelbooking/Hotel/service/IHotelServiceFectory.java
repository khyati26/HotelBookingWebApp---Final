package com.narola.hotelbooking.Hotel.service;

import com.narola.hotelbooking.Hotel.service.Impl.ICancellationRulesServiceImpl;
import com.narola.hotelbooking.Hotel.service.Impl.IHotelServiceImpl;
import com.narola.hotelbooking.Hotel.service.Impl.ISearchHotelServiceImpl;

public class IHotelServiceFectory {
	public static IHotelServiceFectory hotelServiceFectory = null;
	public IHotelService hotelService = null;
	public ISearchHotelService searchHotelService = null;
	public ICancellationRulesService cancellationService = null;

	public static IHotelServiceFectory getInstance() {
		if (hotelServiceFectory == null) {
			hotelServiceFectory = new IHotelServiceFectory();
		}
		return hotelServiceFectory;
	}

	public IHotelService getHotelService() {
		return hotelService = new IHotelServiceImpl();
	}
	
	public ISearchHotelService getSearchHotelService() {
		return searchHotelService = new ISearchHotelServiceImpl();
	}
	public ICancellationRulesService getCancellationService() {
		return cancellationService = new ICancellationRulesServiceImpl();
	}
}
