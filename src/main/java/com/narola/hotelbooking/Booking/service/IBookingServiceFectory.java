package com.narola.hotelbooking.Booking.service;

import com.narola.hotelbooking.Booking.serviceImpl.IBookingServiceImpl;

public class IBookingServiceFectory {

	public static IBookingServiceFectory bookingServiceFectory = null;
	public IBookingService bookingService = null;

	public static IBookingServiceFectory getInstance() {
		if (bookingServiceFectory == null) {
			bookingServiceFectory = new IBookingServiceFectory();
		}
		return bookingServiceFectory;
	}

	public IBookingService getBookingService() {
		return bookingService = new IBookingServiceImpl();
	}
}
