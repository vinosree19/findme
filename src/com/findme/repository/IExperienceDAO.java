package com.findme.repository;

import java.util.List;

import com.findme.model.Experience;

/**
 * @author vinodkumara
 *
 */
public interface IExperienceDAO {

	List<Experience> getExperienceDetails(int userId);
	
	void updateExperienceDetails(Experience experience);

}
