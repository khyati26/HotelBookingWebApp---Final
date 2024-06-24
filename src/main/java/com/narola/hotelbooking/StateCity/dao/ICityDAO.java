package com.narola.hotelbooking.StateCity.dao;

import com.narola.hotelbooking.Exception.DatabaseException;
import com.narola.hotelbooking.StateCity.model.City;

import java.util.List;

public interface ICityDAO {
    City viewCity(int id) throws DatabaseException;
    void updateCityData(City city) throws DatabaseException;
    boolean deleteCity(int id) throws DatabaseException;
    int addcity(City city) throws DatabaseException;
    boolean checkPopularCity(int cityid) throws DatabaseException;
    boolean uncheckPopularCity(int cityid);
    List<City> getData() throws DatabaseException;
    List<City> getStateWiseData(int id) throws DatabaseException;
    List<City> getPopularCities();
}
