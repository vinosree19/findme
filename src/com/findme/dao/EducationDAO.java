package com.findme.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.findme.model.Education;
import com.findme.repository.IEducationDAO;

/**
 * @author vinodkumara
 * 
 */
@Repository
public class EducationDAO implements IEducationDAO {

	@Autowired
	private HibernateTemplate hibernateTemplate;

	@Autowired
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Education> getEducationDetails(int userId) {
		SessionFactory sessionFactory = hibernateTemplate.getSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT {s.*} FROM education_t s WHERE user_id = :id";
		SQLQuery query = (SQLQuery) session.createSQLQuery(sql).setParameter(
				"id", userId);
		query.addEntity("s", Education.class);
		List<Education> list = query.list();
		return list;
	}

	@Override
	public Education updateEducationDetails(Education education) {
		SessionFactory sessionFactory = hibernateTemplate.getSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		session.save(education);
		return education;
	}

	@Override
	public void deleteEducationDetails(int userId) {
		SessionFactory sessionFactory = hibernateTemplate.getSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createSQLQuery(
				"DELETE FROM education_t WHERE user_id = :id").setParameter(
				"id", userId);
		query.executeUpdate();
	}
}
