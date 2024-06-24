package com.narola.hotelbooking.StateCity.service;

import com.narola.hotelbooking.Exception.ApplicationException;
import com.narola.hotelbooking.StateCity.model.State;

import java.util.List;

public interface IStateService {
    List<State> getAllState() throws ApplicationException;
}
