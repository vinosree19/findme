package com.findme.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.findme.model.Experience;
import com.findme.repository.IExperienceDAO;

/**
 * @author vinodkumara
 * 
 */
@Repository
public class ExperienceDAO implements IExperienceDAO {

	@Autowired
	private HibernateTemplate hibernateTemplate;

	@Autowired
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Experience> getExperienceDetails(int userId) {
		return hibernateTemplate.findByCriteria(DetachedCriteria.forClass(
				Experience.class).add(Restrictions.eq("userid", userId)));
	}

	@Override
	public void updateExperienceDetails(Experience experience) {
		hibernateTemplate.saveOrUpdate(experience);
	}

}
