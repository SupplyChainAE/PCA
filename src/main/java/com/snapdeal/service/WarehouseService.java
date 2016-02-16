package com.snapdeal.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.snapdeal.entity.Warehouse;

@Service
public interface WarehouseService {

	public Long saveOrUpdateWarehouse(Warehouse warehouse);
	public void enableWarehouse(Long id);
	public void disableWarehouse(Long id);
	public Warehouse findWarehouseByid(Long id);
	public List<Warehouse> getAllWarehouse();
	public List<Warehouse> getEnabledWarehouses();
	public List<Warehouse> getEnabledSDPlusWarehouses();
	public boolean checkName(String warehouseName);
	public boolean checkCode(String warehouseCode);
	public Long getWarehouseIdByCode(String Code);
	
	
//	public void saveOrupdateSkuType(SkuType skuType);
//	public List<SkuType> getAllSkuType();
//	public SkuType findSkuTypeById(Long id);
//	public void removeSkuType(Long id);
	
}
