package com.rest.client;

//import javax.ws.rs.client.Client;
//import javax.ws.rs.client.ClientBuilder;
//import javax.ws.rs.client.Entity;
//import javax.ws.rs.client.WebTarget;
//import javax.ws.rs.core.MediaType;
//import javax.ws.rs.core.Response;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rest.vo.RestPerson;

@Component("restClienJersey")
public class RestClient_Jersey {
	
	public void testClient()
	{
	
//	Client client = ClientBuilder.newClient();
//	
//	RestPerson restPersonObj = new RestPerson();
//	restPersonObj.setId("1234");
//	restPersonObj.setName("Alex");
//	
// This is rest jersey client. Added by Karuna
//	/*WebTarget target = client.target("http://localhost:8080/restService/rest/getTest");
//	
//	RestPerson restPerson = target.request().accept(MediaType.APPLICATION_JSON).get(RestPerson.class);
//	
//	String restPerson = target.request().accept(MediaType.APPLICATION_JSON).get(String.class);
//	*/
//	WebTarget target = client.target("http://localhost:8080/restService/rest/postTest");
//	
//	Response restPerson = target.request().post(Entity.entity(restPersonObj, MediaType.APPLICATION_JSON));
//	
//	System.out.println(restPerson);
//	
//	ObjectMapper objectMapper = new ObjectMapper();
//	
//	/*try {
//		RestPerson person = objectMapper.readValue(restPerson,new TypeReference<RestPerson>() {});
//		System.out.println(person.getId());
//		System.out.println(person.getName());
//	} catch (JsonParseException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	} catch (JsonMappingException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	} catch (IOException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}*/
	}
}
