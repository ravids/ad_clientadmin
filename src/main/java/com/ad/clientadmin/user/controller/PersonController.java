package com.ad.clientadmin.user.controller;

import com.ad.core.user.domain.Person;
import com.ad.core.user.dto.PersonDto;
import com.ad.core.user.dto.save.SavePersonRequest;
import com.ad.core.user.exception.PersonNotFoundException;
import com.ad.core.user.service.PersonService;
import com.ad.core.user.service.UserService;
import com.ad.core.user.util.DtoFactory;
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
public class PersonController {

	private PersonService personService;
    private UserService userService;
	private DtoFactory personDtoFactory;

	@Autowired
	public PersonController(PersonService personService, UserService userService, DtoFactory personDtoFactory) {
		this.personService = personService;
        this.userService = userService;
		this.personDtoFactory = personDtoFactory;
	}

	/**
	 * @param id
	 * @return Returns the person with the given id.
	 */
	@RequestMapping("uam/{id}")
	@ResponseBody
	public PersonDto getPersonById(@PathVariable Integer id) {
		return personDtoFactory.createUser(userService.getPersonById(id));
	}

	/**
	 * Same as above method, just showing different URL mapping
	 * @param id
	 * @return Returns the person with the given id.
	 */
	@RequestMapping(value = "uam", params = "id")
	@ResponseBody
	public PersonDto getPersonByIdFromParam(@RequestParam Integer id) {
		return personDtoFactory.createPerson(personService.getPersonById(id));
	}

	/**
	 * Creates a new person.
	 * @param request
	 * @return Returns the id for the new person.
	 */
	@RequestMapping(value = "uam", method = RequestMethod.POST)
	@ResponseBody
	public Integer createPerson(@RequestBody SavePersonRequest request) {
		Person person = new Person();
		person.setFirstName(request.getFirstName());
		person.setLastName(request.getLastName());
		person.setUserName(request.getUserName());
		personService.savePerson(person);
		return person.getId();
	}
	
	// --- Error handlers
	
	@ExceptionHandler(PersonNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ResponseBody
	public String handlePersonNotFoundException(PersonNotFoundException e) {
		return e.getMessage();
	}

    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public String handleUserNotFoundException(NullPointerException e) {
        return "No person found for id: -1";
    }
}
