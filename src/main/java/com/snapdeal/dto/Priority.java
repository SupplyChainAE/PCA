package com.snapdeal.dto;



public class Priority {

	private Long Id ;
	private String Code ;
	private String  Name;
	private Long priority ;
	private Long load;
	
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public String getCode() {
		return Code;
	}
	public void setCode(String code) {
		Code = code;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public Long getPriority() {
		return priority;
	}
	public void setPriority(Long priority) {
		this.priority = priority;
	}
	public void setLoad(Long load) {
		this.load = load;
	}
	public Long getLoad() {
		return load;
	}
	
			
	
}
