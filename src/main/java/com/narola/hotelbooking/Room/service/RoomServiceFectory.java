package com.narola.hotelbooking.Room.service;

import com.narola.hotelbooking.Room.service.Impl.RoomServiceImpl;

public class RoomServiceFectory {
	public static RoomServiceFectory roomServiceFectory = null;
	public IRoomService roomService = null;
	
	public static RoomServiceFectory getInstance() {
		if (roomServiceFectory == null) {
			roomServiceFectory = new RoomServiceFectory();
		}
		return roomServiceFectory;
	}

	public IRoomService getRoomService() {
		return roomService = new RoomServiceImpl();
	}
	
}
