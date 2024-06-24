package com.narola.hotelbooking.StateCity.service.Impl;

import com.narola.hotelbooking.Exception.ApplicationException;
import com.narola.hotelbooking.StateCity.dao.IStateDAO;
import com.narola.hotelbooking.StateCity.model.State;
import com.narola.hotelbooking.StateCity.service.IStateService;
import com.narola.hotelbooking.dao.DAOFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StateServiceImpl implements IStateService {

    @Autowired
    private IStateDAO stateDAO;

    @Override
    public List<State> getAllState() throws ApplicationException {
//        IStateDAO stateDAO = DAOFactory.getDaoFactory().getStateDAO();
        return stateDAO.getStates();
    }
}
