package com.findme.web;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.findme.model.Address;
import com.findme.model.Database;
import com.findme.model.Development;
import com.findme.model.Education;
import com.findme.model.Experience;
import com.findme.model.Person;
import com.findme.model.Project;
import com.findme.model.SearchQuery;
import com.findme.model.SearchQueryResult;
import com.findme.model.User;
import com.findme.service.SearchService;
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
	private UserService service = new UserService();

	@Autowired
	private SearchService searchService = new SearchService();

	private JSONParseUtil util = new JSONParseUtil();

	@RequestMapping(value = "/login.action", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, Object> login(HttpServletRequest request, @RequestBody User data) {
		List<Database> database = new ArrayList<Database>();
		List<Development> development = new ArrayList<Development>();
		List<Education> education = new ArrayList<Education>();
		Map<String, Object> response = new HashMap<String, Object>();
		List<User> users = service.getAuthentication(data);
		if (users != null && users.size() > 0
				&& users.get(0).getUsername().equals(data.getUsername())
				&& users.get(0).getPassword().equals(data.getPassword())) {
			request.getSession().setAttribute("user", users.get(0));
			Person person = service.getPersonDetails(users.get(0).getId());
			response.put("person", person);
			for (Address address : service.getAddressDetails(users.get(0)
					.getId())) {
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
			List<Experience> experiences = service.getExperienceDetails(users
					.get(0).getId());
			for (Experience experience : experiences) {
				response.put("experience", experience);
			}
			List<Database> databases = service.getDatabaseDetails(users.get(0)
					.getId());
			for (Database db : databases) {
				database.add(db);
			}
			response.put("database", database);
			List<Development> developments = service
					.getDevelopmentDetails(users.get(0).getId());
			for (Development dev : developments) {
				development.add(dev);
			}
			response.put("development", development);
			List<Education> educations = service.getEducationDetails(users.get(
					0).getId());
			for (Education edu : educations) {
				education.add(edu);
			}
			response.put("education", education);
			response.put("msg", true);
			return response;
		}
		response.put("msg", false);
		return response;
	}

	@RequestMapping(value = "/refresh.action", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, Object> refresh(HttpServletRequest request,
			@RequestBody User data) {
		List<Database> database = new ArrayList<Database>();
		List<Development> development = new ArrayList<Development>();
		List<Education> education = new ArrayList<Education>();
		Map<String, Object> response = new HashMap<String, Object>();
		List<User> users = service.getAuthentication(data);
		if (users != null && users.size() > 0) {
			request.getSession().setAttribute("user", users.get(0));
			Person person = service.getPersonDetails(users.get(0).getId());
			response.put("person", person);
			for (Address address : service.getAddressDetails(users.get(0)
					.getId())) {
				if (address.getAddrSeq() == 1) {
					response.put("address", address);
				}
				if (address.getAddrSeq() == 2) {
					response.put("permanentaddr", address);
				}
			}
			Project project = service.getProjectDetails(users.get(0).getId());
			response.put("project", project);
			List<Experience> experiences = service.getExperienceDetails(users
					.get(0).getId());
			for (Experience experience : experiences) {
				response.put("experience", experience);
			}
			List<Database> databases = service.getDatabaseDetails(users.get(0)
					.getId());
			for (Database db : databases) {
				database.add(db);
			}
			response.put("database", database);
			List<Development> developments = service
					.getDevelopmentDetails(users.get(0).getId());
			for (Development dev : developments) {
				development.add(dev);
			}
			List<Education> educations = service.getEducationDetails(users.get(
					0).getId());
			for (Education edu : educations) {
				education.add(edu);
			}
			response.put("education", education);
			response.put("development", development);
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
		if (users != null && users.size() > 0
				&& users.get(0).getUsername().equals(data.getUsername())) {
			return false;
		} else {
			service.updateUser(data);
			return true;
		}
	}

	@RequestMapping(value = "/save.action", method = RequestMethod.POST)
	public @ResponseBody
	boolean save(HttpServletRequest request,
			@RequestBody HashMap<String, Object> data) throws ParseException {
		int index = 0;
		int devIndex = 0;
		int eduIndex = 0;
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if (data != null) {
			if (data.get("email") != null) {
				user.setEmail((String) data.get("email"));
				service.updateUser(user);
			}
			if (data.get("person") != null) {
				service.updatePersonDetails(
						util.getPersonFromJSON(data.get("person")), user);
			}
			if (data.get("address") != null) {
				service.updateAddressDetails(
						util.getAddressFromJSON(data.get("address")), user);
			}
			if (data.get("permanentaddr") != null) {
				service.updateAddressDetails(
						util.getAddressFromJSON(data.get("permanentaddr")),
						user);
			}
			if (data.get("project") != null) {
				service.updateProjectDetails(
						util.getProjectFromJSON(data.get("project")), user);
			}
			if (data.get("experience") != null) {
				service.updateExperienceDetails(
						util.getExperienceFromJSON(data.get("experience")),
						user);
			}
			if (data.get("database") != null) {
				service.deleteDatabaseDetails(user.getId());
				JSONObject jsonObject = (JSONObject) JSONSerializer.toJSON(data
						.get("database"));
				JSONArray list = (JSONArray) jsonObject
						.getJSONArray("databases");
				for (int i = 0; i < list.size(); i++) {
					JSONObject object = list.getJSONObject(i);
					service.updateDatabaseDetails(object.get("technology")
							.toString(), user, ++index);
				}
			}
			if (data.get("development") != null) {
				service.deleteDevelopmentDetails(user.getId());
				JSONObject jsonObject = (JSONObject) JSONSerializer.toJSON(data
						.get("development"));
				JSONArray list = (JSONArray) jsonObject
						.getJSONArray("developments");
				for (int i = 0; i < list.size(); i++) {
					JSONObject object = list.getJSONObject(i);
					service.updateDevelopmentDetails(object.get("technology")
							.toString(), user, ++devIndex);
				}
			}
			if (data.get("education") != null) {
				service.deleteEducationDetails(user.getId());
				JSONObject jsonObject = (JSONObject) JSONSerializer.toJSON(data
						.get("education"));
				JSONArray list = (JSONArray) jsonObject
						.getJSONArray("educations");
				for (int i = 0; i < list.size(); i++) {
					JSONObject object = list.getJSONObject(i);
					service.updateEducationDetails(
							util.getEducationFromJSON(object), user, ++eduIndex);
				}
			}
			return true;
		}
		return false;
	}

	@RequestMapping(value = "/search.action", method = RequestMethod.POST)
	public @ResponseBody
	List<SearchQueryResult> search(@RequestBody SearchQuery data) {
		return searchService.searchQuery(data);
	}
}
