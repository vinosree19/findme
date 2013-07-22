package com.findme.web;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.findme.model.Address;
import com.findme.model.Database;
import com.findme.model.Development;
import com.findme.model.Experience;
import com.findme.model.Person;
import com.findme.model.Project;
import com.findme.model.User;
import com.findme.service.UserService;
import com.findme.util.DataUtil;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.PageSize;
import com.lowagie.text.html.HtmlWriter;
import com.lowagie.text.pdf.PdfWriter;

@Controller
@RequestMapping
public class PdfController {

	@Autowired
	private UserService service;

	private DataUtil util = new DataUtil();

	@RequestMapping(value = "/export.pdf", method = RequestMethod.GET)
	public @ResponseBody
	void export(ModelMap model, HttpServletResponse response,
			HttpServletRequest request, OutputStream outputStream)
			throws DocumentException, IOException {
		User user = service.getUserDetails(Integer.parseInt(request
				.getParameter("userid")));
		model.addAttribute("user", user);
		Person person = (Person) service.getPersonDetails(user.getId());
		model.addAttribute("person", person);
		for (Address address : service.getAddressDetails(user.getId())) {
			if (address.getAddrSeq() == 1) {
				model.addAttribute("address", address);
			} else {
				model.addAttribute("address1", address);
			}

		}
		Project project = service.getProjectDetails(user.getId());
		model.addAttribute("project", project);
		for (Experience experience : service.getExperienceDetails(user.getId())) {
			model.addAttribute("experience", experience);
		}
		List<Database> databases = service.getDatabaseDetails(user.getId());
		model.addAttribute("databases", databases);
		List<Development> developments = service.getDevelopmentDetails(user
				.getId());
		model.addAttribute("developments", developments);
		Document document = new Document(PageSize.A4, 50, 50, 50, 50);
		try {
			HtmlWriter.getInstance(document, System.out);
			PdfWriter.getInstance(document, response.getOutputStream());
			response.setContentType("application/pdf");
			response.setHeader("Content-Disposition",
					"attachment; filename=report.pdf");
			document.addAuthor("Vinodkumar");
			document.addSubject("About the Employee");
			document.addTitle("Details");
			document.open();
			util.createPdfData(document, model);
		} catch (DocumentException de) {
			System.err.println(de.getMessage());
		} finally {
			document.close();
		}
	}
}
