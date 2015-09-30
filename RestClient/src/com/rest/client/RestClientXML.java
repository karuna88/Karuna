package com.rest.client;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.springframework.stereotype.Component;

import com.rest.vo.RestPersonXML;

@Component("restClientXML")
public class RestClientXML {
	
	public void testXMLClient()
	{
		HttpClient httpClient= HttpClients.createDefault();
		
		HttpPost httpPost = new HttpPost("http://localhost:8080/restService/rest/xmlPostTest");
		httpPost.setHeader(new BasicHeader("Content-Type","application/xml"));
		httpPost.setHeader("Accept","application/xml");
		
		HttpParams httpParam = new BasicHttpParams();
		httpParam.setParameter("testName", "restXML");
		httpPost.setParams(httpParam);
					
			RestPersonXML restPersonXML = new RestPersonXML();
			restPersonXML.setId(1);
			restPersonXML.setName("Dave");
			restPersonXML.setPhoneNum(000);
			
			RestPersonXML restPersonXML2 = new RestPersonXML();
			restPersonXML2.setId(007);
			restPersonXML2.setName("James");
			restPersonXML2.setPhoneNum(123456789);
			
								
			try {
				JAXBContext jaxbContext = JAXBContext.newInstance(RestPersonXML.class);
				Marshaller marshaller = jaxbContext.createMarshaller();
				
				StringWriter xmlInput = new StringWriter();
				marshaller.marshal(restPersonXML, xmlInput);
				
				/*StringWriter xmlInput2 = new StringWriter();
				marshaller.marshal(restPersonXML2, xmlInput2);*/
				
				List<NameValuePair> paramList= new ArrayList<NameValuePair>();
				paramList.add(new BasicNameValuePair("xmlInput",xmlInput.toString()));
				
				/*paramList.add(new BasicNameValuePair("xmlInput2",xmlInput2.toString()));*/
	
			httpPost.setEntity(new UrlEncodedFormEntity(paramList,Consts.UTF_8));
			HttpResponse response = httpClient.execute(httpPost);
			System.out.println("Post Response is "+response);
			HttpEntity responseEntity =  response.getEntity();
			
						
				JAXBContext jabxContext2 = JAXBContext.newInstance(RestPersonXML.class);
				Unmarshaller unMarshaller = jabxContext2.createUnmarshaller();
				
				RestPersonXML personXML = (RestPersonXML) unMarshaller.unmarshal(responseEntity.getContent());
				
				System.out.println("The Client Person XML id is "+personXML.getId());
				System.out.println("The Client Person XML name is "+personXML.getName());
				System.out.println("The Client Person XML phone number is "+personXML.getPhoneNum());
			} catch (JAXBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		HttpGet httpGet = new HttpGet("http://localhost:8080/restService/rest/xmlGetTest");
		httpGet.setHeader(new BasicHeader("Content-Type","application/xml"));
		httpGet.setHeader("Accept","application/xml");
		
		try {
			HttpResponse response = httpClient.execute(httpGet);
			System.out.println("Get Response is "+response);
			HttpEntity responseEntity =  response.getEntity();
			
			JAXBContext jaxbContext;
			try {
				jaxbContext = JAXBContext.newInstance(RestPersonXML.class);
				Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
				RestPersonXML personXML = (RestPersonXML)unmarshaller.unmarshal(responseEntity.getContent());
				
				System.out.println("The Client Person XML id is "+personXML.getId());
				System.out.println("The Client Person XML name is "+personXML.getName());
				System.out.println("The Client Person XML phone number is "+personXML.getPhoneNum());
			} catch (JAXBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
