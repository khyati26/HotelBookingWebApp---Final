package com.narola.hotelbooking.Utility;

import com.narola.hotelbooking.Booking.service.IBookingService;
import com.narola.hotelbooking.Booking.serviceImpl.IBookingServiceImpl;
import com.narola.hotelbooking.Hotel.service.ICancellationRulesService;
import com.narola.hotelbooking.Hotel.service.IHotelService;
import com.narola.hotelbooking.Hotel.service.ISearchHotelService;
import com.narola.hotelbooking.Hotel.service.Impl.ICancellationRulesServiceImpl;
import com.narola.hotelbooking.Hotel.service.Impl.IHotelServiceImpl;
import com.narola.hotelbooking.Hotel.service.Impl.ISearchHotelServiceImpl;
import com.narola.hotelbooking.Room.service.IRoomService;
import com.narola.hotelbooking.Room.service.Impl.RoomServiceImpl;

public class ServiceFectory {

	public static ServiceFectory serviceFectory = null;
	public IRoomService roomService = null;
	public IHotelService hotelService = null;
	public ISearchHotelService searchHotelService = null;
	public ICancellationRulesService cancellationService = null;
	public IBookingService bookingService = null;

	public static ServiceFectory getInstance() {
		if (serviceFectory == null) {
			serviceFectory = new ServiceFectory();
		}
		return serviceFectory;
	}

	public IRoomService getRoomService() {
		if (roomService == null) {
			roomService = new RoomServiceImpl();
		}
		return roomService;
	}

	public IHotelService getHotelService() {
		if (hotelService == null) {
			hotelService = new IHotelServiceImpl();
		}
		return hotelService;
	}

	public ISearchHotelService getSearchHotelService() {
		if (searchHotelService == null) {
			searchHotelService = new ISearchHotelServiceImpl();
		}
		return searchHotelService;
	}

	public ICancellationRulesService getCancellationService() {
		if (cancellationService == null) {
			cancellationService = new ICancellationRulesServiceImpl();
		}
		return cancellationService;
	}

	public IBookingService getBookingService() {
		if (bookingService == null) {
			bookingService = new IBookingServiceImpl();
		}
		return bookingService;
	}
}
