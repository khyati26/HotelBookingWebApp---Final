package com.narola.hotelbooking.StateCity.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.narola.hotelbooking.Exception.DatabaseException;
import com.narola.hotelbooking.StateCity.model.State;
import com.narola.hotelbooking.dao.ConnectDB;

public class StateDAO implements IStateDAO {
	@Override
	public  List<State> getStates() throws DatabaseException{
			Statement st=null;
			ResultSet rs=null;
			List<State> stateList = new ArrayList<>();
			try {
				st=ConnectDB.getConnection().createStatement();
				rs=st.executeQuery("SELECT * FROM state");
				while (rs.next()) {
					State state = new State();
					state.setId(rs.getInt(1));
					state.setStateName(rs.getString(2));
					stateList.add(state);
				}

				return stateList;
			} catch (SQLException e) {
				throw new DatabaseException("Exception while getting states : "+e.getMessage(),e);
			}finally {
				ConnectDB.closeResource(st,rs);
			}
	}
}
