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
@Table(name = "experience_t")
public class Experience implements Serializable {

	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	private int userid;

	private int expSeq;

	private String company;

	private String designation;

	private Date fromdate;

	private Date todate;

	private String experience;

	private Date lstUpdateDt;

	private int lstUpdateUser;

	@Id
	@Column(name = "user_id", nullable = false)
	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	@Id
	@Column(name = "exp_seq", nullable = false)
	public int getExpSeq() {
		return expSeq;
	}

	public void setExpSeq(int expSeq) {
		this.expSeq = expSeq;
	}

	@Column(name = "company", nullable = false)
	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	@Column(name = "designation", nullable = false)
	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	@Column(name = "fromdate", nullable = false)
	public Date getFromdate() {
		return fromdate;
	}

	public void setFromdate(Date fromdate) {
		this.fromdate = fromdate;
	}

	@Column(name = "todate", nullable = false)
	public Date getTodate() {
		return todate;
	}

	public void setTodate(Date todate) {
		this.todate = todate;
	}

	@Column(name = "experience", nullable = false)
	public String getExperience() {
		return experience;
	}

	public void setExperience(String experience) {
		this.experience = experience;
	}

	@Column(name = "lst_update_dt", nullable = false)
	public Date getLstUpdateDt() {
		return lstUpdateDt;
	}

	public void setLstUpdateDt(Date lstUpdateDt) {
		this.lstUpdateDt = lstUpdateDt;
	}

	@Column(name = "lst_update_user", nullable = false)
	public int getLstUpdateUser() {
		return lstUpdateUser;
	}

	public void setLstUpdateUser(int lstUpdateUser) {
		this.lstUpdateUser = lstUpdateUser;
	}

}
