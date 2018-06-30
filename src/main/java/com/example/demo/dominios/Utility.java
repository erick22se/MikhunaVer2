package com.example.demo.dominios;

public class Utility {

	private String password;
	private boolean confirmation;
	
	public Utility(String password, boolean confirmation) {
		super();
		this.password = password;
		this.confirmation = confirmation;
	}

	public Utility() {
		super();
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isConfirmation() {
		return confirmation;
	}

	public void setConfirmation(boolean confirmation) {
		this.confirmation = confirmation;
	}
	
	
	
}
