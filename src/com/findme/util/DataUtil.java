package com.findme.util;

import java.awt.Color;
import java.util.Calendar;
import java.util.Date;

import org.springframework.ui.ModelMap;

import com.findme.model.Address;
import com.findme.model.Database;
import com.findme.model.Development;
import com.findme.model.Experience;
import com.findme.model.Person;
import com.findme.model.Project;
import com.findme.model.User;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.List;
import com.lowagie.text.ListItem;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;

/**
 * @author vinodkumara
 * 
 */
public class DataUtil {

	private static final String[] month_names = new String[] { "Jan", "Feb",
			"Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov",
			"Dec" };

	public Document createPdfData(Document document, ModelMap map)
			throws DocumentException {
		User user = (User) map.get("user");
		if (user != null) {
			createUserData(document, user);
		}
		Person person = (Person) map.get("person");
		if (person != null) {
			createPersonData(document, person);
		}
		Address address = (Address) map.get("address");
		if (address != null) {
			createAddressData(document, address);
		}
		Address address1 = (Address) map.get("address1");
		if (address1 != null) {
			createPermanentAddressData(document, address1);
		}
		Experience experience = (Experience) map.get("experience");
		if (experience != null) {
			createExperienceData(document, experience);
		}
		Project project = (Project) map.get("project");
		if (project != null) {
			createProjectData(document, project);
		}
		createDatabaseData(document, map);
		createDevelopmentData(document, map);
		return document;
	}

	private String covertDate(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return month_names[cal.get(Calendar.MONTH)] + " "
				+ cal.get(Calendar.DATE) + "," + cal.get(Calendar.YEAR);
	}

	private Document createUserData(Document document, User user)
			throws DocumentException {
		Paragraph userpar = new Paragraph();
		userpar.add(user.getUsername());
		userpar.setAlignment(Element.ALIGN_RIGHT);
		userpar.font().setStyle(Font.BOLD);
		document.add(userpar);

		Paragraph mailpar = new Paragraph();
		mailpar.add(user.getEmail());
		mailpar.setAlignment(Element.ALIGN_RIGHT);
		mailpar.font().setStyle(Font.UNDERLINE);
		mailpar.font().setColor(Color.BLUE);
		document.add(mailpar);
		return document;
	}

	private Document createPersonData(Document document, Person person)
			throws DocumentException {
		String name;
		Paragraph personHeader = new Paragraph(
				"Employee Personal Information:", new Font(Font.BOLD));
		personHeader.setSpacingBefore(15);
		personHeader.font().setStyle(Font.BOLD);
		personHeader.font().setStyle(Font.UNDERLINE);
		document.add(personHeader);

		PdfPTable personTable = new PdfPTable(2);
		personTable.setWidthPercentage(75);
		personTable.setSpacingBefore(20);

		Paragraph fnamepar = new Paragraph();
		fnamepar.add(person.getFirstname());
		fnamepar.setAlignment(Element.ALIGN_LEFT);
		personTable.addCell("Firstname");
		personTable.addCell(fnamepar);

		if (person.getMiddlename().equals("(null)")) {
			name = "N/A";
		} else {
			name = person.getMiddlename();
		}
		Paragraph mnamepar = new Paragraph();
		mnamepar.add(name);
		mnamepar.setAlignment(Element.ALIGN_LEFT);
		personTable.addCell("Middlename");
		personTable.addCell(mnamepar);

		Paragraph lnamepar = new Paragraph();
		lnamepar.add(person.getLastname());
		lnamepar.setAlignment(Element.ALIGN_LEFT);
		personTable.addCell("Lastname");
		personTable.addCell(lnamepar);

		Paragraph dobpar = new Paragraph();
		dobpar.add(covertDate(person.getDob()));
		dobpar.setAlignment(Element.ALIGN_LEFT);
		personTable.addCell("Date of Birth");
		personTable.addCell(dobpar);

		Paragraph fmhnamepar = new Paragraph();
		fmhnamepar.add(person.getFmhname());
		fmhnamepar.setAlignment(Element.ALIGN_LEFT);
		personTable.addCell("Father/Mother/Husband Name");
		personTable.addCell(fmhnamepar);

		Paragraph genderpar = new Paragraph();
		genderpar.add(person.getGender());
		genderpar.setAlignment(Element.ALIGN_LEFT);
		personTable.addCell("Gender");
		personTable.addCell(genderpar);

		Paragraph maritalpar = new Paragraph();
		maritalpar.add(person.getMarital());
		maritalpar.setAlignment(Element.ALIGN_LEFT);
		personTable.addCell("Marital Status");
		personTable.addCell(maritalpar);

		document.add(personTable);
		return document;
	}

