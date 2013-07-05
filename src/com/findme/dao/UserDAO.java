package com.findme.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.findme.model.User;
import com.findme.repository.IUserDAO;

/**
 * @author vinodkumara
 *
 */
@Repository
public class UserDAO implements IUserDAO{

	@Autowired
	private HibernateTemplate hibernateTemplate;

	@Autowired
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getUsers() {
		return hibernateTemplate.find("from user_t");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> checkUser(User user) {
		return hibernateTemplate.findByCriteria(
		        DetachedCriteria.forClass(User.class)
		        .add(Restrictions.eq("username", user.getUsername())));
	}

	@Override
	public void updateUser(User user) {
		hibernateTemplate.saveOrUpdate(user);
	}

}
