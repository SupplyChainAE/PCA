package com.snapdeal.service;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.hibernate.Hibernate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import com.snapdeal.dao.EntityDao;
import com.snapdeal.entity.Roles;
import com.snapdeal.entity.User;

@Named("userService")
@Transactional
public class UserServiceImpl implements UserService{

	@Inject
	@Named("entityDao")
	EntityDao entityDao;

	@SuppressWarnings("unchecked")
	public User getUserByUserName(String userName){
		User dbUser = null;
		if(userName != null && !userName.isEmpty())
		{
			EntityManager entityManager = entityDao.getEntityManager();
			Query query = entityManager.createQuery("Select user from User user where user.userName = :username");
			query.setParameter("username", userName);
			List<User> userList = (List<User>)query.getResultList();
			if(userList != null && userList.size() > 0)
			{
				dbUser = userList.get(0);
				Hibernate.initialize(dbUser.getUserRoles());	
			}
			
		}
		return dbUser;
	}

	@Override
	public void saveOrUpdateUser(User user) {
		if(user.getId() == null)
		{
			user.setPassword(getHashedPassword(user.getPassword()));	
		}
		
		entityDao.saveOrUpdate(user);
	}

	private String getHashedPassword(String password){
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();  
		String hashedPassword = passwordEncoder.encode(password);
		return hashedPassword;  
	}

	@Override
	public List<Roles> getAllRoles() {
		List<Roles> userRoles = entityDao.findAll(Roles.class);
		return userRoles;
	}

	public void saveOrUpdateRole(Roles role)
	{
		entityDao.saveOrUpdate(role);
	}

	@Override
	public User findUserById(Long id) {
		User user = entityDao.findById(User.class, id);
//		Hibernate.initialize(user.getUserRoles());
		return user;
	}

	/*@Override
	public void updateUser(User user) {
		user.onUpdate();
		entityDao.update(user);
	}
	 */
	@Override
	public void enableUser(Long id) {
		User user = entityDao.findById(User.class, id);
		user.setEnabled(true);
		entityDao.saveOrUpdate(user);
	}

	@Override
	public void disableUser(Long id) {
		User user = entityDao.findById(User.class, id);
		user.setEnabled(false);
		entityDao.saveOrUpdate(user);
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean checkUser(String userName) {
		EntityManager entityManager = entityDao.getEntityManager();
		Query query = entityManager.createQuery("Select user.userName from User user where user.userName = :userName");
		query.setParameter("userName", userName);
		List<String> name = (List<String>) query.getResultList();
		if(name.size() > 0)
		{
			return false;	
		}
		else
		{
			return true;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getUsersExceptLoggedIn(String userName) {
		EntityManager entityManager = entityDao.getEntityManager();
		Query query = entityManager.createQuery("Select user from User user where user.userName <> :username");
		query.setParameter("username", userName);
		List<User> userList = (List<User>)query.getResultList();
		return userList;
	}
}
