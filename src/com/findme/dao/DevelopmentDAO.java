package com.findme.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.findme.model.Database;
import com.findme.model.Development;
import com.findme.repository.IDevelopmentDAO;

/**
 * @author vinodkumara
 * 
 */
@Repository
public class DevelopmentDAO implements IDevelopmentDAO {

	@Autowired
	private HibernateTemplate hibernateTemplate;

	@Autowired
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	@Override
	public Development updateDevelopmentDetails(Development development) {
		SessionFactory sessionFactory = hibernateTemplate.getSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		session.save(development);
		return development;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Development> getDevelopmentDetails(int userId) {
		SessionFactory sessionFactory = hibernateTemplate.getSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT {s.*} FROM development_t s WHERE user_id = :id";
		SQLQuery query = (SQLQuery) session.createSQLQuery(sql).setParameter(
				"id", userId);
		query.addEntity("s", Development.class);
		List<Development> list = query.list();
		return list;
	}

	@Override
	public void deleteDevelopmentDetails(int userId) {
		SessionFactory sessionFactory = hibernateTemplate.getSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createSQLQuery(
				"DELETE FROM development_t WHERE user_id = :id").setParameter(
				"id", userId);
		query.executeUpdate();
	}

}
