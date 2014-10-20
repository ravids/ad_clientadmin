package com.ad.clientadmin.user.controller.fixture;

import com.ad.core.model.user.domain.User;

/**
 * Test fixture for unit tests.
 * 
 * @author RD
 */
public class ControllerTestFixture {

    /**
     * Creates a Person with id=1
     * @return
     */
    public User createTestUser() {
        User person = new User();
        person.setId(1);
        person.setUserName("r233");
        person.setFirstName("ravi");
        person.setLastName("silva");
        return person;
    }
}
