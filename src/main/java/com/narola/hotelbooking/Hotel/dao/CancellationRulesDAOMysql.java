package com.narola.hotelbooking.Hotel.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;

import com.narola.hotelbooking.Exception.DatabaseException;
import com.narola.hotelbooking.Hotel.model.CancellationRules;
import com.narola.hotelbooking.dao.ConnectDB;

public class CancellationRulesDAOMysql implements ICancellationRulesDAO{

	public  int inserData(CancellationRules cancellationrule) throws DatabaseException {
		PreparedStatement ps = null;
		try {
			String query = "INSERT INTO " + "cancelletionrules (hotelid,hours,refund) " + "VALUES (?,?,?)";
			ps = ConnectDB.getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, cancellationrule.getHotelid());
			ps.setInt(2, cancellationrule.getHours());
			ps.setInt(3, cancellationrule.getRefundpercentsge());
			ps.executeUpdate();
			ResultSet generatedid = ps.getGeneratedKeys();
			if (generatedid.next()) {
				return generatedid.getInt(1);
			}
		} catch (DatabaseException e) {
			throw e;
		} catch (SQLException e) {
			throw new DatabaseException("Exception while inserting cancellationRule : " , e);
		} finally {
			ConnectDB.closeResource(ps);
		}
		return 0;
	
	}

	public  void updateData(CancellationRules cancellationrule) throws DatabaseException {
		PreparedStatement ps = null;
		String query = "update cancelletionrules set hotelid=?,hours=?,refund=? " + "where id = ?";
		try {
			ps = ConnectDB.getConnection().prepareStatement(query);
			ps.setInt(1, cancellationrule.getHotelid());
			ps.setInt(2, cancellationrule.getHours());
			ps.setInt(3, cancellationrule.getRefundpercentsge());
			ps.setInt(4, cancellationrule.getId());
			boolean b = ps.executeUpdate() > 0;
			if (!b) {
				inserData(cancellationrule) ;
			}

		}catch (DatabaseException e) {
			throw e;
		} catch (SQLException e) {
			
			throw new DatabaseException("Exception while updating cancellationRule : " , e);
		} finally {
			ConnectDB.closeResource(ps);
		}
	}

	public  List<CancellationRules> getCancellationRulesByHotel(int hotelId) throws DatabaseException {
		List<CancellationRules> rulesList = new ArrayList<>();
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			ps = ConnectDB.getConnection().prepareStatement("SELECT * FROM cancelletionrules where hotelid = ?");
			ps.setInt(1, hotelId);
			 rs = ps.executeQuery();
			while (rs.next()) {
				CancellationRules cancellationrule = new CancellationRules();
				cancellationrule.setId(rs.getInt(1));
				cancellationrule.setHotelid(rs.getInt(2));
				cancellationrule.setHours(rs.getInt(3));
				cancellationrule.setRefundpercentsge(rs.getInt(4));
				rulesList.add(cancellationrule);
			}
			return rulesList;
		}catch (DatabaseException e) {
			throw e;
		} catch (SQLException e) {		
			throw new DatabaseException("Exception while getting cancellationRules hotel wise : " , e);
		} finally {
			ConnectDB.closeResource(ps,rs);
		}
	}

	public  boolean deleteData(int cancellationId) throws DatabaseException {
		PreparedStatement ps=null;
		try {
			ps = ConnectDB.getConnection().prepareStatement("delete from  cancelletionrules where id = ? ");
			ps.setInt(1, cancellationId);
			 ps.executeUpdate() ;
			return true;
		}catch (DatabaseException e) {
			throw e;
		} catch (SQLException e) {			
			throw new DatabaseException("Exception while deleting cancellationRule  : " , e);
		} finally {
			ConnectDB.closeResource(ps);
		}
	}

	public  boolean deleteHotelWiseData(int hotelid) throws DatabaseException {
		PreparedStatement ps=null;
		try {
			ps = ConnectDB.getConnection()
					.prepareStatement("delete from  cancelletionrules where hotelid = ? ");
			ps.setInt(1, hotelid);
			ps.executeUpdate() ;
			return true;
		}catch (DatabaseException e) {
			throw e;
		} catch (SQLException e) {			
			throw new DatabaseException("Exception while deleting cancellationRules hotel wise : " , e);
		} finally {
			ConnectDB.closeResource(ps);
		}
		
	}

}
