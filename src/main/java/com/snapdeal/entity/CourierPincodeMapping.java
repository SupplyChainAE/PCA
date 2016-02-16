package com.snapdeal.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="courierPincode")
public class CourierPincodeMapping extends BaseEntity {
	
	/**
	 * Courier Entity
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name="pincode",length=100,nullable=false)
	private String pincode;
	
	@Column(name="courierId",nullable=false)
	private Long courierId;
	
	@Column(name="enabled",nullable=false)
	private Boolean enabled;
	
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

		public static long getSerialversionuid() {
		return serialVersionUID;
	}

		public String getPincode() {
			return pincode;
		}

		public void setPincode(String pincode) {
			this.pincode = pincode;
		}

	
		
}