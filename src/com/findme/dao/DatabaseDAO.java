package com.findme.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.findme.model.Database;
import com.findme.repository.IDatabaseDAO;

/**
 * @author vinodkumara
 * 
 */
@Repository
public class DatabaseDAO implements IDatabaseDAO {

	@Autowired
	private HibernateTemplate hibernateTemplate;

	@Autowired
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	@Override
	public Database updateDatabaseDetails(Database databases) {
		SessionFactory sessionFactory = hibernateTemplate.getSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		session.save(databases);
		return databases;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Database> getDatabaseDetails(int userId) {
		SessionFactory sessionFactory = hibernateTemplate.getSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT {s.*} FROM database_t s WHERE user_id = :id";
		SQLQuery query = (SQLQuery) session.createSQLQuery(sql).setParameter(
				"id", userId);
		query.addEntity("s", Database.class);
		List<Database> list = query.list();
		return list;
	}

	@Override
	public void deleteDatabaseDetails(int userId) {
		SessionFactory sessionFactory = hibernateTemplate.getSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createSQLQuery(
				"DELETE FROM database_t WHERE user_id = :id").setParameter(
				"id", userId);
		query.executeUpdate();
	}
}
