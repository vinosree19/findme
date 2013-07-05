package com.findme.web;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.findme.model.Address;
import com.findme.model.Person;
import com.findme.model.Project;
import com.findme.model.User;
import com.findme.service.UserService;

/**
 * @author vinodkumara
 * 
 */
@Controller
@RequestMapping
@SessionAttributes({ "user" })
public class UserController {

	@Autowired
	private UserService service;

	@RequestMapping(value = "/login.action", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, Object> login(HttpServletRequest request, @RequestBody User data) {
		Map<String, Object> response = new HashMap<String, Object>();
		List<User> users = service.getAuthentication(data);
		if (users != null && users.size() > 0 && users.get(0).getUsername().equals(data.getUsername())
				&& users.get(0).getPassword().equals(data.getPassword())) {
			request.getSession().setAttribute("user", users.get(0));
			Person person = service.getPersonDetails(users.get(0).getId());
			response.put("person", person);
			for (Address address : service.getAddressDetails(users.get(0).getId())) {
				if (address.getAddrSeq() == 1) {
					response.put("address", address);
				}
				if (address.getAddrSeq() == 2) {
					response.put("permanentaddr", address);
				}
			}
			response.put("user", users.get(0));
			Project project = service.getProjectDetails(users.get(0).getId());
			response.put("project", project);
			response.put("msg", true);
			return response;
		}
		response.put("msg", false);
		return response;
	}

	@RequestMapping(value = "/refresh.action", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, Object> refresh(HttpServletRequest request, @RequestBody User data) {
		Map<String, Object> response = new HashMap<String, Object>();
		List<User> users = service.getAuthentication(data);
		if (users != null && users.size() > 0) {
			request.getSession().setAttribute("user", users.get(0));
			Person person = service.getPersonDetails(users.get(0).getId());
			response.put("person", person);
			for (Address address : service.getAddressDetails(users.get(0).getId())) {
				if (address.getAddrSeq() == 1) {
					response.put("address", address);
				}
				if (address.getAddrSeq() == 2) {
					response.put("permanentaddr", address);
				}
			}
			Project project = service.getProjectDetails(users.get(0).getId());
			response.put("project", project);
			response.put("user", users.get(0));
			response.put("msg", true);
			return response;
		}
		response.put("msg", false);
		return response;
	}

	@RequestMapping(value = "/register.action", method = RequestMethod.POST)
	public @ResponseBody
	boolean registration(@RequestBody User data) {
		List<User> users = service.getAuthentication(data);
		if (users != null && users.size() > 0 && users.get(0).getUsername().equals(data.getUsername())) {
			return false;
		} else {
			service.updateUser(data);
			return true;
		}
	}

	@RequestMapping(value = "/save.action", method = RequestMethod.POST)
	public @ResponseBody
	boolean save(HttpServletRequest request, @RequestBody HashMap<String, Object> data) throws ParseException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if (data != null) {
			if (data.get("email") != null) {
				user.setEmail((String) data.get("email"));
				service.updateUser(user);
			}
			if (data.get("person") != null) {
				service.updatePersonDetails(getPersonFromJSON(data.get("person")), user);
			}
			if (data.get("address") != null) {
				service.updateAddressDetails(getAddressFromJSON(data.get("address")), user);
			}
			if (data.get("permanentaddr") != null) {
				service.updateAddressDetails(getAddressFromJSON(data.get("permanentaddr")), user);
			}
			if (data.get("project") != null) {
				service.updateProjectDetails(getProjectFromJSON(data.get("project")), user);
			}
			return true;
		}
		return false;
	}

	private Person getPersonFromJSON(Object data) throws ParseException {
		Person person = new Person();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
		JSONObject jsonObject = JSONObject.fromObject(data);
		person.setFirstname(jsonObject.getString("firstname"));
		person.setMiddlename(jsonObject.getString("middlename"));
		person.setLastname(jsonObject.getString("lastname"));
		person.setFmhname(jsonObject.getString("fmhname"));
		person.setGender(jsonObject.getString("gender"));
		person.setMarital(jsonObject.getString("marital"));
		// Person person = (Person) JSONObject.toBean(jsonObject, Person.class);
		if (person.getMiddlename().equals("") || person.getMiddlename().equals(null)) {
			person.setMiddlename("(null)");
		}
		Calendar today = Calendar.getInstance();
		today.setTime(formatter.parse(jsonObject.getString("dob")));
		// today.add(Calendar.DATE, 1);
		person.setDob(today.getTime());
		return person;
	}

	private Address getAddressFromJSON(Object data) {
		JSONObject jsonObject = JSONObject.fromObject(data);
		Address address = (Address) JSONObject.toBean(jsonObject, Address.class);
		if (address.getMobilenumber().equals("") || address.getMobilenumber().equals(null)) {
			address.setMobilenumber("(null)");
		}
		return address;
	}

	private Project getProjectFromJSON(Object data) {
		JSONObject jsonObject = JSONObject.fromObject(data);
		Project project = (Project) JSONObject.toBean(jsonObject, Project.class);
		return project;
	}
}
