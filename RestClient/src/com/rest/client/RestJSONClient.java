package com.rest.client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rest.vo.Address;
import com.rest.vo.RestPerson;
import com.rest.vo.RestResponseVO;

@Component("restJSONClient")
public class RestJSONClient {
	
	public void testClient()
	{
		/*HttpClient httpClient = new DefaultHttpClient();*/
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpClient httpClient3 = HttpClientBuilder.create().build();
		
		/*SSLContext sslcontext = null;
		try {
			sslcontext = SSLContexts.custom()
			        .loadTrustMaterial(new File("my.keystore"), "nopassword".toCharArray(),
			                new TrustSelfSignedStrategy())
			        .build();
		} catch (KeyManagementException | NoSuchAlgorithmException
				| KeyStoreException | CertificateException | IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
        // Allow TLSv1 protocol only
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
                sslcontext,
                new String[] { "TLSv1" },
                null,
                SSLConnectionSocketFactory.getDefaultHostnameVerifier());
        CloseableHttpClient httpclient = HttpClients.custom()
                .setSSLSocketFactory(sslsf)
                .build();
*/
		ObjectMapper objectMapper = new ObjectMapper();
		
		
		HttpPost httpPost = new HttpPost("http://localhost:8080/restService/rest/jsonPostTest");
		httpPost.setHeader(new BasicHeader("Content-Type","application/json"));
		httpPost.setHeader(new BasicHeader("Accept","application/json"));
		
		JSONArray jsonArrayParam = new JSONArray();
		JSONObject jsonObjParam = new JSONObject();
		
		RestPerson restPerson = new RestPerson();
		restPerson.setId("1234");
		restPerson.setName("Alex");
		
	
			try {
				jsonObjParam.put("id", restPerson.getId());
				jsonObjParam.put("name", restPerson.getName());
			} catch (JSONException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		jsonArrayParam.put(jsonObjParam);
				
		List<NameValuePair> paramList = new ArrayList<NameValuePair>();
		paramList.add(new BasicNameValuePair("jsonArray",jsonArrayParam.toString()));
		paramList.add(new BasicNameValuePair("testName","test"));
		
			/*try {*/
				HttpEntity entity = new  UrlEncodedFormEntity(paramList,Consts.UTF_8);
				System.out.println(entity.getContentEncoding());
				System.out.println(entity.getContentType());
				httpPost.setEntity(entity);
			/*} catch (UnsupportedEncodingException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}*/
		
		try {
			HttpResponse httpRes = httpClient3.execute(httpPost);
			System.out.println("Rest response is"+httpRes.toString());
			HttpEntity httpEntity = httpRes.getEntity();
			System.out.println("Rest response is"+httpEntity.toString());
			
			/*List<RestPerson> personList = objectMapper.readValue(httpEntity.getContent(),List.class);*/
			
			List<RestPerson> personList = objectMapper.readValue(httpEntity.getContent(),new TypeReference<List<RestPerson>>() {});
			for(RestPerson person : personList)
			{
				System.out.println("The Client Person id is "+person.getId());
				System.out.println("The Client Person name is "+person.getName());
			}
			/*RestPerson person = objectMapper.readValue(httpEntity.getContent(), RestPerson.class);
			
				System.out.println("The Person id is "+person.getId());
				System.out.println("The person name is "+person.getName());*/
			
		} catch (ClientProtocolException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		
		/*HttpGet httpGet = new HttpGet("http://localhost:8080/restService/rest/jsonGetRest");
		httpGet.setHeader(new BasicHeader("Content-Type","application/json"));
		httpGet.setHeader(new BasicHeader("Accept","application/json"));
		
		try {
			HttpResponse httpResponse = httpclient.execute(httpGet);
			
			HttpEntity httpEntity = httpResponse.getEntity();
			RestPerson person = objectMapper.readValue(httpEntity.getContent(), RestPerson.class);
					
				System.out.println("The Person id is "+person.getId());
				System.out.println("The person name is "+person.getName());
			
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
	}
	
	public void testClientGet()
	{
		HttpGet httpGet = new HttpGet("http://localhost:8080/restService/rest/jsonGetRest/Emila/Jon/Sansa?site1=Netflix&site2=HBO");
		httpGet.setHeader(new BasicHeader("Content-Type","application/json"));
		httpGet.setHeader(new BasicHeader("Accept","application/json"));
		
		try {
			CloseableHttpClient httpclient = HttpClients.createDefault();
			
			HttpResponse httpResponse = httpclient.execute(httpGet);
			
			HttpEntity httpEntity = httpResponse.getEntity();
			
			ObjectMapper objectMapper = new ObjectMapper();
			
			RestResponseVO restResponseVO = objectMapper.readValue(httpEntity.getContent(), RestResponseVO.class);
					
				System.out.println("The Person id is "+restResponseVO.getRestPerson().getId());
				System.out.println("The person name is "+restResponseVO.getRestPerson().getName());
			
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void testClientPost()
	{
		HttpPost httpPost = new HttpPost("http://localhost:8080/restService/rest/jsonPostRest");
		httpPost.setHeader(new BasicHeader("Content-Type","application/json"));
		httpPost.setHeader(new BasicHeader("Accept","application/json"));
		CloseableHttpClient httpclient = HttpClients.createDefault();
		
		JSONObject jsonPersonObject = new JSONObject();
		
		JSONObject jsonAddressObject = new JSONObject();
		
		try {
			jsonPersonObject.put("id", "White Collar");
			jsonPersonObject.put("name", "Peter Burke");
			
			jsonAddressObject.put("street", "White Collar");
			jsonAddressObject.put("aptNum", 24);
			jsonAddressObject.put("pincode", 553);
			jsonAddressObject.put("state", "New Jersey");
			jsonAddressObject.put("city", "New York");
			
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		RestPerson restPerson = new RestPerson();
		restPerson.setId("White Collar");
		restPerson.setName("Peter Burke");
		
		Address address = new Address();
		
		address.setStreet("White Collar");
		address.setAptNum(24);
		address.setPincode(553);
		address.setState("New Jersey");
		address.setCity("New York");
		
		List<Long> contactNums = new ArrayList<Long>();
		contactNums.add(0,new Long(5903));
		contactNums.add(1,new Long(8167));
				
		JSONObject jsonObject = new JSONObject();
		
		try {
			jsonObject.put("address", jsonAddressObject);
			jsonObject.put("restPerson", jsonPersonObject);
			jsonObject.put("contactNums", contactNums);
			jsonObject.put("user", "Karuna");
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try
		{
			HttpEntity entity = new  StringEntity(jsonObject.toString(), ContentType.APPLICATION_JSON);
						
			httpPost.setEntity(entity);
		
		HttpResponse httpResponse = httpclient.execute(httpPost);
				
		HttpEntity httpEntity = httpResponse.getEntity();
		
		ObjectMapper objectMapper = new ObjectMapper();
		
		RestResponseVO restResponseVO = objectMapper.readValue(httpEntity.getContent(), RestResponseVO.class);
		
		System.out.println("The Person id is "+restResponseVO.getRestPerson().getId());
		System.out.println("The person name is "+restResponseVO.getRestPerson().getName());
	
		System.out.println("The Status code is :"+httpResponse.getStatusLine().getStatusCode());	
			
		/*List paramList = new ArrayList();
		paramList.add(new BasicNameValuePair("jsonObject",jsonObject.toString()));	
		
		HttpEntity entity2 = new  UrlEncodedFormEntity(paramList,Consts.UTF_8);
			httpPost.setEntity(entity2);
			
			HttpResponse httpResponse2 = httpclient.execute(httpPost);	
			System.out.println("The Status code is :"+httpResponse2.getStatusLine().getStatusCode());*/
		
	} catch (ClientProtocolException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	

}