	private Document createAddressData(Document document, Address address)
			throws DocumentException {
		Paragraph addressHeader = new Paragraph("Present Address Detail:",
				new Font(Font.BOLD));
		addressHeader.setSpacingBefore(15);
		addressHeader.font().setStyle(Font.BOLD);
		addressHeader.font().setStyle(Font.UNDERLINE);
		document.add(addressHeader);

		PdfPTable addrTable = new PdfPTable(2);
		addrTable.setWidthPercentage(75);
		addrTable.setSpacingBefore(20);

		Paragraph addrpar = new Paragraph();
		addrpar.add(address.getAddress());
		addrpar.setAlignment(Element.ALIGN_LEFT);
		addrTable.addCell("Present Address");
		addrTable.addCell(addrpar);

		Paragraph statepar = new Paragraph();
		statepar.add(address.getState());
		statepar.setAlignment(Element.ALIGN_LEFT);
		addrTable.addCell("State");
		addrTable.addCell(statepar);

		Paragraph districtpar = new Paragraph();
		districtpar.add(address.getDistrict());
		districtpar.setAlignment(Element.ALIGN_LEFT);
		addrTable.addCell("District");
		addrTable.addCell(districtpar);

		Paragraph pincodepar = new Paragraph();
		pincodepar.add(new Integer(address.getPincode()).toString());
		pincodepar.setAlignment(Element.ALIGN_LEFT);
		addrTable.addCell("Pincode");
		addrTable.addCell(pincodepar);

		Paragraph phonenumberpar = new Paragraph();
		phonenumberpar.add(address.getPhonenumber());
		phonenumberpar.setAlignment(Element.ALIGN_LEFT);
		addrTable.addCell("PhoneNumber");
		addrTable.addCell(phonenumberpar);

		Paragraph mobilenumberpar = new Paragraph();
		mobilenumberpar.add(address.getMobilenumber());
		mobilenumberpar.setAlignment(Element.ALIGN_LEFT);
		addrTable.addCell("MobileNumber");
		addrTable.addCell(mobilenumberpar);

		document.add(addrTable);
		return document;
	}

	private Document createPermanentAddressData(Document document,
			Address address) throws DocumentException {
		Paragraph addressHeader = new Paragraph("Permanent Address Detail:",
				new Font(Font.BOLD));
		addressHeader.setSpacingBefore(15);
		addressHeader.font().setStyle(Font.BOLD);
		addressHeader.font().setStyle(Font.UNDERLINE);
		document.add(addressHeader);

		PdfPTable addrTable = new PdfPTable(2);
		addrTable.setWidthPercentage(75);
		addrTable.setSpacingBefore(20);

		Paragraph addrpar = new Paragraph();
		addrpar.add(address.getAddress());
		addrpar.setAlignment(Element.ALIGN_LEFT);
		addrTable.addCell("Permanent Address");
		addrTable.addCell(addrpar);

		Paragraph statepar = new Paragraph();
		statepar.add(address.getState());
		statepar.setAlignment(Element.ALIGN_LEFT);
		addrTable.addCell("State");
		addrTable.addCell(statepar);

		Paragraph districtpar = new Paragraph();
		districtpar.add(address.getDistrict());
		districtpar.setAlignment(Element.ALIGN_LEFT);
		addrTable.addCell("District");
		addrTable.addCell(districtpar);

		Paragraph pincodepar = new Paragraph();
		pincodepar.add(new Integer(address.getPincode()).toString());
		pincodepar.setAlignment(Element.ALIGN_LEFT);
		addrTable.addCell("Pincode");
		addrTable.addCell(pincodepar);

		Paragraph phonenumberpar = new Paragraph();
		phonenumberpar.add(address.getPhonenumber());
		phonenumberpar.setAlignment(Element.ALIGN_LEFT);
		addrTable.addCell("PhoneNumber");
		addrTable.addCell(phonenumberpar);

		document.add(addrTable);
		return document;
	}

