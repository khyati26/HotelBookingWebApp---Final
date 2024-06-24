package com.narola.hotelbooking.StateCity.model;

public class State {
	private int id;
	private String stateName;

	public State() {
	}

	public State(int id, String stateName) {
		this.id = id;
		this.stateName = stateName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStateName() {
		return stateName;
	}
	public void setStateName(String statename) {
		this.stateName = statename;
	}
}
