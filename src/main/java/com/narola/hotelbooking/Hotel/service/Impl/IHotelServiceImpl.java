package com.narola.hotelbooking.Hotel.service.Impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import com.narola.hotelbooking.Exception.ApplicationException;
import com.narola.hotelbooking.Exception.DatabaseException;
import com.narola.hotelbooking.Hotel.dao.ICancellationRulesDAO;
import com.narola.hotelbooking.Hotel.dao.IHotelDAO;
import com.narola.hotelbooking.Hotel.model.Hotel;
import com.narola.hotelbooking.Hotel.service.IHotelService;
import com.narola.hotelbooking.Room.model.Room;
import com.narola.hotelbooking.Utility.Constant;
import com.narola.hotelbooking.dao.DAOFactory;
import org.springframework.stereotype.Service;

@Service
public class IHotelServiceImpl implements IHotelService {

	IHotelDAO hotelDAO = DAOFactory.getDaoFactory().getHotelDAO();

	@Override
	public int addHotel(Hotel hotel) throws ApplicationException {
		try {
			return hotelDAO.inserData(hotel);
		} catch (DatabaseException e) {
			throw new ApplicationException("Opps,Something went wrong.", e);
		} catch (Exception e) {
			throw new ApplicationException("Opps,Something went wrong.", e);
		}
	}

	@Override
	public void updateHotel(Hotel hotel) throws ApplicationException {
		try {
			hotelDAO.updateData(hotel);
		} catch (DatabaseException e) {
			throw new ApplicationException("Opps,Something went wrong.", e);
		} catch (Exception e) {
			throw new ApplicationException("Opps,Something went wrong.", e);
		}
	}


	@Override
	public Hotel addHotelRoomImages(Hotel hotel, Room room, HttpServletRequest request)
			throws ApplicationException, IOException, ServletException {
		try {
			String hotelDefaultImage = null, roomDefaultImage = null;
			String hotelfilePath = request.getServletContext().getRealPath("/") + Constant.HOTEL_FOLDER_PARENTPATH + hotel.getId();

			String roomfilePath = request.getServletContext().getRealPath("/") + Constant.ROOM_FOLDER_PARENTPATH + room.getId();

			File folder = new File(hotelfilePath);
			if (!folder.exists()) {
				folder.mkdirs();
			}

			File folderroom = new File(roomfilePath);
			if (!folderroom.exists()) {
				folderroom.mkdirs();
			}
			for (Part filePart : request.getParts()) {
				if (filePart.getName().equalsIgnoreCase("photos") && filePart.getSize() > 0) {
					String submitedfilename = filePart.getSubmittedFileName().replaceAll("\\s+", "").trim();
					SaveimageInFolder(filePart, hotelfilePath + "\\" + submitedfilename);
				} else if (filePart.getName().equalsIgnoreCase("roomphotos")) {
					String submitedfilename = filePart.getSubmittedFileName().replaceAll("\\s+", "").trim();
					SaveimageInFolder(filePart, roomfilePath + "\\" + submitedfilename);
				} else if (filePart.getName().equalsIgnoreCase("mainphoto")) {
					String submitedfilename = filePart.getSubmittedFileName().replaceAll("\\s+", "").trim();
					hotelDefaultImage = "main" + hotel.getId()
							+ submitedfilename.substring(submitedfilename.lastIndexOf("."));
					SaveimageInFolder(filePart, hotelfilePath + "\\" + hotelDefaultImage);
				} else if (filePart.getName().equalsIgnoreCase("roommainphoto") && filePart.getSize() > 0) {
					String submitedfilename = filePart.getSubmittedFileName().replaceAll("\\s+", "").trim();
					roomDefaultImage = "main" + room.getId()
							+ submitedfilename.substring(submitedfilename.lastIndexOf("."));
					SaveimageInFolder(filePart, roomfilePath + "\\" + roomDefaultImage);
				}
			}
			room.setImage(roomDefaultImage);
			List<Room> rooms = new ArrayList<>();
			rooms.add(room);
			hotel.setImage(hotelDefaultImage);
			hotel.setRooms(rooms);
			return hotel;
		} catch (DatabaseException e) {
			throw new ApplicationException("Opps,Something went wrong.", e);
		} catch (Exception e) {
			throw new ApplicationException("Opps,Something went wrong.", e);
		}
	}

	void SaveimageInFolder(Part imagepart, String imagefullpath) throws IOException {
		File newFile = new File(imagefullpath);
		InputStream inputStream = imagepart.getInputStream();
		final byte[] imgAsBytes = new byte[1024];
		FileOutputStream fos = new FileOutputStream(newFile);
		int read = 0;
		while ((read = inputStream.read(imgAsBytes)) != -1) {
			fos.write(imgAsBytes, 0, read);
		}

		fos.flush();
		fos.close();
	}

