package com.narola.hotelbooking.StateCity.service;

import com.narola.hotelbooking.StateCity.service.Impl.CityServiceImpl;

public class CityServiceFectory {
    public static CityServiceFectory cityServiceFectory = null;
    public ICityService cityService = null;

    public static CityServiceFectory getInstance() {
        if (cityServiceFectory == null) {
            cityServiceFectory = new CityServiceFectory();
        }
        return cityServiceFectory;
    }

    public ICityService getcityService() {
        return cityService = new CityServiceImpl();
    }
}
