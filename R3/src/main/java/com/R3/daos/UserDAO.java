package com.R3.daos;

import java.util.List;

import javax.validation.UnexpectedTypeException;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.R3.beans.ReviewBean;
import com.R3.beans.UserBean;

public class UserDAO {

	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Transactional(isolation = Isolation.READ_COMMITTED,
					propagation = Propagation.REQUIRED,
					rollbackFor = Exception.class)
	public UserBean save(UserBean user){
		sessionFactory.getCurrentSession().save(user);
		return user;
	}
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public UserBean update(UserBean user) {
		sessionFactory.getCurrentSession().update(user);
		return user;

	}
	@Transactional(isolation = Isolation.READ_COMMITTED,
			propagation = Propagation.REQUIRED,
			rollbackFor = Exception.class)
	
	public void delete(UserBean user){
		sessionFactory.getCurrentSession().delete(user);
	}
	
	@Transactional(isolation = Isolation.READ_COMMITTED,
			propagation = Propagation.REQUIRED,
			rollbackFor = Exception.class)
	public UserBean findOne(UserBean user){
		return (UserBean) sessionFactory.getCurrentSession().createCriteria(UserBean.class)
			.add(Restrictions.eq("userId", user.getUserId()));
	}
	@Transactional(isolation = Isolation.READ_COMMITTED,
			propagation = Propagation.REQUIRED,
			rollbackFor = Exception.class)
	public UserBean findOneById(int id){
		return (UserBean) sessionFactory.getCurrentSession().load(UserBean.class, id);
	}
	@SuppressWarnings("unchecked")
	@Transactional(isolation = Isolation.READ_COMMITTED,
			propagation = Propagation.REQUIRED,
			rollbackFor = Exception.class)
	public List<UserBean> findAllUsers(){
																// .createQuery("FROM R3_USER"); same effect
			return (List<UserBean>) sessionFactory.getCurrentSession().createCriteria(UserBean.class).list(); 
	}
	@Transactional(isolation = Isolation.READ_COMMITTED,
			propagation = Propagation.REQUIRED,
			rollbackFor = Exception.class)
	public List<ReviewBean> getAllReviewsByUserId(Integer pk){
		UserBean bean = (UserBean) sessionFactory.getCurrentSession().load(UserBean.class, pk);
		List<ReviewBean> myList = bean.getReview();
		return myList;
	}
	@Transactional(isolation = Isolation.READ_COMMITTED,
			propagation = Propagation.REQUIRED)//,
			//rollbackFor = Exception.class)
	public UserBean authenticateUser(UserBean user){
		try{
			System.out.println("IN USERDAO LOGIN");
		UserBean myuser = (UserBean) sessionFactory.getCurrentSession().createCriteria(UserBean.class).add(Restrictions.eq("UserBean.username",user));//.uniqueResult();
		//UserBean myuser2 = myuser.setRole(user);
		return myuser;
		}catch(Exception e){
			return null;
		}
	}
}
