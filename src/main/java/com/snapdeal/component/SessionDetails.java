package com.snapdeal.component;

import javax.inject.Named;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.snapdeal.entity.User;

@Component
@Named("sessionDetails")
public class SessionDetails {

	public User getSessionUser()
	{
		User sessionUser = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return sessionUser;
	}
	
//	public Long getActiveSupplierId()
//	{
//		User sessionUser = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//		return sessionUser.getActiveWarehouse().getId();
//	}
//	
//	public Warehouse getSessionWarehouse()
//	{
//		User sessionUser = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//		return sessionUser.getActiveWarehouse();
//	}
	
}
