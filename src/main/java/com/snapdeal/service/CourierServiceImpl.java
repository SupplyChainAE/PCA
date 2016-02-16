package com.snapdeal.service;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import com.snapdeal.component.SessionDetails;
import com.snapdeal.dao.EntityDao;
import com.snapdeal.entity.Courier;

/** Service Implementation for courier.**/
@Named("courierService")
@Transactional
public class CourierServiceImpl implements CourierService{

	@Inject
	@Named("entityDao")
	EntityDao entityDao;
	
//	@Inject
//	@Named("postalCodeService")
//	PostalCodeService postalCodeService;
//	
	@Inject
	@Named("sessionDetails")
	SessionDetails sessionDetails;
	
	/** Save courier details in database.**/
	@Override
	public Long saveCourier(Courier courier){
		entityDao.saveOrUpdate(courier);
		return courier.getId();
	}
	
	@Override
	public void enableCourier(Long id) {
		Courier persitedCourier = entityDao.findById(Courier.class, id);
		persitedCourier.setEnabled(true);
		entityDao.saveOrUpdate(persitedCourier);
	}

	@Override
	public void disableCourier(Long id) {
		Courier persitedCourier = entityDao.findById(Courier.class, id);
		persitedCourier.setEnabled(false);
		entityDao.saveOrUpdate(persitedCourier);
	}

	@Override
	public Courier findCourierByid(Long id) {
		Courier courier = entityDao.findById(Courier.class, id);
		return courier;
	}

	@Override
	public List<Courier> getAllcourier() {
		List<Courier> courier = entityDao.findAll(Courier.class);
		return courier;
	}

	@Override
	public List<Courier> getEnabledCourier() {
		return entityDao.findAllEnabledObjects(Courier.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean checkName(String courierName) {
		EntityManager entityManager = entityDao.getEntityManager();
		Query query = entityManager.createQuery("Select courier.name from Courier courier where " +
		"courier.name = :name");
		query.setParameter("name", courierName);
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
	public boolean checkCode(String courierCode) {
		EntityManager entityManager = entityDao.getEntityManager();
		Query query = entityManager.createQuery("Select courier.code from Courier courier where " +
		"courier.code = :code");
		query.setParameter("code", courierCode);
		List<String> nameList = (List<String>)query.getResultList();
		if(nameList != null && nameList.size() > 0)
		{
			return false;	
		}
		else {
			return true;
		}
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Long getCourierIdByCode(String Code) {
		EntityManager entityManager = entityDao.getEntityManager();
		Query query = entityManager.createQuery("Select courier.id from Courier courier where " +
		"courier.code = :code");
		query.setParameter("code", Code);
		Long id;
		List idList = query.getResultList();
		if (!idList.isEmpty())
		   id = (Long) idList.get(0);
		else
			id = null;
			
		return id;
	}
//
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<Warehouse> getEnabledSDPlusWarehouses() {
//		EntityManager entityManager = entityDao.getEntityManager();
//		Query query = entityManager.createQuery("Select warehouse from Warehouse warehouse where " +
//		"warehouse.warehouseType = 'SD Plus'");
//		List<Warehouse> whList = query.getResultList();
//		return whList;
//	}

	
	
	
	
//	/** Check for the existing email in the database corresponding to a courier entity.**/
//	@SuppressWarnings("unchecked")
//	@Override
//	public boolean checkCourierByEmail(String email){
//		EntityManager entityManager = entityDao.getEntityManager();
//		Query query = entityManager.createQuery("Select courier from Courier courier where courier.primaryEmail = :primaryEmail");
//		query.setParameter("primaryEmail", email);
//		
//		List<Courier> courierList = (List<Courier>)query.getResultList();
//		
//		/** If exists return true else false. **/
//		if(courierList.size() > 0)
//			return true;
//		else
//			return false;
//	}

	/** Search courier by courier code in database. **/
	@SuppressWarnings("unchecked")
	@Override
	public Courier searchCourierByCode(String code){
		EntityManager entityManager = entityDao.getEntityManager();
		Query query = entityManager.createQuery("Select courier from Courier courier where courier.code = :code");
		query.setParameter("code", code);
		List<Courier> courierList = (List<Courier>)query.getResultList();
		
		if(courierList.isEmpty())
			return null;
		else
			return courierList.get(0);
	}

	/** Get all enabled courier code list from the database. **/
	@SuppressWarnings("unchecked")
	@Override
	public List<String> getAllEnabledCourierCode(){
		EntityManager entityManager = entityDao.getEntityManager();
		Query query = entityManager.createQuery("Select courier.code from Courier courier where courier.enabled =true");
		List<String> courierList = (List<String>)query.getResultList();
		return courierList;
	}
	
	/** Get all courier code list(both enabled and disabled) from the database. **/
	@SuppressWarnings("unchecked")
	@Override
	public List<String> getAllCourierCode(){
		EntityManager entityManager = entityDao.getEntityManager();
		Query query = entityManager.createQuery("Select courier.code from Courier courier");
		List<String> courierList = (List<String>)query.getResultList();
		return courierList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Courier> getCourierByType(String type) {
		// TODO Auto-generated method stub
		EntityManager entityManager = entityDao.getEntityManager();
		Query query = entityManager.createQuery("Select courier from Courier courier where courier.enabled =true and courier.type = :type ");
		query.setParameter("type", type);
		List<Courier> courierList = (List<Courier>)query.getResultList();
		return courierList;
		
		
	}




}
