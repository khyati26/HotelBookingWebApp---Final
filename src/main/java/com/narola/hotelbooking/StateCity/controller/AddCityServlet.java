package com.narola.hotelbooking.StateCity.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.narola.hotelbooking.StateCity.dao.ICityDAO;
import com.narola.hotelbooking.StateCity.dao.IStateDAO;
import com.narola.hotelbooking.StateCity.model.City;
import com.narola.hotelbooking.StateCity.dao.CityDAO;
import com.narola.hotelbooking.StateCity.dao.StateDAO;
import com.narola.hotelbooking.StateCity.service.CityServiceFectory;
import com.narola.hotelbooking.StateCity.service.ICityService;
import com.narola.hotelbooking.StateCity.service.IStateService;
import com.narola.hotelbooking.StateCity.service.StateServiceFectory;
import com.narola.hotelbooking.Utility.AdminURLConstant;
import com.narola.hotelbooking.Utility.Constant;
import com.narola.hotelbooking.dao.DAOFactory;

/**
 * Servlet implementation class AddCityServlet
 */
public class AddCityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		IStateService stateService = StateServiceFectory.getInstance().getStateService();

		request.setAttribute("states",stateService.getAllState());
		RequestDispatcher rd = request.getRequestDispatcher("admin/statecity/AddCity.jsp");
		rd.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		ICityDAO cityDAO = DAOFactory.getDaoFactory().getCityDAO();
		ICityService cityService = CityServiceFectory.getInstance().getcityService();

		City city=new City();
		city.setCityName(request.getParameter("cityname"));
		city.setPopular(Integer.parseInt(request.getParameter("popular")));
		city.setStateId(Integer.parseInt(request.getParameter("stateid")));
		
//		String filePath =  request.getServletContext().getRealPath("/") + Constant.CITY_FOLDER_PARENTPATH ;
//		String submitedfilename = request.getPart("cityimage").getSubmittedFileName().replaceAll("\\s+", "").trim();
//		String	mainfilename = city.getCityName() + submitedfilename.substring(submitedfilename.lastIndexOf("."));
//		SaveimageInFolder(request.getPart("cityimage"),filePath + mainfilename );
//		city.setImage(mainfilename);
//		city.setId(cityDAO.addcity(city));
		int cityId = cityService.addCity(city,request);
		response.sendRedirect(request.getContextPath() + AdminURLConstant.DISPLAYALL_CITIES_URL);
	}
	
//	void SaveimageInFolder(Part imagepart, String imagefullpath) throws IOException {
//		File newFile = new File(imagefullpath);
//		InputStream inputStream = imagepart.getInputStream();
//		final byte[] imgAsBytes = new byte[1024];
//		FileOutputStream fos = new FileOutputStream(newFile);
//		int read = 0;
//		while ((read = inputStream.read(imgAsBytes)) != -1) {
//			fos.write(imgAsBytes, 0, read);
//		}
//		fos.flush();
//		fos.close();
//		inputStream.close();
//	}
}
