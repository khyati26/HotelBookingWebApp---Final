package com.narola.hotelbooking.Room.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.narola.hotelbooking.Exception.DatabaseException;
import com.narola.hotelbooking.Hotel.dao.IHotelDAO;
import com.narola.hotelbooking.Hotel.model.Hotel;
import com.narola.hotelbooking.Room.model.Room;
import com.narola.hotelbooking.dao.ConnectDB;
import com.narola.hotelbooking.dao.DAOFactory;

public class RoomDAOPostgres implements IRoomDAO {

	@Override
	public int inserData(Room room) throws DatabaseException {
		PreparedStatement ps = null;
		try {
			String query = "INSERT INTO "
					+ "roomtype (name,hotelid,price,qty,inclusions,image,maxcapacity,description) "
					+ "VALUES (?,?,?,?,?,?,?,?)";
			ps = ConnectDB.getInstance().getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, room.getName());
			ps.setInt(2, room.getHotelID());
			ps.setDouble(3, room.getPrice());
			ps.setInt(4, room.getQty());
			ps.setString(5, room.getInclusions());
			ps.setString(6, room.getImage());
			ps.setInt(7, room.getMaxcapacity());
			ps.setString(8, room.getDescription());
			ps.executeUpdate();
			ResultSet generatedid = ps.getGeneratedKeys();
			if (generatedid.next()) {
				return generatedid.getInt(1);
			}
		} catch (DatabaseException e) {
			throw e;
		} catch (SQLException e) {
			throw new DatabaseException("Exception while inserting room details : " + e.getMessage(), e);
		} finally {
			ConnectDB.closeResource(ps);
		}
		return 0;
	}

	@Override
	public List<Room> showHotelWiseRoom(int hotelid) throws DatabaseException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Room> roomList = new ArrayList<>();
		IHotelDAO HotelDAO = DAOFactory.getDaoFactory().getHotelDAO();
		try {
			ps = ConnectDB.getInstance().getConnection().prepareStatement(
					"SELECT r.*,h.name FROM roomtype r,hotels h where r.hotelid=h.id and r.hotelid = ?");
			ps.setInt(1, hotelid);
			rs = ps.executeQuery();
			while (rs.next()) {
				Room room = new Room();
				Hotel hotel = HotelDAO.viewHotel(rs.getInt(3));
				hotel.setName(rs.getString(11));
				room.setId(rs.getInt(1));
				room.setName(rs.getString(2));
				room.setHotelID(rs.getInt(3));
				room.setPrice(rs.getDouble(4));
				room.setQty(rs.getInt(5));
				room.setInclusions(rs.getString(6));
				room.setImage(rs.getString(7));
				room.setMaxcapacity(rs.getInt(8));
				room.setDescription(rs.getString(9));
				room.setCreatedon(rs.getString(10));
				room.setUpdatedon(rs.getString(11));
				room.setHotel(hotel);
				roomList.add(room);
			}
			return roomList;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DatabaseException("Exception while getting hotel wise rooms : " + e.getMessage(), e);
		} finally {
			ConnectDB.closeResource(ps, rs);
		}
	}

	@Override
	public List<Room> showData() throws DatabaseException {
		List<Room> roomList = new ArrayList();
		Statement s = null;
		ResultSet rs = null;
		try {

			s = ConnectDB.getInstance().getConnection().createStatement();
			rs = s.executeQuery("SELECT r.*,h.name FROM roomtype r,hotels h where r.hotelid=h.id");

			while (rs.next()) {
				Room room = new Room();
				Hotel hotel = new Hotel();
				hotel.setName(rs.getString(12));
//				System.out.println(rs.getString(2));
				room.setId(rs.getInt(1));
				room.setName(rs.getString(2));
				room.setHotelID(rs.getInt(3));
				room.setPrice(rs.getDouble(4));
				room.setQty(rs.getInt(5));
				room.setInclusions(rs.getString(6));
				room.setImage(rs.getString(7));
				room.setMaxcapacity(rs.getInt(8));
				room.setDescription(rs.getString(9));
				room.setCreatedon(rs.getString(10));
				room.setUpdatedon(rs.getString(11));
				room.setHotel(hotel);
				roomList.add(room);
			}

			return roomList;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new DatabaseException("Exception while getting all rooms : " + e.getMessage(), e);
		} finally {
			ConnectDB.closeResource(s, rs);
		}
	}

	@Override
	public boolean deleteRoom(int roomid) throws DatabaseException {
		PreparedStatement ps = null;
		try {
			ps = ConnectDB.getInstance().getConnection().prepareStatement("delete from roomtype where id = ? ");
			ps.setInt(1, roomid);
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DatabaseException("Exception while deleting  room : " + e.getMessage(), e);
		} finally {
			ConnectDB.closeResource(ps);
		}
	}

	public Room viewRoom(int roomid) throws DatabaseException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = ConnectDB.getInstance().getConnection().prepareStatement("select * from roomtype where id = ?");
			ps.setInt(1, roomid);
			rs = ps.executeQuery();
			Room room = null;
			if (rs.next()) {
				room = new Room();
				room.setId(rs.getInt(1));
				room.setName(rs.getString(2));
				room.setHotelID(rs.getInt(3));
				room.setPrice(rs.getDouble(4));
				room.setQty(rs.getInt(5));
				room.setInclusions(rs.getString(6));
				room.setImage(rs.getString(7));
				room.setMaxcapacity(rs.getInt(8));
				room.setDescription(rs.getString(9));
				room.setCreatedon(rs.getString(10));
				room.setUpdatedon(rs.getString(11));
			}
			return room;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DatabaseException("Exception while getting single room : " + e.getMessage(), e);
		} finally {
			ConnectDB.closeResource(ps, rs);
		}

	}

	@Override
	public void updateData(Room room) throws DatabaseException {

		String query = "update roomtype set "
				+ "name = ?,hotelid = ?,price = ?,qty = ?,inclusions = ?,maxcapacity = ?,description = ?,image=? "
				+ "where id = ?";
		PreparedStatement ps = null;
		try {
			ps = ConnectDB.getInstance().getConnection().prepareStatement(query);
			ps.setString(1, room.getName());
			ps.setInt(2, room.getHotelID());
			ps.setDouble(3, room.getPrice());
			ps.setInt(4, room.getQty());
			ps.setString(5, room.getInclusions());
			ps.setInt(6, room.getMaxcapacity());
			ps.setString(7, room.getDescription());
			ps.setString(8, room.getImage());
			ps.setInt(9, room.getId());
			ps.executeUpdate();
		} catch (DatabaseException e) {
			throw e;
		} catch (SQLException e) {
			throw new DatabaseException("Exception while updatingting room details: " + e.getMessage(), e);
		} finally {
			ConnectDB.closeResource(ps);
		}
	}

}
