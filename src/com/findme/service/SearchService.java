package com.findme.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.findme.model.SearchQuery;
import com.findme.model.SearchQueryResult;

/**
 * @author vinodkumara
 * 
 */
@Service
public class SearchService {

	@Autowired
	private HibernateTemplate hibernateTemplate;

	@Autowired
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<SearchQueryResult> searchQuery(SearchQuery data) {
		String sql = dynamicQuery();
		String where = formWhereCondition(data);
		List<SearchQueryResult> list = null;
		SessionFactory sessionFactory = hibernateTemplate.getSessionFactory();
		Session session = sessionFactory.getCurrentSession();

		SQLQuery query = (SQLQuery) session.createSQLQuery(sql + where);
		query.addEntity(SearchQueryResult.class);
		list = (List<SearchQueryResult>) query.list();
		return list;
	}

	private String dynamicQuery() {
		String sql = "SELECT distinct ut.user_id AS userid,"
				+ " ut.username AS username,"
				+ " CONCAT(pst.last_name, CONCAT(', ', pst.first_name)) AS fullname,"
				+ " pt.project AS project" + " FROM user_t ut"
				+ " INNER JOIN project_t pt" + " ON ut.user_id = pt.user_id"
				+ " INNER JOIN person_t pst" + " ON pt.user_id = pst.user_id"
				+ " INNER JOIN database_t dbt"
				+ " ON pst.user_id = dbt.user_id"
				+ " INNER JOIN development_t devt"
				+ " ON dbt.user_id = devt.user_id" + " WHERE ";
		return sql;
	}

	private String formWhereCondition(SearchQuery data) {
		String where = null;
		List<String> dbs = new ArrayList<String>();
		List<String> devs = new ArrayList<String>();
		for (String str : data.getDatabase()) {
			dbs.add('"' + str + '"');
		}
		for (String str : data.getDevelopment()) {
			devs.add('"' + str + '"');
		}
		if (data.getUser() != null && where == null) {
			where = " ut.username LIKE '%" + data.getUser() + "%'";
		}
		if (data.getProject() != null && data.getProject() != "") {
			if (where != null) {
				where = where + " AND pt.project LIKE '%" + data.getProject()
						+ "%'";
			} else {
				where = " pt.project LIKE '%" + data.getProject() + "%'";
			}
		}
		if (data.getDesignation() != null && data.getDesignation() != "") {
			if (where != null) {
				where = where + " AND pt.designation LIKE '%"
						+ data.getDesignation() + "%'";
			} else {
				where = " pt.designation LIKE '%" + data.getDesignation()
						+ "%'";
			}
		}
		if (dbs.size() > 0) {
			if (where != null) {
				where = where
						+ " AND dbt.technology IN ("
						+ dbs.toString().replaceAll("\\[", "")
								.replaceAll("\\]", "") + ")";
			} else {
				where = " dbt.technology IN ("
						+ dbs.toString().replaceAll("\\[", "")
								.replaceAll("\\]", "") + ")";
			}
		}
		if (devs.size() > 0) {
			if (where != null) {
				where = where
						+ " AND devt.technology IN ("
						+ devs.toString().replaceAll("\\[", "")
								.replaceAll("\\]", "") + ")";
			} else {
				where = " devt.technology IN ("
						+ devs.toString().replaceAll("\\[", "")
								.replaceAll("\\]", "") + ")";
			}
		}
		return where;
	}
}
