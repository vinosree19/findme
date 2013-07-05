package com.findme.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonAutoDetect;

/**
 * @author vinodkumara
 *
 */
@JsonAutoDetect
@Entity
@Table(name = "project_t")
public class Project implements Serializable {

	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	private int userid;

	private String project;

	private String designation;

	private String role;

	private String projectdesc;

	private Date lstUpdateDt;

	private int lstUpdateUser;

	/**
	 * @return the userid
	 */
	@Id
	@Column(name = "user_id", nullable = false)
	public int getUserid() {
		return userid;
	}

	/**
	 * @param userid the userid to set
	 */
	public void setUserid(int userid) {
		this.userid = userid;
	}

	/**
	 * @return the project
	 */
	@Column(name = "project", nullable = false)
	public String getProject() {
		return project;
	}

	/**
	 * @param project the project to set
	 */
	public void setProject(String project) {
		this.project = project;
	}

	/**
	 * @return the designation
	 */
	@Column(name = "designation", nullable = false)
	public String getDesignation() {
		return designation;
	}

	/**
	 * @param designation the designation to set
	 */
	public void setDesignation(String designation) {
		this.designation = designation;
	}

	/**
	 * @return the role
	 */
	@Column(name = "role", nullable = false)
	public String getRole() {
		return role;
	}

	/**
	 * @param role the role to set
	 */
	public void setRole(String role) {
		this.role = role;
	}

	/**
	 * @return the project_desc
	 */
	@Column(name = "project_desc", nullable = false)
	public String getProjectdesc() {
		return projectdesc;
	}

	/**
	 * @param project_desc the project_desc to set
	 */
	public void setProjectdesc(String projectdesc) {
		this.projectdesc = projectdesc;
	}

	/**
	 * @return the lstUpdateDt
	 */
	@Column(name = "lst_update_dt", nullable = false)
	public Date getLstUpdateDt() {
		return lstUpdateDt;
	}

	/**
	 * @param lstUpdateDt the lstUpdateDt to set
	 */
	public void setLstUpdateDt(Date lstUpdateDt) {
		this.lstUpdateDt = lstUpdateDt;
	}

	/**
	 * @return the lstUpdateUser
	 */
	@Column(name = "lst_update_user", nullable = false)
	public int getLstUpdateUser() {
		return lstUpdateUser;
	}

	/**
	 * @param lstUpdateUser the lstUpdateUser to set
	 */
	public void setLstUpdateUser(int lstUpdateUser) {
		this.lstUpdateUser = lstUpdateUser;
	}

}
