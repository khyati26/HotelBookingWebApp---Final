package com.narola.hotelbooking.Hotel.service;

import com.narola.hotelbooking.Exception.ApplicationException;
import com.narola.hotelbooking.Hotel.model.CancellationRules;

public interface ICancellationRulesService {
	int addCancellationRule(CancellationRules cancellationRules) throws ApplicationException;

	void editCancellationRule(CancellationRules cancellationRules) throws ApplicationException;

	void deleteCancellationRule(int cancellationRuleId) throws ApplicationException;
	
	
}
