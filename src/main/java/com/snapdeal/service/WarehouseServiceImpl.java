package com.snapdeal.service;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import com.snapdeal.dao.EntityDao;
import com.snapdeal.entity.Warehouse;

@Named("warehouseService")
@Transactional
public class WarehouseServiceImpl implements WarehouseService {

	@Inject
	@Named("entityDao")
	EntityDao entityDao;

	@Override
	public Long saveOrUpdateWarehouse(Warehouse warehouse) {
		entityDao.saveOrUpdate(warehouse);
		return warehouse.getId();
	}

	@Override
	public void enableWarehouse(Long id) {
		Warehouse persitedWarehouse = entityDao.findById(Warehouse.class, id);
		persitedWarehouse.setEnabled(true);
		entityDao.saveOrUpdate(persitedWarehouse);
	}

	@Override
	public void disableWarehouse(Long id) {
		Warehouse persitedWarehouse = entityDao.findById(Warehouse.class, id);
		persitedWarehouse.setEnabled(false);
		entityDao.saveOrUpdate(persitedWarehouse);
	}

	@Override
	public Warehouse findWarehouseByid(Long id) {
		Warehouse warehouse = entityDao.findById(Warehouse.class, id);
		return warehouse;
	}

	@Override
	public List<Warehouse> getAllWarehouse() {
		List<Warehouse> warehouse = entityDao.findAll(Warehouse.class);
		return warehouse;
	}

	@Override
	public List<Warehouse> getEnabledWarehouses() {
		return entityDao.findAllEnabledObjects(Warehouse.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean checkName(String warehouseName) {
		EntityManager entityManager = entityDao.getEntityManager();
		Query query = entityManager.createQuery("Select warehouse.name from Warehouse warehouse where " +
		"warehouse.name = :name");
		query.setParameter("name", warehouseName);
		List<String> nameList = (List<String>)query.getResultList();
		if(nameList != null && nameList.size() > 0)
		{
			return false;	
		}
		else {
			return true;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean checkCode(String warehouseCode) {
		EntityManager entityManager = entityDao.getEntityManager();
		Query query = entityManager.createQuery("Select warehouse.code from Warehouse warehouse where " +
		"warehouse.code = :code");
		query.setParameter("code", warehouseCode);
		List<String> nameList = (List<String>)query.getResultList();
		if(nameList != null && nameList.size() > 0)
		{
			return false;	
		}
		else {
			return true;
		}
	}

	@Override
	public Long getWarehouseIdByCode(String Code) {
		EntityManager entityManager = entityDao.getEntityManager();
		Query query = entityManager.createQuery("Select warehouse.id from Warehouse warehouse where " +
		"warehouse.code = :code");
		query.setParameter("code", Code);
		Long id = (Long) query.getSingleResult();
		return id;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Warehouse> getEnabledSDPlusWarehouses() {
		EntityManager entityManager = entityDao.getEntityManager();
		Query query = entityManager.createQuery("Select warehouse from Warehouse warehouse where " +
		"warehouse.warehouseType = 'SD Plus'");
		List<Warehouse> whList = query.getResultList();
		return whList;
	}
	
	
}
