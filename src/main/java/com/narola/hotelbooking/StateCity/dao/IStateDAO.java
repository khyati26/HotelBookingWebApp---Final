package com.narola.hotelbooking.StateCity.dao;

import com.narola.hotelbooking.Exception.DatabaseException;
import com.narola.hotelbooking.StateCity.model.State;

import java.util.List;

public interface IStateDAO {
    List<State> getStates() throws DatabaseException;

}
