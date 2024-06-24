package com.narola.hotelbooking.Hotel.service.Impl;

import com.narola.hotelbooking.Exception.ApplicationException;
import com.narola.hotelbooking.Exception.DatabaseException;
import com.narola.hotelbooking.Hotel.dao.ICancellationRulesDAO;
import com.narola.hotelbooking.Hotel.model.CancellationRules;
import com.narola.hotelbooking.Hotel.service.ICancellationRulesService;
import com.narola.hotelbooking.dao.DAOFactory;

public class ICancellationRulesServiceImpl implements ICancellationRulesService {

	@Override
	public int addCancellationRule(CancellationRules cancellationRules) throws ApplicationException {
		try {
			ICancellationRulesDAO cancellationRulesDAO = DAOFactory.getDaoFactory().getCancellationRulesDAO();
			return cancellationRulesDAO.inserData(cancellationRules);

		} catch (DatabaseException e) {
			throw new ApplicationException("Opps,Something went wrong.", e);
		} catch (Exception e) {
			throw new ApplicationException("Opps,Something went wrong.", e);
		}
	}

	@Override
	public void editCancellationRule(CancellationRules cancellationRules) throws ApplicationException {
		try {
			ICancellationRulesDAO cancellationRulesDAO = DAOFactory.getDaoFactory().getCancellationRulesDAO();
			cancellationRulesDAO.updateData(cancellationRules);

		} catch (DatabaseException e) {
			throw new ApplicationException("Opps,Something went wrong.", e);
		} catch (Exception e) {
			throw new ApplicationException("Opps,Something went wrong.", e);
		}
	}

	@Override
	public void deleteCancellationRule(int cancellationRuleId) throws ApplicationException {
		try {
			ICancellationRulesDAO cancellationRulesDAO = DAOFactory.getDaoFactory().getCancellationRulesDAO();
			cancellationRulesDAO.deleteData(cancellationRuleId);

		} catch (DatabaseException e) {
			throw new ApplicationException("Opps,Something went wrong.", e);
		} catch (Exception e) {
			throw new ApplicationException("Opps,Something went wrong.", e);
		}
	}
}
