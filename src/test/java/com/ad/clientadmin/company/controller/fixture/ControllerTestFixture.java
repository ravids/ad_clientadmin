package com.ad.clientadmin.company.controller.fixture;

import com.ad.core.model.company.domain.Company;

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
    public Company createTestCompany() {
        Company company = new Company();
        company.setId(1);
        company.setName("testcmp");
        return company;
    }
}
