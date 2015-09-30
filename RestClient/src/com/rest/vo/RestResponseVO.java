package com.rest.vo;

import java.util.List;

public class RestResponseVO {
	
private Address address;
	
	private RestPerson restPerson;
	
	private List<Long> contactNums;
	
	private String user;

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public RestPerson getRestPerson() {
		return restPerson;
	}

	public void setRestPerson(RestPerson restPerson) {
		this.restPerson = restPerson;
	}

	public List<Long> getContactNums() {
		return contactNums;
	}

	public void setContactNums(List<Long> contactNums) {
		this.contactNums = contactNums;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}
	

}
