package com.snapdeal.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.snapdeal.entity.Courier;

/** Service Interface for Courier **/
@Service
public interface CourierService {
	public Long saveCourier(Courier courier);
	public Courier searchCourierByCode(String code);
//	public boolean checkCourierByEmail(String email);
//	public void updateCourier(Courier courier);
	public List<String> getAllEnabledCourierCode();
	public List<String> getAllCourierCode();
	public List<Courier> getCourierByType(String type);
	public void enableCourier(Long id);
	public void disableCourier(Long id);
	public Courier findCourierByid(Long id);
	public List<Courier> getAllcourier();
	public List<Courier> getEnabledCourier();
	public boolean checkName(String courierName);
	public boolean checkCode(String courierCode);
	public Long getCourierIdByCode(String Code);
//	public AWBResult getAWB(String pincode, String shippingMode);
//	public void unSetAWB(String courierCode, String trackingNumber);
}
