package com.findme.repository;

import java.util.List;

import com.findme.model.Development;

/**
 * @author vinodkumara
 * 
 */
public interface IDevelopmentDAO {

	List<Development> getDevelopmentDetails(int userId);

	Development updateDevelopmentDetails(Development development);

	void deleteDevelopmentDetails(int userId);
}
