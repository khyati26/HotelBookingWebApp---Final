package com.narola.hotelbooking.StateCity.service;

import com.narola.hotelbooking.Exception.ApplicationException;
import com.narola.hotelbooking.JPA.CityEntity;
import com.narola.hotelbooking.StateCity.model.City;
import com.narola.hotelbooking.StateCity.model.State;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface ICityService {
    int addCity(City city, HttpServletRequest request) throws ApplicationException;

    List<CityEntity> getAllCity() throws ApplicationException;

    void uncheckPopularCity(int cityID) throws ApplicationException;

    void uncheckPopularCity(String uncheckedids) throws ApplicationException;

    void checkPopularCity(int cityId) throws ApplicationException;

    void checkPopularCity(String checkedids) throws ApplicationException;

    List<City> getStateWiseCities(int stateId) throws ApplicationException;

    City getCity(int cityId) throws ApplicationException;

    void updateCity(City city, HttpServletRequest request) throws ApplicationException;
}
