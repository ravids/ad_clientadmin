package com.ad.clientadmin.user.controller.fixture;

import com.ad.core.user.domain.Person;

/**
 * Test fixture for unit tests.
 * 
 * @author Trey
 */
public class ControllerTestFixture {

	/**
	 * Creates a Person with id=1, userName=thoward333, firstName=Trey, lastName=Howard
	 * @return
	 */
	public Person createTestPerson() {
		Person person = new Person();
		person.setId(1);
		person.setUserName("r233");
		person.setFirstName("ravi");
		person.setLastName("silva");
		return person;
	}

}
