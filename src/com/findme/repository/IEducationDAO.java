package com.findme.repository;

import java.util.List;

import com.findme.model.Education;

/**
 * @author vinodkumara
 * 
 */
public interface IEducationDAO {

	List<Education> getEducationDetails(int userId);

	Education updateEducationDetails(Education education);

	void deleteEducationDetails(int userId);

}
