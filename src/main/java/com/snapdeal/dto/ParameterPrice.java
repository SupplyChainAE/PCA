package com.snapdeal.dto;



public class ParameterPrice {

	private Long Id ;
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	private String parameterName ;
	private Double  price;
//	private Long priority ;
	public String getParameterName() {
		return parameterName;
	}
	public void setParameterName(String parameterName) {
		this.parameterName = parameterName;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
		
			
	
}
