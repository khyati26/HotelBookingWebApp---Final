package com.narola.hotelbooking.Hotel.dao;

import java.util.List;

import com.narola.hotelbooking.Exception.DatabaseException;
import com.narola.hotelbooking.Hotel.model.CancellationRules;

public interface ICancellationRulesDAO {
	int inserData(CancellationRules cancellationrule) throws DatabaseException;

	void updateData(CancellationRules cancellationrule) throws DatabaseException;

	List<CancellationRules> getCancellationRulesByHotel(int hotelId) throws DatabaseException;

	boolean deleteData(int cancellationId) throws DatabaseException;

	boolean deleteHotelWiseData(int hotelid) throws DatabaseException;

}
