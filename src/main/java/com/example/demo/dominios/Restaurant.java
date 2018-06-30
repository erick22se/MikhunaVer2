package com.example.demo.dominios;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="restaurant")
public class Restaurant {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int restaurantId;
	@NotNull
	@NotEmpty
	@Size(min = 4, max = 30)
	private String name;
	@NotNull
	@NotEmpty
	@Min(900000000)
	@Max(999999999)
	private String cellphone;
	@NotNull
	@NotEmpty
	private String openHour;
	@NotNull
	@NotEmpty
	private String closeHour;
	@NotNull
	private boolean state;
	
	@ManyToOne
	@JoinColumn(name="userId")
	private User user;
	
	@ManyToOne
	@JoinColumn(name="districtId")
	private District district;
	
	public Restaurant(int restaurantId, String name, String cellphone, String openHour, String closeHour, boolean state,
			User user, District district) {
		super();
		this.restaurantId = restaurantId;
		this.name = name;
		this.cellphone = cellphone;
		this.openHour = openHour;
		this.closeHour = closeHour;
		this.state = state;
		this.user = user;
		this.district = district;
	}

	public Restaurant() {
		super();
		this.name = "";
		this.cellphone = "";
		this.openHour = "";
		this.closeHour = "";
		this.state = false;
	}
	
	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	public int getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public boolean isState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public District getDistrict() {
		return district;
	}

	public void setDistrict(District district) {
		this.district = district;
	}
}
