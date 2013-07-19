package com.findme.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.findme.model.Person;
import com.findme.repository.IPersonDAO;

/**
 * @author vinodkumara
 * 
 */
@Repository
public class PersonDAO implements IPersonDAO {

	@Autowired
	private HibernateTemplate hibernateTemplate;

	@Autowired
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	@Override
	public void updatePersonDetails(Person person) {
		hibernateTemplate.saveOrUpdate(person);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Person getPersonDetails(int userId) {
		List<Person> persons = hibernateTemplate
				.findByCriteria(DetachedCriteria.forClass(Person.class).add(
						Restrictions.eq("userid", userId)));
		if (persons != null && persons.size() > 0) {
			return persons.get(0);
		}
		return null;
	}
}
