package com.ad.clientadmin.company.controller;

import com.ad.clientadmin.company.dto.CompanyDto;
import com.ad.clientadmin.company.dto.CompanyDtoFactory;
import com.ad.clientadmin.company.dto.save.SaveCompanyRequest;
import com.ad.core.model.company.domain.Company;
import com.ad.core.model.company.service.CompanyService;
import com.ad.clientadmin.user.dto.UserDto;
import com.ad.core.model.user.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * REST layer for managing people.
 * 
 * @author Adapted from http://codetutr.com/2013/04/09/spring-mvc-easy-rest-based-json-services-with-responsebody/
 */
@Controller
public class CompanyController {

    private CompanyService companyService;
	private CompanyDtoFactory companyDtoFactory;

	@Autowired
	public CompanyController(CompanyService companyService, CompanyDtoFactory personDtoFactory) {
        this.companyService = companyService;
		this.companyDtoFactory = personDtoFactory;
	}

	/**
	 * @param id
	 * @return Returns the person with the given id.
	 */
	@RequestMapping("company/{id}")
	@ResponseBody
	public CompanyDto getPersonById(@PathVariable Integer id) {
		return companyDtoFactory.createCompany(companyService.getCompanyById(id));
	}


	/**
	 * Creates a new person.
	 * @param request
	 * @return Returns the id for the new person.
	 */
	@RequestMapping(value = "company", method = RequestMethod.POST)
	@ResponseBody
	public Integer createPerson(@RequestBody SaveCompanyRequest request) {
        Company company = new Company();
        company.setName(request.getCompanyName());
        companyService.saveCompany(company);
        System.out.println("after save in controller : company id's = " + company.getId());
		return company.getId();
	}
	
	// --- Error handlers
	
	@ExceptionHandler(UserNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ResponseBody
	public String handlePersonNotFoundException(UserNotFoundException e) {
		return e.getMessage();
	}

    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public String handleUserNotFoundException(NullPointerException e) {
        return "No company found for id: -1";
    }
}
