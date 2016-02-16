package com.snapdeal.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="warehouse")
public class Warehouse extends BaseEntity{



	private static final long serialVersionUID = 1L;

	@Column(name="name",unique=true)
	private String name;

	@Column(name="code",unique=true)
	private String code;

//	@Column(name="warehouseType")
//	private String warehouseType;


	@Column(name="enabled")
	private boolean enabled = true;


	@Column(name="mobile")
	private String mobile;

	@Column(name="address1")
	private String address1;
	@Column(name="address2")
	private String address2;
	@Column(name="city")
	private String city;

	@Column(name="state")
	private String state;

	@Column(name="pincode")
	private Long pincode;



//	public String getWarehouseType() {
//		return warehouseType;
//	}
//
//	public void setWarehouseType(String warehouseType) {
//		this.warehouseType = warehouseType;
//	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Long getPincode() {
		return pincode;
	}

	public void setPincode(Long pincode) {
		this.pincode = pincode;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}



}
