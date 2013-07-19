package com.findme.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.findme.dao.AddressDAO;
import com.findme.dao.DatabaseDAO;
import com.findme.dao.DevelopmentDAO;
import com.findme.dao.ExperienceDAO;
import com.findme.dao.PersonDAO;
import com.findme.dao.ProjectDAO;
import com.findme.dao.UserDAO;
import com.findme.model.Address;
import com.findme.model.Database;
import com.findme.model.Development;
import com.findme.model.Experience;
import com.findme.model.Person;
import com.findme.model.Project;
import com.findme.model.User;

/**
 * @author vinodkumara
 * 
 */
@Service
public class UserService {

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private PersonDAO personDAO;

	@Autowired
	private AddressDAO addressDAO;

	@Autowired
	private ProjectDAO projectDAO;

	@Autowired
	private ExperienceDAO experienceDAO;

	@Autowired
	private DatabaseDAO databaseDAO;

	@Autowired
	private DevelopmentDAO developmentDAO;

	@Autowired
	private HibernateTemplate hibernateTemplate;

	@Autowired
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	@Transactional
	public List<User> getAuthentication(User data) {
		return userDAO.checkUser(data);
	}

	@Transactional
	public void updateUser(User data) {
		data.setLstUpdateDt(new Date());
		data.setLstUpdateUser(1000);
		userDAO.updateUser(data);
	}

	@Transactional
	public void updatePersonDetails(Person person, User user) {
		person.setUserid(user.getId());
		person.setLstUpdateDt(new Date());
		person.setLstUpdateUser(user.getId());
		personDAO.updatePersonDetails(person);
	}

	@Transactional
	public Person getPersonDetails(int userId) {
		return personDAO.getPersonDetails(userId);
	}

	@Transactional
	public void updateAddressDetails(Address address, User user) {
		address.setUserid(user.getId());
		address.setLstUpdateDt(new Date());
		address.setLstUpdateUser(user.getId());
		addressDAO.updateAddressDetails(address);
	}

	@Transactional
	public List<Address> getAddressDetails(int userId) {
		return addressDAO.getAddressDetails(userId);
	}

	@Transactional
	public void updateProjectDetails(Project project, User user) {
		project.setUserid(user.getId());
		project.setLstUpdateDt(new Date());
		project.setLstUpdateUser(user.getId());
		projectDAO.updateProjectDetails(project);
	}

	@Transactional
	public Project getProjectDetails(int userId) {
		return projectDAO.getProjectDetails(userId);
	}

	@Transactional
	public void updateExperienceDetails(Experience experience, User user) {
		experience.setUserid(user.getId());
		experience.setLstUpdateDt(new Date());
		experience.setLstUpdateUser(user.getId());
		experienceDAO.updateExperienceDetails(experience);
	}

	@Transactional
	public List<Experience> getExperienceDetails(int userId) {
		return experienceDAO.getExperienceDetails(userId);
	}

	@Transactional
	public Database updateDatabaseDetails(String string, User user, int index) {
		Database database = new Database();
		database.setUserid(user.getId());
		database.setDbSeq(index);
		database.setTechnology(string);
		database.setLstUpdateDt(new Date());
		database.setLstUpdateUser(user.getId());
		return databaseDAO.updateDatabaseDetails(database);
	}

	@Transactional
	public List<Database> getDatabaseDetails(int userId) {
		return databaseDAO.getDatabaseDetails(userId);
	}

	@Transactional
	public void deleteDatabaseDetails(int userId) {
		databaseDAO.deleteDatabaseDetails(userId);
	}

	@Transactional
	public Development updateDevelopmentDetails(String string, User user,
			int index) {
		Development development = new Development();
		development.setUserid(user.getId());
		development.setDevSeq(index);
		development.setTechnology(string);
		development.setLstUpdateDt(new Date());
		development.setLstUpdateUser(user.getId());
		return developmentDAO.updateDevelopmentDetails(development);
	}

	@Transactional
	public List<Development> getDevelopmentDetails(int userId) {
		return developmentDAO.getDevelopmentDetails(userId);
	}

	@Transactional
	public void deleteDevelopmentDetails(int userId) {
		developmentDAO.deleteDevelopmentDetails(userId);
	}
}
