package com.rest.client;

/*import com.ibm.json.java.JSONArray
import com.ibm.json.java.JSONObject;*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/*import net.sf.json.JSONArray;
import net.sf.json.JSONObject;*/

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.rest.vo.RestPerson;


@Component("restClient")
public class RestClient {
	
	// Git test commit
	public void testClient()
	{
		HttpClient httpClient = new HttpClient();
		
		GetMethod getMethod = new GetMethod("http://localhost:9080/alpha/rest");
		/*GetMethod getMethod = new GetMethod("http://localhost:9080/alpha/rest2/name");*/
		
		try {
			
			httpClient.executeMethod(getMethod);
			InputStream inputStream = getMethod.getResponseBodyAsStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
			StringBuffer response = new StringBuffer();
			String temp = null;
			while((temp= reader.readLine()) !=null)
			{
				response.append(temp);
			}
			System.out.println("Response value is "+response);
		} catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		PostMethod postMethod = new PostMethod("http://localhost:9080/alpha/rest");
		postMethod.addParameter(new NameValuePair("testName","test"));
		postMethod.setRequestHeader("contentType", "application/json");
		postMethod.setRequestHeader("Accept","application/json");

		JSONArray jsonArray = new JSONArray();
		
		JSONObject jsonObject = new JSONObject();
		
		RestPerson person = new RestPerson();
		person.setId("1234");
		person.setName("name");
		
		try {
			jsonObject.put("id", person.getId());
			jsonObject.put("name",person.getName());
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		jsonArray.put(jsonObject);
		
		postMethod.addParameter(new NameValuePair("jsonArray",jsonArray.toString()));
		try {
			httpClient.executeMethod(postMethod);
			
			InputStream stream2= postMethod.getResponseBodyAsStream();
			
			BufferedReader reader2 = new BufferedReader(new InputStreamReader(stream2));
			String temp = null;
			StringBuffer response = new StringBuffer();
			while((temp=reader2.readLine()) != null)
			{
				response.append(temp);
			}
			System.out.println("Response value is "+response);
			JSONArray jsonArrayResponse;
			try {
				jsonArrayResponse = new JSONArray(response.toString());
				/*jsonArrayResponse = jsonArrayResponse.parse(response.toString()); import com.ibm.json.java.JSONArray*/
				/*jsonArrayResponse=jsonArrayResponse.element(response.toString()); import net.sf.json.JSONArray*/
				JSONObject jsonObjResponse = (JSONObject)jsonArrayResponse.get(0);
				
				//If single object is returned, parse it directly in JSON object or parse it in JSONArray
				/*JSONObject jsonObjResponse2 = new JSONObject();
				jsonObjResponse2 = jsonObjResponse2.parse(response.toString());*/
				
				
				System.out.println("Person name is "+jsonObjResponse.get("id"));
				System.out.println("Person name is "+jsonObjResponse.get("name"));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
		} catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
