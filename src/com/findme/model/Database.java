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
@Table(name = "database_t")
public class Database implements Serializable {

	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	private int userid;

	private int dbSeq;

	private String technology;

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
	@Column(name = "db_seq", nullable = false)
	public int getDbSeq() {
		return dbSeq;
	}

	public void setDbSeq(int dbSeq) {
		this.dbSeq = dbSeq;
	}

	@Column(name = "technology", nullable = false)
	public String getTechnology() {
		return technology;
	}

	public void setTechnology(String technology) {
		this.technology = technology;
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
