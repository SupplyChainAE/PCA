package com.snapdeal.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="couriers")
public class Courier extends BaseEntity {
	
	/**
	 * Courier Entity
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name="name",length=100,nullable=false)
	private String name;
	
	@Column(name="type",length=10,nullable=false)
	private String type;
	
	@Column(name="priority")
	private Long priority=0L;
	
	@Column(name="load_limit")
	private Long load = 0L;
	
	@Column(name="current_load")
	private Long currentLoad =0L;
	
	@Column(name="code",length=200,nullable=false)
	private String code;
	
	@Column(name="enabled")
	private Boolean enabled = false;
	
	@ManyToMany(fetch = FetchType.EAGER)
	private List<Warehouse> warehouseList;
	
	public Long getPriority() {
		return priority;
	}
	public void setPriority(Long priority) {
		this.priority = priority;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Boolean getEnabled() {
		return enabled;
	}
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	public List<Warehouse> getWarehouseList() {
		return warehouseList;
	}
	public void setWarehouseList(List<Warehouse> warehouseList) {
		this.warehouseList = warehouseList;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setLoad(Long load) {
		this.load = load;
	}
	public Long getLoad() {
		return load;
	}
	public void setCurrentLoad(Long currentLoad) {
		this.currentLoad = currentLoad;
	}
	public Long getCurrentLoad() {
		return currentLoad;
	}
}