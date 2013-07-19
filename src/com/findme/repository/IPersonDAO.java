package com.findme.repository;

import com.findme.model.Person;

/**
 * @author vinodkumara
 * 
 */
public interface IPersonDAO {

	Person getPersonDetails(int userId);

	void updatePersonDetails(Person person);

}
