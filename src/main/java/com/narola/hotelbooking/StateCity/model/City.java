package com.narola.hotelbooking.StateCity.model;

import org.springframework.web.multipart.MultipartFile;

public class City {

	private int stateId;
	private String cityName;
	private MultipartFile cityimage;
	private int popular;

	private int id;
	private String image;
	private String stateName;
	private String imagePath;

	public City() {
	}

	public City(int stateId, String cityName, int popular, int id, String image, String stateName) {
		this.stateId = stateId;
		this.cityName = cityName;
		this.cityimage = cityimage;
		this.popular = popular;
		this.id = id;
		this.image = image;
		this.stateName = stateName;
		this.imagePath = imagePath;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getStateId() {
		return stateId;
	}
	public void setStateId(int stateid) {
		this.stateId = stateid;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	public int getPopular() {
		return popular;
	}
	public void setPopular(int popular) {
		this.popular = popular;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}

	public MultipartFile getCityimage() {
		return cityimage;
	}

	public void setCityimage(MultipartFile cityimage) {
		this.cityimage = cityimage;
	}
}
