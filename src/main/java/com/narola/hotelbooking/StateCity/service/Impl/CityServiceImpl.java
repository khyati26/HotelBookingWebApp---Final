package com.narola.hotelbooking.StateCity.service.Impl;

import com.google.gson.Gson;
import com.narola.hotelbooking.Exception.ApplicationException;
import com.narola.hotelbooking.Exception.DatabaseException;
import com.narola.hotelbooking.JPA.CityEntity;
import com.narola.hotelbooking.JPA.Repository.CityRepository;
import com.narola.hotelbooking.StateCity.dao.ICityDAO;
import com.narola.hotelbooking.StateCity.model.City;
import com.narola.hotelbooking.StateCity.service.ICityService;
import com.narola.hotelbooking.Utility.Constant;
import com.narola.hotelbooking.dao.DAOFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class CityServiceImpl implements ICityService {

    @Autowired
    CityRepository cityRepository;
    //    @Autowired
//    private DAOFactory daoFactory;
    @Autowired
    private ICityDAO cityDAO;

    @Override
    public List<CityEntity> getAllCity() throws ApplicationException {
        try {
//            List<City> cityList = cityDAO.getData();
            List<CityEntity> cityList = new ArrayList<>();
            cityRepository.findAll().forEach(cityEntity -> cityList.add(cityEntity));
            return cityList;
        } catch (DatabaseException e) {
            throw new ApplicationException("Opps, something went wrong", e);
        }
    }

    @Override
    public int addCity(City city, HttpServletRequest request) throws ApplicationException {
        try {
//            ICityDAO cityDAO = daoFactory.getCityDAO();

            String filePath = request.getServletContext().getRealPath("/") + Constant.CITY_FOLDER_PARENTPATH;
//            String filePath = request.+"/images/cities";
            String submitedfilename = request.getPart("cityimage").getSubmittedFileName().replaceAll("\\s+", "").trim();
            String mainfilename = city.getCityName() + submitedfilename.substring(submitedfilename.lastIndexOf("."));
            SaveimageInFolder(request.getPart("cityimage"), filePath + mainfilename);
            city.setImage(mainfilename);

            return cityDAO.addcity(city);
        } catch (ServletException e) {
            throw new ApplicationException("Opps, something went wrong", e);
        } catch (IOException e) {
            throw new ApplicationException("Opps, something went wrong", e);
        }
    }


    @Override
    public void uncheckPopularCity(int cityId) throws ApplicationException {
        try {
//            daoFactory.getCityDAO().uncheckPopularCity(cityId);
            cityDAO.uncheckPopularCity(cityId);
        } catch (DatabaseException e) {
            throw new ApplicationException("Opps, something went wrong", e);
        }
    }

    @Override
    public void uncheckPopularCity(String uncheckedids) throws ApplicationException {
        try {
            ArrayList<String> myList2 = new ArrayList<String>(Arrays.asList(uncheckedids.split(",")));
            if (!myList2.isEmpty()) {
                for (String integer : myList2) {
//                    daoFactory.getCityDAO().uncheckPopularCity(Integer.parseInt(integer));
                    cityDAO.uncheckPopularCity(Integer.parseInt(integer));
                }
            }
        } catch (DatabaseException e) {
            throw new ApplicationException("Opps, something went wrong", e);
        }
    }

    @Override
    public void checkPopularCity(int cityId) throws ApplicationException {
        try {
//            daoFactory.getCityDAO().checkPopularCity(cityId);
            cityDAO.checkPopularCity(cityId);
        } catch (DatabaseException e) {
            throw new ApplicationException("Opps, something went wrong", e);
        }
    }

    @Override
    public void checkPopularCity(String checkedids) throws ApplicationException {
        try {
            ArrayList<String> myList2 = new ArrayList<String>(Arrays.asList(checkedids.split(",")));
            if (!myList2.isEmpty()) {
                for (String integer : myList2) {
//                    daoFactory.getCityDAO().checkPopularCity(Integer.parseInt(integer));
                    cityDAO.checkPopularCity(Integer.parseInt(integer));
                }
            }
        } catch (DatabaseException e) {
            throw new ApplicationException("Opps, something went wrong", e);
        }
    }

    @Override
    public List<City> getStateWiseCities(int stateId) throws ApplicationException {
        try {
//            return daoFactory.getCityDAO().getStateWiseData(stateId);
            return cityDAO.getStateWiseData(stateId);
        } catch (DatabaseException e) {
            throw new ApplicationException("Opps, something went wrong", e);
        } catch (Exception e) {
            throw new ApplicationException("Opps, something went wrong", e);
        }
    }

    @Override
    public City getCity(int cityId) throws ApplicationException {
        try {
//            return daoFactory.getCityDAO().viewCity(cityId);
            return cityDAO.viewCity(cityId);
        } catch (DatabaseException e) {
            throw new ApplicationException("Opps, something went wrong", e);
        }
    }

    @Override
    public void updateCity(City city, HttpServletRequest request) throws ApplicationException {
        try {
//            ICityDAO cityDAO = DAOFactory.getDaoFactory().getCityDAO();

            String filePath = request.getServletContext().getRealPath("/") + Constant.CITY_FOLDER_PARENTPATH;
            String submitedfilename = request.getPart("cityimage").getSubmittedFileName().replaceAll("\\s+", "").trim();
            String mainfilename = city.getCityName() + submitedfilename.substring(submitedfilename.lastIndexOf("."));
            SaveimageInFolder(request.getPart("cityimage"), filePath + mainfilename);

            city.setImage(mainfilename);
            city.setPopular(Integer.parseInt(request.getParameter("popular")));

//            daoFactory.getCityDAO().updateCityData(city);
            cityDAO.updateCityData(city);
        } catch (ServletException e) {
            throw new ApplicationException("Opps, something went wrong", e);
        } catch (IOException e) {
            throw new ApplicationException("Opps, something went wrong", e);
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
        inputStream.close();
    }
}
