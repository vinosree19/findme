package com.findme.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import net.sf.json.JSONObject;

import com.findme.model.Address;
import com.findme.model.Experience;
import com.findme.model.Person;
import com.findme.model.Project;

/**
 * @author vinodkumara
 * 
 */
public class JSONParseUtil {

	public Person getPersonFromJSON(Object data) throws ParseException {
		Person person = new Person();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
		JSONObject jsonObject = JSONObject.fromObject(data);
		person.setFirstname(jsonObject.getString("firstname"));
		person.setMiddlename(jsonObject.getString("middlename"));
		person.setLastname(jsonObject.getString("lastname"));
		person.setFmhname(jsonObject.getString("fmhname"));
		person.setGender(jsonObject.getString("gender"));
		person.setMarital(jsonObject.getString("marital"));
		if (person.getMiddlename().equals("")
				|| person.getMiddlename().equals(null)) {
			person.setMiddlename("(null)");
		}
		Calendar today = Calendar.getInstance();
		today.setTime(formatter.parse(jsonObject.getString("dob")));
		person.setDob(today.getTime());
		return person;
	}

	public Address getAddressFromJSON(Object data) {
		JSONObject jsonObject = JSONObject.fromObject(data);
		Address address = (Address) JSONObject
				.toBean(jsonObject, Address.class);
		if (address.getMobilenumber().equals("")
				|| address.getMobilenumber().equals(null)) {
			address.setMobilenumber("(null)");
		}
		return address;
	}

	public Project getProjectFromJSON(Object data) {
		JSONObject jsonObject = JSONObject.fromObject(data);
		Project project = (Project) JSONObject
				.toBean(jsonObject, Project.class);
		return project;
	}

	public Experience getExperienceFromJSON(Object data) throws ParseException {
		Experience experience = new Experience();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
		JSONObject jsonObject = JSONObject.fromObject(data);
		Calendar from = Calendar.getInstance();
		from.setTime(formatter.parse(jsonObject.getString("fromdate")));
		Calendar to = Calendar.getInstance();
		to.setTime(formatter.parse(jsonObject.getString("todate")));
		experience.setCompany(jsonObject.getString("company"));
		experience.setExpSeq(new Integer(jsonObject.getString("expSeq"))
				.intValue());
		experience.setDesignation(jsonObject.getString("designation"));
		experience.setFromdate(from.getTime());
		experience.setTodate(to.getTime());
		experience.setExperience(jsonObject.getString("experience"));
		return experience;
	}

}
