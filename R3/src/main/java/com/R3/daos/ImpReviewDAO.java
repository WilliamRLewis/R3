package com.R3.daos;


import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.R3.beans.ReviewBean;
import com.R3.beans.UserBean;


public class ImpReviewDAO {
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public ReviewBean create(ReviewBean review) {
		sessionFactory.getCurrentSession().save(review);
		return review;

	}
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public ReviewBean update(ReviewBean review) {
		sessionFactory.getCurrentSession().update(review);
		return review;

	}
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void delete(ReviewBean review) {
		sessionFactory.getCurrentSession().delete(review);

	}
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public ReviewBean find(ReviewBean review) {
		return (ReviewBean) sessionFactory.getCurrentSession().createCriteria(ReviewBean.class)
				.add(Restrictions.eq("id", review.getId()));
	}
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public ReviewBean findById(int pk) {
		ReviewBean bean = (ReviewBean) sessionFactory.getCurrentSession().load(ReviewBean.class, pk);
		return bean;
	}
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@SuppressWarnings("unchecked")
	public List<ReviewBean> findAll() {
		return (List<ReviewBean>) sessionFactory.getCurrentSession().createCriteria(ReviewBean.class).list();
	}
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public UserBean getOwner(ReviewBean review) {
		ReviewBean myReview = (ReviewBean) sessionFactory.getCurrentSession().load(ReviewBean.class, review.getId());
			return myReview.getUser();
	}

	
}
