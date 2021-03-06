package com.ad.clientadmin.user.controller;

import com.ad.clientadmin.user.dto.save.SaveDriverRequest;
import com.ad.core.model.company.service.CompanyService;
import com.ad.core.model.user.domain.User;
import com.ad.clientadmin.user.dto.UserDto;
import com.ad.core.model.user.exception.UserNotFoundException;
import com.ad.core.model.user.service.UserService;
import com.ad.clientadmin.user.dto.UserDtoFactory;
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
public class PrimaryDriverController {

    private UserService userService;
    private CompanyService companyService;
	private UserDtoFactory personDtoFactory;

	@Autowired
	public PrimaryDriverController(UserService userService, UserDtoFactory personDtoFactory) {
        this.userService = userService;
		this.personDtoFactory = personDtoFactory;
	}

	/**
	 * @param id
	 * @return Returns the person with the given id.
	 */
	@RequestMapping("uam/{id}")
	@ResponseBody
	public UserDto getPersonById(@PathVariable Integer id) {
		return personDtoFactory.createUser(userService.getUserById(id));
	}


	/**
	 * Creates a new person.
	 * @param request
	 * @return Returns the id for the new person.
	 */
	@RequestMapping(value = "uam", method = RequestMethod.POST)
	@ResponseBody
	public Integer createPerson(@RequestBody SaveDriverRequest request) {
        User person = new User();
		person.setFirstName(request.getFirstName());
		person.setLastName(request.getLastName());
		person.setUserName(request.getUserName());
		userService.saveUser(person);
        System.out.println("after save in controller : user id's = " + person.getId());
		return person.getId();
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
        return "No person found for id: -1";
    }
}
