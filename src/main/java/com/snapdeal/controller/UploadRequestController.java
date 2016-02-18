package com.snapdeal.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.snapdeal.component.SessionDetails;
import com.snapdeal.dao.Dao;
import com.snapdeal.dto.FileBean;
import com.snapdeal.entity.Courier;
import com.snapdeal.entity.Warehouse;
import com.snapdeal.service.CourierService;
import com.snapdeal.service.WarehouseService;

@Controller
@RequestMapping("/Upload")
public class UploadRequestController {
	@Autowired
	Dao localDao;

	
	@Inject
	@Named("warehouseService")
	WarehouseService warehouseService;
	
	@Inject
	@Named("courierService")
	CourierService courierService;
	
	
	@Inject
	@Named("sessionDetails")
	SessionDetails sessionDetails;
	@RequestMapping(value="/uploadRequest")
	public String createRequest( ModelMap map){
//		map.put("name", sessionDetails.getSessionUser().getUsername());
		return "/upload";
	}
	
	

	
	@SuppressWarnings("unchecked")
	@RequestMapping("/upload")
	public void uploadRequest(@ModelAttribute("postedFile") FileBean file,ModelMap map,HttpServletResponse response,HttpServletResponse response1)
	{
	
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		MultipartFile postedFile = file.getPostedFile();
		//			String msg = "";
		String errorLine;
		
		Long userId = sessionDetails.getSessionUser().getId();
		List <String> status = new ArrayList<String>();
		status.add("Request Rejected");
		status.add("Dispatched");
		status.add("Delivered");
		status.add("Delayed");
		status.add("RTO");
		
		if(postedFile != null)
		{
			InputStream inputStream;
			try {

				inputStream = postedFile.getInputStream();
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
				String line = "";
				String splitBy = ",";


				int header = 1;
				int counter = 1;
				int error = 0;
				int error1 = 0;
				int requestErr = 0;
				while ((line = bufferedReader.readLine()) != null)
				{
					String [] fileHeader = line.split(splitBy);
					if (header ==1){
						System.out.println(fileHeader[0]);
						System.out.println(fileHeader[1]);
						System.out.println(fileHeader[2]);
						System.out.println(fileHeader[3]);
						System.out.println(fileHeader[4]);
						System.out.println(fileHeader[5]);
						
						
						if(!fileHeader[0].trim().equals("Request ID")||!fileHeader[1].trim().equals("Request Status")||!fileHeader[2].trim().equals("warehouse Code")||!fileHeader[3].trim().equals("AWB")||!fileHeader[4].trim().equals("Courier Code"))
						{
							error = 1;

							break;
						}
						else
						{
							header = 2;
						}
					}
					else
					{
						counter++;
						String [] data1 = line.split(splitBy);
						  
						if (data1[0].equals("")){
							errorLine = "Request ID is Blank : Line No "+ counter +"\n";
							response.getWriter().write(errorLine);
							error1=1;
							requestErr =1;
						}	
						else{
							try{
								String [] request = data1[0].split("/",2);
							}
							catch(NullPointerException e){
								
								errorLine = "Request ID is incorrect : Line No "+ counter +"\n";
								response.getWriter().write(errorLine);
								requestErr =1;
							}
							
						}
						
						if (data1[1].equals("")){
							errorLine = "Request Status is Blank : Line No "+ counter +"\n";
							response.getWriter().write(errorLine);
							error1=1;
						}	
						else{
							Boolean contains =  status.contains(data1[1].trim());
							if (!contains)
							{
								errorLine = "Request Status is Invalid : Line No "+ counter +"\n";
								response.getWriter().write(errorLine);
								error1=1;
							}
							
						}
						
						if (data1[2].equals("")){
							errorLine = "Warehouse Code is blank : Line No "+ counter +"\n";
							response.getWriter().write(errorLine);
							error1=1;
						}	
						else if (warehouseService.checkCode(data1[2].trim())){
								errorLine = "Warehouse Code is Invalid : Line No "+ counter +"\n";
								response.getWriter().write(errorLine);
								error1=1;
							}
						else if(!checkWarehouseForUser(data1[2].trim()))
						{
							errorLine = "Warehouse Code is Not Mapped to User : Line No "+ counter +"\n";
							response.getWriter().write(errorLine);
							error1=1;
						}	
						

						if (data1[4].equals("")){
							errorLine = "Courier Code is blank : Line No "+ counter +"\n";
							response.getWriter().write(errorLine);
							error1=1;
						}	
						else{
							if (courierService.checkCode(data1[4].trim())){
								errorLine = "Courier Code is Invalid : Line No "+ counter +"\n";
								response.getWriter().write(errorLine);
								error1=1;
							}
						}
												
						try{
							
							Long para1 =   Long.parseLong(data1[5].trim());
							if (para1 > 0 && requestErr == 0 ){
								if (!localDao.checkQty("para1", data1[0], para1)){
									errorLine = "Dispatch Quantity (6.5X6.5) greater than Order Qty : Line No "+ counter +"\n";
									response.getWriter().write(errorLine);
									error1=1;
								}
							}
						}
						catch(NumberFormatException nfe)  
						{  
							errorLine = "Invalid Quantity (6.5X6.5) : Line No "+ counter +"\n";
							response.getWriter().write(errorLine);
							error1=1; 
						}
						
						try{
							Long para2 = Long.parseLong(data1[6]);
							
							if (para2 > 0 && requestErr == 0 ){
								if (!localDao.checkQty("para2", data1[0], para2)){
									errorLine = "Dispatch Quantity (8.5*11.5) greater than Order Qty : Line No "+ counter +"\n";
									response.getWriter().write(errorLine);
									error1=1;
								}
							}
						}
						catch(NumberFormatException nfe)  
						{  
							errorLine = "Invalid Quantity (8.5*11.5) : Line No "+ counter +"\n";
							response.getWriter().write(errorLine);
							error1=1; 
						}
						
						
						/*try{
							Long para3 = Long.parseLong(data1[7]);
							if (para3 > 0 && requestErr == 0 ){
								if (!localDao.checkQty("para3", data1[0], para3)){
									errorLine = "Dispatch Quantity (10x10) greater than Order Qty : Line No "+ counter +"\n";
									response.getWriter().write(errorLine);
									error1=1;
								}
							}
							
						}
						catch(NumberFormatException nfe)  
						{  
							errorLine = "Invalid Quantity (10x10) : Line No "+ counter +"\n";
							response.getWriter().write(errorLine);
							error1=1; 
						}
		*/
						
						try{
							Long para4 = Long.parseLong(data1[7]);
							if (para4 > 0 && requestErr == 0 ){
								if (!localDao.checkQty("para4", data1[0], para4)){
									errorLine = "Dispatch Quantity (11.5x13.5) greater than Order Qty : Line No "+ counter +"\n";
									response.getWriter().write(errorLine);
									error1=1;
								}
							}
						}
						catch(NumberFormatException nfe)  
						{  
							errorLine = "Invalid Quantity (11.5x13.5) : Line No "+ counter +"\n";
							response.getWriter().write(errorLine);
							error1=1; 
						}
						
						
						try{
							Long para5 = Long.parseLong(data1[8]);
							if (para5 > 0 && requestErr == 0 ){
								if (!localDao.checkQty("para5", data1[0], para5)){
									errorLine = "Dispatch Quantity (13.5x16) greater than Order Qty : Line No "+ counter +"\n";
									response.getWriter().write(errorLine);
									error1=1;
								}
							}
						}
						catch(NumberFormatException nfe)  
						{  
							errorLine = "Invalid Quantity (13.5x16) : Line No "+ counter +"\n";
							response.getWriter().write(errorLine);
							error1=1; 
						}

						
						try{
							 Long para6 = Long.parseLong(data1[9]);
							 if (para6 > 0 && requestErr == 0 ){
									if (!localDao.checkQty("para6", data1[0], para6)){
										errorLine = "Dispatch Quantity (15.5x18) greater than Order Qty : Line No "+ counter +"\n";
										response.getWriter().write(errorLine);
										error1=1;
									}
								}
						}
						catch(NumberFormatException nfe)  
						{  
							errorLine = "Invalid Quantity (15.5x18) : Line No "+ counter +"\n";
							response.getWriter().write(errorLine);
							error1=1; 
						}
						
						
						try{
							Long para7 = Long.parseLong(data1[10]);
							if (para7 > 0 && requestErr == 0 ){
								if (!localDao.checkQty("para7", data1[0], para7)){
									errorLine = "Dispatch Quantity (17.5x19) greater than Order Qty : Line No "+ counter +"\n";
									response.getWriter().write(errorLine);
									error1=1;
								}
							}
						}
						catch(NumberFormatException nfe)  
						{  
							errorLine = "Invalid Quantity (17.5x19) : Line No "+ counter +"\n";
							response.getWriter().write(errorLine);
							error1=1; 
						}
						
						
						try{
							Long para10 = Long.parseLong(data1[11]);
							if (para10 > 0 && requestErr == 0 ){
								if (!localDao.checkQty("para10", data1[0], para10)){
									errorLine = "Dispatch Quantity (20x22.5) greater than Order Qty : Line No "+ counter +"\n";
									response.getWriter().write(errorLine);
									error1=1;
								}
							}
						}
						catch(NumberFormatException nfe)  
						{  
							errorLine = "Invalid Quantity (20x22.5) : Line No "+ counter +"\n";
							response.getWriter().write(errorLine);
							error1=1; 
						}
						
						try{
							Long para11 = Long.parseLong(data1[12]);
							if (para11 > 0 && requestErr == 0 ){
								if (!localDao.checkQty("para11", data1[0], para11)){
									errorLine = "Dispatch Quantity (22.5x24.5) greater than Order Qty : Line No "+ counter +"\n";
									response.getWriter().write(errorLine);
									error1=1;
								}
							}
						}
						catch(NumberFormatException nfe)  
						{  
							errorLine = "Invalid Quantity (22.5x24.5) : Line No "+ counter +"\n";
							response.getWriter().write(errorLine);
							error1=1; 
						}

						
						try{
							Long para8 = Long.parseLong(data1[13]);
							if (para8 > 0 && requestErr == 0 ){
								if (!localDao.checkQty("para8", data1[0], para8)){
									errorLine = "Dispatch Quantity (Tape) greater than Order Qty : Line No "+ counter +"\n";
									response.getWriter().write(errorLine);
									error1=1;
								}
							}
						}
						catch(NumberFormatException nfe)  
						{  
							errorLine = "Invalid Quantity (Tape) : Line No "+ counter +"\n";
							response.getWriter().write(errorLine);
							error1=1; 
						}
						
						try{
							Long para9 = Long.parseLong(data1[14]);
							if (para9 > 0 && requestErr == 0 ){
								if (!localDao.checkQty("para9", data1[0], para9)){
									errorLine = "Dispatch Quantity (Sticker) greater than Order Qty : Line No "+ counter +"\n";
									response.getWriter().write(errorLine);
									error1=1;
								}
							}
						}
						catch(NumberFormatException nfe)  
						{  
							errorLine = "Invalid Quantity (Sticker) : Line No "+ counter +"\n";
							response.getWriter().write(errorLine);
							error1=1; 
						}
						 if (error1 == 0){
							Long courierId = courierService.getCourierIdByCode(data1[4]);
							Long warehouseId = warehouseService.getWarehouseIdByCode(data1[2]);
							
							Boolean updated = localDao.UpdateData(data1, userId, courierId, warehouseId);
							
							if (updated){
								errorLine = "Request("+data1[0]+ ") Updated Successfully : Line No "+ counter +"\n";
								response.getWriter().write(errorLine);
								
							}
						}
						
					}
				}
				if(error == 1){
					response.getWriter().write("Invalid File Uploaded");
					response.setContentType("text/csv");
					response.setHeader("Content-Disposition", "attachment; filename=ErrorFile.csv");
				}
				else{
					response.setContentType("text/csv");
					response.setHeader("Content-Disposition", "attachment; filename=logFile "+dateFormat.format(date)+ ".csv");
				}
			
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}

		FileBean newFile = new FileBean();
		map.put("fileData", newFile);
	}


	private boolean checkWarehouseForUser(String warehouseCode) {
		
		Warehouse warehouse = warehouseService.getWarehouseByCode(warehouseCode);
		if(warehouse != null)
		{
			if(sessionDetails.getSessionUser().getUserWarehouse().contains(warehouse))
				return true;
			else
				return false;
		}
		else 
			return false;
	}




	@RequestMapping("/downloadTemplate")
	public void downloadRequest(ModelMap map,HttpServletResponse response) throws ParseException{
		try {
			String line="Request ID,Request Status,warehouse Code,AWB,Courier Code,6.5x6.5,8.5*11.5,11.5x13.5,13.5x16,15.5x18,17.5x19,20x22.5,22.5x24.5,Tape,Sticker,Comment \n";
			response.setContentType("text/csv");
			response.setHeader("Content-Disposition", "attachment; filename=Request.csv");
			response.setContentLength(line.length());
			response.getWriter().write(line);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
//	@RequestMapping("/getData")
//	public RequestDetails getData(@RequestParam("id") Long id,ModelMap map) throws ParseException{
//		RequestDetails rd = localDao.getRequestDataBasedonID(id);
//	    return rd;
//		
//	}
//	
	
}

