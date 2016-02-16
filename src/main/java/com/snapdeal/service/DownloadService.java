package com.snapdeal.service;

import javax.servlet.http.HttpServletResponse;


public interface DownloadService {
	
	void generateRequest(String cond,String status, HttpServletResponse response);
	
	String generateDownloadConditions(String sellerCode,String status, String dateRange,Long courierId,Long warehouseId);
	
}
