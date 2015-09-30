package com.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.rest.client.RestClient;
import com.rest.client.RestXMLClient;
import com.rest.client.RestClient_Jersey;
import com.rest.client.RestJSONClient;
import com.rest.dao.RestDAO;

@Service("restService")
public class RestService {
	
	@Autowired
	@Qualifier("restDAO")
	private RestDAO restDAO;
	
	@Autowired
	@Qualifier("restClient")
	private RestClient restClient;
	
	@Autowired
	@Qualifier("restJSONClient")
	private RestJSONClient restJSONClient;
	
	@Autowired
	@Qualifier("restXMLClient")
	RestXMLClient restXMLClient;
	
	@Autowired
	@Qualifier("restClienJersey")
	RestClient_Jersey restClienJersey;
	
	public String testMethod()
	{
		//getRestClient().testClient();
		//getRestClienJersey().testClient();
		/*getRestClient2().testClient();
		getRestClientXML().testXMLClient();
		*/
		//new client method for Get and Post
		getRestJSONClient().testClientGet();
		getRestJSONClient().testClientPost();
		
		getRestXMLClient().testXMLClient();
		getRestXMLClient().testXMLClientGet();
		getRestXMLClient().testXMLClientPost();
		String msg = getTestDAO().testMethod();
		return msg;
	}

	public RestDAO getTestDAO() {
		return restDAO;
	}

	public void setTestDAO(RestDAO restDAO) {
		this.restDAO = restDAO;
	}

	public RestClient getRestClient() {
		return restClient;
	}

	public void setRestClient(RestClient restClient) {
		this.restClient = restClient;
	}

	public RestJSONClient getRestJSONClient() {
		return restJSONClient;
	}

	public void setRestJSONClient(RestJSONClient restJSONClient) {
		this.restJSONClient = restJSONClient;
	}

	public RestDAO getRestDAO() {
		return restDAO;
	}

	public void setRestDAO(RestDAO restDAO) {
		this.restDAO = restDAO;
	}

	
	public RestXMLClient getRestXMLClient() {
		return restXMLClient;
	}

	public void setRestXMLClient(RestXMLClient restXMLClient) {
		this.restXMLClient = restXMLClient;
	}

	public RestClient_Jersey getRestClienJersey() {
		return restClienJersey;
	}

	public void setRestClienJersey(RestClient_Jersey restClienJersey) {
		this.restClienJersey = restClienJersey;
	}
	
	

}
