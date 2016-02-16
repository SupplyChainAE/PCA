package com.snapdeal.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.snapdeal.component.SessionDetails;
import com.snapdeal.dao.Dao;
import com.snapdeal.dto.Request;
import com.snapdeal.dto.RequestDetails;
import com.snapdeal.entity.Courier;
import com.snapdeal.entity.Warehouse;
import com.snapdeal.service.CourierService;
import com.snapdeal.service.DownloadService;
import com.snapdeal.service.WarehouseService;

@Controller
@RequestMapping("/Download")
public class DownloadRequestController {
	@Autowired
	Dao localDao;

	
	@Inject
	@Named("warehouseService")
	WarehouseService warehouseService;
	
	@Inject
	@Named("courierService")
	CourierService courierService;
	
	@Inject
	@Named("downloadService")
	DownloadService downloadService;
	
	@Inject
	@Named("sessionDetails")
	SessionDetails sessionDetails;
	
	@RequestMapping(value="/downloadRequest")
	public String createRequest( ModelMap map){
//		map.put("name", sessionDetails.getSessionUser().getUsername());
		List<Warehouse> warehouseList = warehouseService.getAllWarehouse();
		List<Courier> courierList = courierService.getEnabledCourier();
		
		map.put("warehouse", warehouseList);
		map.put("courier", courierList);
		
		return "/downloadRequest";
	}
	
	
	@RequestMapping("/getRequests")
	public String getRequest(@ModelAttribute("sellerCode") String sellerCode,@ModelAttribute("status") String status, @ModelAttribute("dateRange") String dateRange,
			@RequestParam(value="courier",required=false)Long courierId,@RequestParam(value="warehouse",required=false)Long warehouseId, ModelMap map) throws ParseException{
		
		List<Warehouse> warehouseList = warehouseService.getEnabledWarehouses();
	    String conditions = downloadService.generateDownloadConditions(sellerCode, status, dateRange, courierId, warehouseId);
	    List<Courier> courierList = courierService.getEnabledCourier();
	    List<Request> requestList = localDao.getRequestData(conditions);
		
	    map.put("warehouse",warehouseList);
		map.put("condition", conditions);
		map.put("status", status);
		map.put("courier",courierList);
		map.put("requestList",requestList); 
	    return "/details";
		
	}

	@RequestMapping("/downloadRequests")
	public void downloadRequest(@RequestParam("cond") String cond,@RequestParam("status") String status,ModelMap map,HttpServletResponse response) throws ParseException{
		System.out.println("cond"+ cond);
		downloadService.generateRequest(cond, status, response);
	}

	@RequestMapping("/getData")
	public @ResponseBody RequestDetails getData(@RequestParam("id") Long id,ModelMap map) throws ParseException{
		RequestDetails rd = localDao.getRequestDataBasedonID(id);
		
	    return rd;	
	}
	
	
}

