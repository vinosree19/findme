package com.findme.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonAutoDetect;


/**
 * @author vinodkumara
 *
 */
@JsonAutoDetect
@Entity
@Table(name="user_t")
public class User implements Serializable{

	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String username;
	private String password;
	private String email;
	private Date lstUpdateDt;
	private int lstUpdateUser;
	/**
	 * @return the id
	 */
	@Id
	@GeneratedValue
	@Column(name="user_id")
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the username
	 */
	@Column(name="username", nullable=false)
	public String getUsername() {
		return username;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * @return the password
	 */
	@Column(name="password", nullable=false)
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the email
	 */
	@Column(name="email", nullable=false)
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the lstUpdateDt
	 */
	@Column(name="lst_update_dt", nullable=false)
	public Date getLstUpdateDt() {
		return lstUpdateDt;
	}
	/**
	 * @param date the lstUpdateDt to set
	 */
	public void setLstUpdateDt(Date date) {
		this.lstUpdateDt = date;
	}
	/**
	 * @return the lstUpdateUser
	 */
	@Column(name="lst_update_user", nullable=false)
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
