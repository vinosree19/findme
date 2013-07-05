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
@Table(name = "person_t")
public class Person implements Serializable {

	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	private int userid;

	private String firstname;

	private String middlename;

	private String lastname;

	private Date dob;

	private String fmhname;

	private String gender;

	private String marital;

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
	 * @param userid
	 *            the userid to set
	 */
	public void setUserid(int userid) {
		this.userid = userid;
	}

	/**
	 * @return the firstname
	 */
	@Column(name = "first_name", nullable = false)
	public String getFirstname() {
		return firstname;
	}

	/**
	 * @param firstname
	 *            the firstname to set
	 */
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	/**
	 * @return the middlename
	 */
	@Column(name = "middle_name")
	public String getMiddlename() {
		return middlename;
	}

	/**
	 * @param middlename
	 *            the middlename to set
	 */
	public void setMiddlename(String middlename) {
		this.middlename = middlename;
	}

	/**
	 * @return the lastname
	 */
	@Column(name = "last_name", nullable = false)
	public String getLastname() {
		return lastname;
	}

	/**
	 * @param lastname
	 *            the lastname to set
	 */
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	/**
	 * @return the dob
	 */
	@Column(name = "dob", nullable = false)
	public Date getDob() {
		return dob;
	}

	/**
	 * @param dob
	 *            the dob to set
	 */
	public void setDob(Date dob) {
		this.dob = dob;
	}

	/**
	 * @return the fmhname
	 */
	@Column(name = "fmh_name", nullable = false)
	public String getFmhname() {
		return fmhname;
	}

	/**
	 * @param fmhname
	 *            the fmhname to set
	 */
	public void setFmhname(String fmhname) {
		this.fmhname = fmhname;
	}

	/**
	 * @return the gender
	 */
	@Column(name = "gender", nullable = false)
	public String getGender() {
		return gender;
	}

	/**
	 * @param gender
	 *            the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * @return the marital
	 */
	@Column(name = "marital", nullable = false)
	public String getMarital() {
		return marital;
	}

	/**
	 * @param marital
	 *            the marital to set
	 */
	public void setMarital(String marital) {
		this.marital = marital;
	}

	/**
	 * @return the lstUpdateDt
	 */
	@Column(name = "lst_update_dt", nullable = false)
	public Date getLstUpdateDt() {
		return lstUpdateDt;
	}

	/**
	 * @param lstUpdateDt
	 *            the lstUpdateDt to set
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
	 * @param lstUpdateUser
	 *            the lstUpdateUser to set
	 */
	public void setLstUpdateUser(int lstUpdateUser) {
		this.lstUpdateUser = lstUpdateUser;
	}

}
