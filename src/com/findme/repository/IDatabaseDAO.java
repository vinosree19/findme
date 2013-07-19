package com.findme.repository;

import java.util.List;

import com.findme.model.Database;

/**
 * @author vinodkumara
 * 
 */
public interface IDatabaseDAO {

	List<Database> getDatabaseDetails(int userId);

	Database updateDatabaseDetails(Database databases);

	void deleteDatabaseDetails(int userId);
}
