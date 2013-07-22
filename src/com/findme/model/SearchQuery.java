package com.findme.model;

/**
 * @author vinodkumara
 * 
 */
public class SearchQuery {

	private String user;

	private String project;

	private String designation;

	private String[] database;

	private String[] development;

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String[] getDatabase() {
		return database;
	}

	public void setDatabase(String[] database) {
		this.database = database;
	}

	public String[] getDevelopment() {
		return development;
	}

	public void setDevelopment(String[] development) {
		this.development = development;
	}

}
