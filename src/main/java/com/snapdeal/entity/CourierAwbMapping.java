package com.snapdeal.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="courierAwb")
public class CourierAwbMapping extends BaseEntity {
	
	/**
	 * Courier Entity
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name="awb",length=100,nullable=false)
	private String awb;
	
	@Column(name="courierId",nullable=false)
	private Long courierId;
	
	@Column(name="enabled",nullable=false)
	private Boolean enabled;
	
	@Column(name="processed",nullable=false)
	private Boolean processed;

	@Column(name="ref_number")
	private String refNumber;
	
	public String getAwb() {
		return awb;
	}

	public void setAwb(String awb) {
		this.awb = awb;
	}

	public Long getCourierId() {
		return courierId;
	}

	public void setCourierId(Long courierId) {
		this.courierId = courierId;
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

	public void setRefNumber(String refNumber) {
		this.refNumber = refNumber;
	}

	public String getRefNumber() {
		return refNumber;
	}

	
		
}