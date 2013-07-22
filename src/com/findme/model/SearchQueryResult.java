package com.findme.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author vinodkumara
 * 
 */
@Entity
public class SearchQueryResult {

	private int userid;

	private String username;

	private String fullname;

	private String project;

	@Id
	@Column(name = "userid", nullable = false)
	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	@Column(name = "username", nullable = false)
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "fullname", nullable = false)
	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	@Column(name = "project", nullable = false)
	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

}
