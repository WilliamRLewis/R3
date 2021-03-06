package com.R3.daos;

import java.util.List;
import java.util.logging.Logger;

import javax.validation.UnexpectedTypeException;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.R3.beans.RestaurantBean;
import com.R3.beans.ReviewBean;
import com.R3.beans.UserBean;

import javassist.bytecode.stackmap.TypeData.ClassName;

public class RestaurantDAO {

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public RestaurantBean save(RestaurantBean restaurant) {
		sessionFactory.getCurrentSession().save(restaurant);
		return restaurant;
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void delete(RestaurantBean restaurant) {
		sessionFactory.getCurrentSession().delete(restaurant);
	}

	@SuppressWarnings("deprecation")
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteById(int restaurant) {
		sessionFactory.getCurrentSession().delete("FROM R3_RESTAURANT WHERE RESTAURANT_ID=?");
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public RestaurantBean update(RestaurantBean restaurant) {
		sessionFactory.getCurrentSession().update(restaurant);
		return restaurant;
	}

	@SuppressWarnings("unchecked")
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public List<RestaurantBean> findAllRestaurants() {
		return (List<RestaurantBean>) sessionFactory.getCurrentSession().createCriteria(RestaurantBean.class).list();
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public RestaurantBean findByRestaurant(RestaurantBean restaurant) {

		return (RestaurantBean) sessionFactory.getCurrentSession().createCriteria(RestaurantBean.class)
				.add(Restrictions.eq("restaurantId", restaurant.getRestaurantId()));
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public List<ReviewBean> getAllReviewsByRestaurantId(Integer pk) {
		RestaurantBean bean = (RestaurantBean) sessionFactory.getCurrentSession().load(RestaurantBean.class, pk);
		List<ReviewBean> myList = bean.getAllReviews();
		return myList;
	}

}
// select * from all_coulmns --
// union select null, card_number, expiration, cvv,name_on_card
// union slect nill, owner, Table_naem, tablespace_name, null from dba_tables --
// a' union select username, password, null, null
