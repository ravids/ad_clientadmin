package com.ad.clientadmin.user.e2e.fixture;

import com.ad.core.user.domain.Person;

/**
 * Test fixture for unit tests.
 * 
 * @author RD
 */
public class EndToEndTestFixture {

	/**
	 * Creates a Person with id=1
	 */
	public Person createPerson() {
		Person person = new Person();
		person.setId(1);
		person.setUserName("ravi333");
		person.setFirstName("Ravi");
		person.setLastName("Silva");
		return person;
	}

}
