package com.findme.repository;

import com.findme.model.Project;

/**
 * @author vinodkumara
 * 
 */
public interface IProjectDAO {

	Project getProjectDetails(int userId);

	void updateProjectDetails(Project project);

}
