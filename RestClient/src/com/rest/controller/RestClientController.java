package com.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.rest.command.RestCommand;
import com.rest.service.RestService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("restClientController")
public class RestClientController {
	
	@Autowired
	@Qualifier("restService")
	private RestService restService;
	
	@RequestMapping(value="restClient.do")
	public ModelAndView testMethod(@ModelAttribute("restCommand") RestCommand restCommand)
	{
		ModelAndView mav = new ModelAndView();
		String msg = restService.testMethod();
		System.out.println("Request flow:"+ msg);
		mav.setViewName("restClient_HomePage");
		return mav;
	}

	public RestService getRestService() {
		return restService;
	}

	public void setRestService(RestService restService) {
		this.restService = restService;
	}
	
	

}
