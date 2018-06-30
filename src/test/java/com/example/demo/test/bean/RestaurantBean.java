package com.example.demo.test.bean;

public class RestaurantBean {
	private String name;
	private String cellphone;
	private String openHour;
	private String closeHour;
	private boolean state;
	public RestaurantBean(String name, String cellphone, String openHour, String closeHour, boolean state) {
		super();
		this.name = name;
		this.cellphone = cellphone;
		this.openHour = openHour;
		this.closeHour = closeHour;
		this.state = state;
	}
	public RestaurantBean() {
		super();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCellphone() {
		return cellphone;
	}
	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}
	public String getOpenHour() {
		return openHour;
	}
	public void setOpenHour(String openHour) {
		this.openHour = openHour;
	}
	public String getCloseHour() {
		return closeHour;
	}
	public void setCloseHour(String closeHour) {
		this.closeHour = closeHour;
	}
	public boolean getState() {
		return state;
	}
	public void setState(boolean state) {
		this.state = state;
	}
}
