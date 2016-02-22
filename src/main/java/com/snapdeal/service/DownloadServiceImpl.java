package com.snapdeal.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.snapdeal.component.MailDetails;
import com.snapdeal.component.SessionDetails;
import com.snapdeal.dao.Dao;
import com.snapdeal.dto.RequestDetails;
import com.snapdeal.entity.Courier;

@Service("downloadService")
public class DownloadServiceImpl implements DownloadService {

	@Autowired
	Dao localDao;

	@Autowired
	CourierService courierService;

	@Inject
	@Named("sessionDetails")
	SessionDetails sessionDetails;
	
	@Autowired
	MailService mailService;

	@Override
	public void generateRequest(String cond, String status,	HttpServletResponse response) 
	{
		List<RequestDetails> requestList = localDao.getDownloadData(cond);
		try {
			String header = "AWB,Courier Code,W/H Code,Type,Reference Number,Request ID, Seller Code,Seller Name"
					+ ",Seller Address,Seller City,"
					+ "Seller State,Seller Mobile,Seller Pincode,Seller Email,"
					+ "Seller Tin,Request Status,6.5x6.5,8.5x11.5,11.5x13.5,13.5x16,15.5x18,17.5x19,20x22.5,22.5x24.5,Tape,Sticker,"
					+ "Created,Declared Value,Shipper Name,Return Address1,Return Address2,Return Address3"
					+ ",Return Pin\n";

			StringBuilder line = new StringBuilder("");
			String declaredValue;
			line.append(header);
			response.setContentType("text/csv");
			response.setHeader("Content-Disposition",
					"attachment; filename=Request.csv");

			for (RequestDetails rd : requestList) {
				String[] id = rd.getId().split("/");

				if (status.equals("Request Created")) {
					localDao.UpdateStatus(sessionDetails.getSessionUser()
							.getId(), Long.parseLong(id[2]),
							"Request Processing");
				}
				
				declaredValue = getDeclaredValue(rd.getCourierCode());

				line.append(joinString(rd.getAwb()))
						.append(joinString(rd.getCourierCode()))
						.append(joinString(rd.getWarehouseCode()))
						.append(joinString("NON COD"))
						.append(joinString(rd.getRefNumber()))
						.append(joinString(rd.getId()))
						.append(joinString(rd.getSellerCode()))
						.append(joinString(rd.getSellerName()))
						.append(joinString(rd.getSellerAddress().replace(',', ';')))
						.append(joinString(rd.getSellerCity()))
						.append(joinString(rd.getSellerState()))
						.append(joinString(rd.getSellerMobile()))
						.append(joinString(rd.getSellerPincode()))
						.append(joinString(rd.getSellerEmail()))
						.append(joinString(rd.getTin()))
						.append(joinString(rd.getStatus()))
						.append(joinString(rd.getPara1().toString()))
						.append(joinString(rd.getPara2().toString()))
//						.append(joinString(rd.getPara3().toString()))
						.append(joinString(rd.getPara4().toString()))
						.append(joinString(rd.getPara5().toString()))
						.append(joinString(rd.getPara6().toString()))
						.append(joinString(rd.getPara7().toString()))
						.append(joinString(rd.getPara10().toString()))
						.append(joinString(rd.getPara11().toString()))
						.append(joinString(rd.getPara8().toString()))
						.append(joinString(rd.getPara9().toString()))
						.append(joinString(rd.getCreated()))
						.append(joinString(declaredValue))
						.append(joinString(rd.getWarehouseName()))
						.append(joinString(rd.getWarehouseAddress1().replace(',', ';')))
						.append(joinString(rd.getWarehouseAddress2().replace(',', ';')))
						.append(joinString(rd.getWarehouseCity()))
						.append(rd.getWarhousePincode()).append("\n");

			}
			response.getWriter().write(line.toString());
			
			mailService.setMailDetails(getMailDetails());
			
			ExecutorService executor = Executors.newFixedThreadPool(1);
			executor.execute(mailService);
			executor.shutdown();
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private String joinString(String str) {
		return str + ",";
	}

	@Cacheable("declaredValue")
	private String getDeclaredValue(String courierCode) {
		Courier courier = new Courier();
		Long id = courierService.getCourierIdByCode(courierCode);
		if (id != null)
			 courier = courierService.findCourierByid(id);
		else
			return "850";
		if (courier.getType().equalsIgnoreCase("SURFACE"))
			return "1500";
		else
			return "850";
	}

	@Override
	public String generateDownloadConditions(String sellerCode,String status, 
			String dateRange,	Long courierId, Long warehouseId) 
	{
		String conditions ="";
		
		if (!status.equals("ALL")){
			conditions = "where requeststatus = '"+ status +"'";
		}
		if (!sellerCode.equals("")){
			if (conditions.equals("")){
				conditions = "where SellerCode = '" + sellerCode +"'";
			}
			else {
				conditions = conditions +" and SellerCode = '" + sellerCode +"'";
			}
				
		}
		
		if (courierId != null){
			if (conditions.equals("")){
				conditions = "where courierCode = " + courierId ;
			}
			else {
				conditions = conditions +" and courierCode = " + courierId ;
			}	
		}
		
		if (warehouseId != null){
			if (conditions.equals("")){
				conditions = "where warehouse = " + warehouseId ;
			}
			else {
				conditions = conditions +" and warehouse = " + warehouseId;
			}	
		}
		
		if (!status.equals("Request Created")){
			if (conditions.equals("")){
				conditions = "where processed != NULL ";
			}
			else {
				conditions = conditions +" and processed != NULL ";
			}	
		}
		
		if (!dateRange.equals("")){
			String[] parts = dateRange.split("-");
			if (conditions.equals("")){
				conditions = " where Date_Format(sr.created,'%Y/%m/%d %H:%i:%s')  >= '"+ parts[0] +" 00:00:00' and Date_Format(sr.created,'%Y/%m/%d %H:%i:%s') <= '"+ parts[1].trim() +" 23:59:59'";
			}
			else{
				conditions = conditions + " and Date_Format(sr.created,'%Y/%m/%d %H:%i:%s')  >= '"+ parts[0] +" 00:00:00' and Date_Format(sr.created,'%Y/%m/%d %H:%i:%s') <= '"+ parts[1].trim() +" 23:59:59'";
			}
		}
		
		System.out.println("cond"+conditions) ;
		return conditions;
	}

	private MailDetails getMailDetails()
	{
		MailDetails mailDetails = new MailDetails();
//		String[] recipients = {"mohit.gupta@snapdeal.com","harshit.gupta@snapdeal.com"};
		String[] recipients = {"mohit.gupta@snapdeal.com","dharmendar.kumar@snapdeal.com","virender.sharma@snapdeal.com","deepak.batra@snapdeal.com","jitendra.singh@snapdeal.com"};
		
		mailDetails.setRecipients(recipients);
		
		mailDetails.setSender("noreply@snapdeal.in");
		mailDetails.setSubject("PCA Request Downloaded");
		mailDetails.setCurrentDate(new Date());
		mailDetails.setUsername(sessionDetails.getSessionUser().getUsername());
		
		mailDetails.setBody("Requests Downloaded from PCA at : "+mailDetails.getCurrentDate()
				+" by user : "+ mailDetails.getUsername());
		
		return mailDetails;
	}
}
