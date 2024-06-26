package com.narola.hotelbooking.Utility;

import org.springframework.context.annotation.Bean;


public class AdminURLConstant {

	//		state-city URL
	public final static String DISPLAYALL_CITIES_URL = "/cities";
	public final static String POPULAR_CITIES_URL = "/popularcities";
	public final static String ADD_CITYPAGE_URL = "/addcity";
	public final static String EDIT_CITYPAGE_URL = "/editcitypage";
	//		Hotel's URL
	public final static String DISPLAYALL_HOTELS_URL = "/displayallhotels";
	public final static String VIEW_HOTELPAGE_URL = "/viewhotel";
	public static String DELETE_CITY_URL = "/deletecity";
	public static String ADD_HOTELPAGE_URL = "/addhotelpage";
	public static String ADD_HOTELROOM_URL = "/addhotelandroom";
	public static String DELETE_HOTEL_URL = "/deletehotel";
	public static String EDIT_HOTELPAGE_URL = "/edithotelpage";
	//		Room's URL
	public static String DISPLAYALL_ROOMS_URL = "/displayallrooms";
	public static String ADD_ROOMPAGE_URL = "/addroompage";
	public static String VIEW_ROOMPAGE_URL = "/viewroom";
	public static String DELETE_ROOM_URL = "/deleteroom";
	public static String EDIT_ROOMPAGE_URL = "/editroompage";

	public static String ADMIN_LOGIN_URL = "/adminlogin";

	public static String DISPLAYALL_BOOKING_URL = "/listofbookings";

}
