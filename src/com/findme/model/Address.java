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
@Table(name = "address_t")
public class Address implements Serializable {

	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	private int userid;

	private int addrSeq;

	private String address;

	private String state;

	private String district;

	private int pincode;

	private String phonenumber;

	private String mobilenumber;

	private Date lstUpdateDt;

	private int lstUpdateUser;
	
	private boolean addrind;

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
	 * @return the addrSeq
	 */
	@Id
	@Column(name = "addr_seq", nullable = false)
	public int getAddrSeq() {
		return addrSeq;
	}

	/**
	 * @param addrSeq
	 *            the addrSeq to set
	 */
	public void setAddrSeq(int addrSeq) {
		this.addrSeq = addrSeq;
	}

	/**
	 * @return the address
	 */
	@Column(name = "address", nullable = false)
	public String getAddress() {
		return address;
	}

	/**
	 * @param address
	 *            the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the state
	 */
	@Column(name = "state", nullable = false)
	public String getState() {
		return state;
	}

	/**
	 * @param state
	 *            the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the district
	 */
	@Column(name = "district", nullable = false)
	public String getDistrict() {
		return district;
	}

	/**
	 * @param district
	 *            the district to set
	 */
	public void setDistrict(String district) {
		this.district = district;
	}

	/**
	 * @return the pincode
	 */
	@Column(name = "pincode", nullable = false)
	public int getPincode() {
		return pincode;
	}

	/**
	 * @param pincode
	 *            the pincode to set
	 */
	public void setPincode(int pincode) {
		this.pincode = pincode;
	}

	/**
	 * @return the phonenumber
	 */
	@Column(name = "phone_number")
	public String getPhonenumber() {
		return phonenumber;
	}

	/**
	 * @param phonenumber
	 *            the phonenumber to set
	 */
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	/**
	 * @return the mobilenumber
	 */
	@Column(name = "mobile_number", nullable = false)
	public String getMobilenumber() {
		return mobilenumber;
	}

	/**
	 * @param mobilenumber
	 *            the mobilenumber to set
	 */
	public void setMobilenumber(String mobilenumber) {
		this.mobilenumber = mobilenumber;
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

	/**
	 * @return the addrind
	 */
	@Column(name = "addr_ind")
	public boolean isAddrind() {
		return addrind;
	}

	/**
	 * @param addrind the addrind to set
	 */
	public void setAddrind(boolean addrind) {
		this.addrind = addrind;
	}

}
