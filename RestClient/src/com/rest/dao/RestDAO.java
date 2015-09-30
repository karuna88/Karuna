package com.rest.dao;

import org.springframework.stereotype.Repository;

@Repository("restDAO")
public class RestDAO {
	
	public String testMethod()
	{
		return "DAP class works";
	}

}