	@Override
	public List<Hotel> getAllHotel() throws ApplicationException {

		try {
			return hotelDAO.showData();
		} catch (DatabaseException e) {
			throw new ApplicationException("Opps,Something went wrong.", e);
		} catch (Exception e) {
			throw new ApplicationException("Opps,Something went wrong.", e);
		}
	}

	@Override
	public Hotel getHotel(int HotelID) throws ApplicationException {
		try {
			ICancellationRulesDAO cancellationRulesDAO = DAOFactory.getDaoFactory().getCancellationRulesDAO();
			Hotel hotel = hotelDAO.viewHotel(HotelID);
			hotel.setCancellationRulesList(cancellationRulesDAO.getCancellationRulesByHotel(HotelID));
			return hotel;
		} catch (DatabaseException e) {
			throw new ApplicationException("Opps,Something went wrong.", e);
		} catch (Exception e) {
			throw new ApplicationException("Opps,Something went wrong.", e);
		}
	}

	@Override
	public void deleteHotel(int HotelID) throws ApplicationException {
		try {
			hotelDAO.deleteHotel(HotelID);

		} catch (DatabaseException e) {
			throw new ApplicationException("Opps,Something went wrong.", e);
		} catch (Exception e) {
			throw new ApplicationException("Opps,Something went wrong.", e);
		}
	}

	@Override
	public Hotel getHotelForUpdate(int HotelId, HttpServletRequest request) throws ApplicationException {
		try {
			ICancellationRulesDAO cancellationRulesDAO = DAOFactory.getDaoFactory().getCancellationRulesDAO();
			Hotel hotel = hotelDAO.viewHotel(HotelId);
			hotel.setCancellationRulesList(cancellationRulesDAO.getCancellationRulesByHotel(HotelId));

			Collection<String> images = new ArrayList<String>();
//File imageDir = new File(
//		getServletContext().getRealPath("/") + ImagePathConstant.HOTEL_FOLDER_PARENTPATH + id);
			File imageDir = new File(request.getServletContext().getRealPath("/") + Constant.HOTEL_FOLDER_PARENTPATH + hotel.getId());
			if (!imageDir.exists()) {
				imageDir.mkdirs();
			}
			for (File imageFile : imageDir.listFiles()) {
				String imageFileName = imageFile.getName();
				if (!imageFileName.contains("main" + hotel.getId())) {
					images.add(imageFileName);
				}
			}
			hotel.setImages(images);
			return hotel;
		} catch (DatabaseException e) {
			throw new ApplicationException("Opps,Something went wrong.", e);
		} catch (Exception e) {
			throw new ApplicationException("Opps,Something went wrong.", e);
		}
	}

	@Override
	public void UpdateHotel(Hotel hotel, String deletedfiles, HttpServletRequest request) throws ApplicationException {
		try {
			String mainfilename = null;
			String submitedfilename = null;

			String filePath = request.getServletContext().getRealPath("/") + Constant.HOTEL_FOLDER_PARENTPATH + hotel.getId();

			for (String file : deletedfiles.split(",")) {
				if (!file.isEmpty()) {
					try {
						Files.deleteIfExists(Paths.get(filePath + "\\" + file));
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}

			File folder = new File(filePath);
			if (!folder.exists()) {
				folder.mkdirs();
			}

			for (Part filePart : request.getParts()) {
				if (filePart.getName().equalsIgnoreCase("photos") && !filePart.getSubmittedFileName().isEmpty()) {

					submitedfilename = filePart.getSubmittedFileName().replaceAll("\\s+", "").trim();
					SaveimageInFolder(filePart, filePath + "\\" + submitedfilename);
				}
				if (filePart.getName().equalsIgnoreCase("mainphoto")) {
					if (filePart.getSubmittedFileName().isEmpty()) {
						hotel.setImage(request.getParameter("hiddenmainphoto"));
					} else {
						submitedfilename = filePart.getSubmittedFileName().replaceAll("\\s+", "").trim();
						mainfilename = "main" + hotel.getId()
								+ submitedfilename.substring(submitedfilename.lastIndexOf("."));
						SaveimageInFolder(filePart, filePath + "\\" + mainfilename);
						hotel.setImage(mainfilename);
					}
				}
			}

			hotelDAO.updateData(hotel);

		} catch (DatabaseException e) {
			throw new ApplicationException("Opps,Something went wrong.", e);
		} catch (Exception e) {
			throw new ApplicationException("Opps,Something went wrong.", e);
		}
	}
}
