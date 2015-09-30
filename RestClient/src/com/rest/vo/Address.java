package com.rest.vo;

public class Address {
	
	private String street;
	
	private int aptNum;
	
	private String city;
	
	private String state;
	
	private long pincode;

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public int getAptNum() {
		return aptNum;
	}

	public void setAptNum(int aptNum) {
		this.aptNum = aptNum;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public long getPincode() {
		return pincode;
	}

	public void setPincode(long pincode) {
		this.pincode = pincode;
	}
	
	
}
