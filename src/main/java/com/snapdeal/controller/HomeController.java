package com.snapdeal.controller;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.snapdeal.component.SessionDetails;
import com.snapdeal.dao.Dao;
import com.snapdeal.service.MailService;

@Controller

public class HomeController {
	@Autowired
	Dao localDao;

	@Inject
	@Named("sessionDetails")
	SessionDetails sessionDetails;
	@RequestMapping(value="/home")
	public String createRequest( ModelMap map){
//		map.put("name", sessionDetails.getSessionUser().getUsername());
		return "/home";
	}
	
	@RequestMapping("/error")
	public String getErrorPage()
	{
		return "error";
	}
	
	
//	@RequestMapping("/getRequests")
//	public String getRequest(@ModelAttribute("sellerCode") String sellerCode,@ModelAttribute("status") String status, @ModelAttribute("dateRange") String dateRange,ModelMap map) throws ParseException{
//		
//		String conditions;
//		
//		conditions = "where requeststatus = '"+ status +"'";
//		if (!sellerCode.equals("")){
//			conditions = conditions +" and SellerCode = '" + sellerCode +"'";
//		}  
//		if (!dateRange.equals("")){
//			String[] parts = dateRange.split("-");
//			conditions = conditions + " and Date_Format(created,'%Y/%m/%d %H:%i:%s')  >= '"+ parts[0] +" 00:00:00' and Date_Format(created,'%Y/%m/%d %H:%i:%s') <= '"+ parts[1].trim() +" 23:59:59'";
//		}  
//		
//		System.out.println("cond"+conditions);
//		List<Request> requestList = localDao.getRequestData(conditions);
//		map.put("requestList",requestList); 
//	    
//		return "/details";
//	}
	
	
	
}

