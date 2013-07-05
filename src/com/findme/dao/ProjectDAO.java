package com.findme.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.findme.model.Project;
import com.findme.repository.IProjectDAO;

/**
 * @author vinodkumara
 * 
 */
@Repository
public class ProjectDAO implements IProjectDAO {

	@Autowired
	private HibernateTemplate hibernateTemplate;

	@Autowired
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Project getProjectDetails(int userId) {
		List<Project> projects = hibernateTemplate.findByCriteria(DetachedCriteria.forClass(Project.class).add(
				Restrictions.eq("userid", userId)));
		if (projects != null && projects.size() > 0) {
			return projects.get(0);
		}
		return null;
	}

	@Override
	public void updateProjectDetails(Project project) {
		hibernateTemplate.saveOrUpdate(project);
	}

}