	private Document createExperienceData(Document document,
			Experience experience) throws DocumentException {
		Paragraph addressHeader = new Paragraph("Experience Detail:", new Font(
				Font.BOLD));
		addressHeader.setSpacingBefore(15);
		addressHeader.font().setStyle(Font.BOLD);
		addressHeader.font().setStyle(Font.UNDERLINE);
		document.add(addressHeader);

		PdfPTable expTable = new PdfPTable(2);
		expTable.setWidthPercentage(75);
		expTable.setSpacingBefore(20);

		Paragraph companypar = new Paragraph();
		companypar.add(experience.getCompany());
		companypar.setAlignment(Element.ALIGN_LEFT);
		expTable.addCell("Company");
		expTable.addCell(companypar);

		Paragraph designationpar = new Paragraph();
		designationpar.add(experience.getDesignation());
		designationpar.setAlignment(Element.ALIGN_LEFT);
		expTable.addCell("Designation");
		expTable.addCell(designationpar);

		Paragraph fromdatepar = new Paragraph();
		fromdatepar.add(covertDate(experience.getFromdate()));
		fromdatepar.setAlignment(Element.ALIGN_LEFT);
		expTable.addCell("From Date");
		expTable.addCell(fromdatepar);

		Paragraph todatepar = new Paragraph();
		todatepar.add(covertDate(experience.getTodate()));
		todatepar.setAlignment(Element.ALIGN_LEFT);
		expTable.addCell("To Date");
		expTable.addCell(todatepar);

		Paragraph experiencepar = new Paragraph();
		experiencepar.add(experience.getExperience());
		experiencepar.setAlignment(Element.ALIGN_LEFT);
		expTable.addCell("Experience");
		expTable.addCell(experiencepar);

		document.add(expTable);
		return document;
	}

	private Document createProjectData(Document document, Project project)
			throws DocumentException {
		Paragraph addressHeader = new Paragraph("Project Detail:", new Font(
				Font.BOLD));
		addressHeader.setSpacingBefore(15);
		addressHeader.font().setStyle(Font.BOLD);
		addressHeader.font().setStyle(Font.UNDERLINE);
		document.add(addressHeader);

		PdfPTable projectTable = new PdfPTable(2);
		projectTable.setWidthPercentage(75);
		projectTable.setSpacingBefore(20);

		Paragraph projectpar = new Paragraph();
		projectpar.add(project.getProject());
		projectpar.setAlignment(Element.ALIGN_LEFT);
		projectTable.addCell("Project");
		projectTable.addCell(projectpar);

		Paragraph designationpar = new Paragraph();
		designationpar.add(project.getDesignation());
		designationpar.setAlignment(Element.ALIGN_LEFT);
		projectTable.addCell("Designation");
		projectTable.addCell(designationpar);

		document.add(projectTable);

		Paragraph descheadpar = new Paragraph("Project Description:", new Font(
				Font.ITALIC));
		descheadpar.setSpacingBefore(15);
		descheadpar.font().setStyle(Font.UNDERLINE);
		descheadpar.setAlignment(Element.ALIGN_LEFT);
		document.add(descheadpar);

		Paragraph projectdescpar = new Paragraph();
		projectdescpar.setSpacingBefore(10);
		projectdescpar.add(project.getProjectdesc());
		projectdescpar.setAlignment(Element.ALIGN_JUSTIFIED);
		document.add(projectdescpar);

		Paragraph roleheadpar = new Paragraph("Role in Project:", new Font(
				Font.ITALIC));
		roleheadpar.setSpacingBefore(15);
		roleheadpar.font().setStyle(Font.UNDERLINE);
		roleheadpar.setAlignment(Element.ALIGN_LEFT);
		document.add(roleheadpar);

		Paragraph rolepar = new Paragraph();
		rolepar.setSpacingBefore(10);
		rolepar.add(project.getRole());
		rolepar.setAlignment(Element.ALIGN_JUSTIFIED);
		document.add(rolepar);

		return document;
	}

	@SuppressWarnings("unchecked")
	private Document createDatabaseData(Document document, ModelMap map)
			throws DocumentException {
		java.util.List<Database> databases = (java.util.List<Database>) map
				.get("databases");
		if (databases != null && databases.size() > 0) {
			Paragraph databaseHeader = new Paragraph("Database Details:",
					new Font(Font.BOLD));
			databaseHeader.setSpacingBefore(15);
			databaseHeader.setSpacingAfter(15);
			databaseHeader.font().setStyle(Font.BOLD);
			databaseHeader.font().setStyle(Font.UNDERLINE);
			document.add(databaseHeader);

			List list = new List(true, 50);
			for (Database database : databases) {
				list.add(new ListItem(database.getTechnology()));
			}
			document.add(list);
		}
		return document;
	}

	@SuppressWarnings("unchecked")
	private Document createDevelopmentData(Document document, ModelMap map)
			throws DocumentException {
		java.util.List<Development> developments = (java.util.List<Development>) map
				.get("developments");
		if (developments != null && developments.size() > 0) {
			Paragraph developmentHeader = new Paragraph("Development Details:",
					new Font(Font.BOLD));
			developmentHeader.setSpacingBefore(15);
			developmentHeader.setSpacingAfter(15);
			developmentHeader.font().setStyle(Font.BOLD);
			developmentHeader.font().setStyle(Font.UNDERLINE);
			document.add(developmentHeader);

			List list = new List(true, 50);
			for (Development development : developments) {
				list.add(new ListItem(development.getTechnology()));
			}
			document.add(list);
		}
		return document;
	}
}
