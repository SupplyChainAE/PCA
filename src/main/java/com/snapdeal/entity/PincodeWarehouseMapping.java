package com.snapdeal.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="pincodeWarehouse")
public class PincodeWarehouseMapping extends BaseEntity {
	
	/**
	 * Courier Entity
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name="pincode",length=10,nullable=false)
	private String pincode;
	
	@Column(name="warehouseId",nullable=false)
	private Long warehouseId;
	
	@Column(name="enabled",nullable=false)
	private Boolean enabled;
	
	@Column(name="processed",nullable=false)
	private Boolean processed;

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public Long getWarehouseId() {
		return warehouseId;
	}

	public void setWarehouseId(Long warehouseId) {
		this.warehouseId = warehouseId;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public Boolean getProcessed() {
		return processed;
	}

	public void setProcessed(Boolean processed) {
		this.processed = processed;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
		
}