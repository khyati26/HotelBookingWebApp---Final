package com.narola.hotelbooking.StateCity.service;

import com.narola.hotelbooking.Room.service.IRoomService;
import com.narola.hotelbooking.Room.service.Impl.RoomServiceImpl;
import com.narola.hotelbooking.StateCity.service.Impl.StateServiceImpl;

public class StateServiceFectory {
	public static StateServiceFectory stateServiceFectory = null;
	public IStateService stateService = null;
	
	public static StateServiceFectory getInstance() {
		if (stateServiceFectory == null) {
			stateServiceFectory = new StateServiceFectory();
		}
		return stateServiceFectory;
	}

	public IStateService getStateService() {
		return stateService = new StateServiceImpl();
	}
}
