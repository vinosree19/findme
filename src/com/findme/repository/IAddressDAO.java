package com.findme.repository;

import java.util.List;

import com.findme.model.Address;

/**
 * @author vinodkumara
 *
 */
public interface IAddressDAO {
	
	List<Address> getAddressDetails(int userId);
	
	void updateAddressDetails(Address address);

}
