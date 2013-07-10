package com.findme.web;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.findme.model.Address;
import com.findme.model.Experience;
import com.findme.model.Person;
import com.findme.model.Project;
import com.findme.model.User;
import com.findme.service.UserService;
import com.findme.util.JSONParseUtil;

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
	
	private JSONParseUtil util;

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
			List<Experience> experiences = service.getExperienceDetails(users.get(0).getId());
			for (Experience experience : experiences) {
				response.put("experience", experience);
			}
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
			List<Experience> experiences = service.getExperienceDetails(users.get(0).getId());
			for (Experience experience : experiences) {
				response.put("experience", experience);
			}
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
				service.updatePersonDetails(util.getPersonFromJSON(data.get("person")), user);
			}
			if (data.get("address") != null) {
				service.updateAddressDetails(util.getAddressFromJSON(data.get("address")), user);
			}
			if (data.get("permanentaddr") != null) {
				service.updateAddressDetails(util.getAddressFromJSON(data.get("permanentaddr")), user);
			}
			if (data.get("project") != null) {
				service.updateProjectDetails(util.getProjectFromJSON(data.get("project")), user);
			}
			if (data.get("experience") != null) {
				service.updateExperienceDetails(util.getExperienceFromJSON(data.get("experience")), user);
			}
			return true;
		}
		return false;
	}
}
