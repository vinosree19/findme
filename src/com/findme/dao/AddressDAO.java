package com.findme.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.findme.model.Address;
import com.findme.repository.IAddressDAO;

/**
 * @author vinodkumara
 * 
 */
@Repository
public class AddressDAO implements IAddressDAO {

	@Autowired
	private HibernateTemplate hibernateTemplate;

	@Autowired
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	@Override
	public void updateAddressDetails(Address address) {
		hibernateTemplate.saveOrUpdate(address);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Address> getAddressDetails(int userId) {
		return hibernateTemplate.findByCriteria(DetachedCriteria.forClass(
				Address.class).add(Restrictions.eq("userid", userId)));
	}

}
