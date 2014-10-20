package com.ad.clientadmin.company.e2e;

import com.ad.clientadmin.bootstrap.RootConfig;
import com.ad.clientadmin.company.controller.CompanyController;
import com.ad.clientadmin.company.controller.fixture.ControllerTestFixture;
import com.ad.clientadmin.company.dto.CompanyDtoFactory;
import com.ad.core.model.company.domain.Company;
import com.ad.core.model.user.domain.User;
import com.ad.core.model.company.service.CompanyService;
import com.ad.framework.util.converter.JsonConverter;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { RootConfig.class })
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class })
@DatabaseSetup("companyData.xml")
public class TestEndToEnd {

    @Autowired private CompanyService companyService;
	@Autowired private CompanyDtoFactory dtoFactory;
	
	private MockMvc mockMvc;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(new CompanyController(companyService, dtoFactory)).build();
	}

	@Test
	public void test_getCompanyById() throws Exception {
		ControllerTestFixture f = new ControllerTestFixture();
        Company company = f.createTestCompany();

		mockMvc.perform(get("/company/{id}", 1)
				.accept(JsonConverter.APPLICATION_JSON_UTF8)
				)
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id", is(1)))
				.andExpect(jsonPath("$.name", is(company.getName())))
				.andReturn();
	}

	@Test
	public void test_getCompanyById_NotFound() throws Exception {
		Integer badId = -1;
		mockMvc.perform(get("/company/{id}", badId)
				.accept(JsonConverter.APPLICATION_JSON_UTF8)
				)
				.andExpect(status().isNotFound())
				.andExpect(content().string("No company found for id: " + badId))
				.andReturn();
	}


	/**
     * complications with generated keys
     *
     * Until i figure out how to reset the auto increment, I am commenting out this test
     * The test verifies the new ID of the POST. Though DB unit clears the rows, it does not reset
     * the autoincrement.
     */
//	@Test
//	public void test_savePerson() throws Exception {
//		ControllerTestFixture f = new ControllerTestFixture();
//		Person person = f.createTestPerson();
//		person.setId(null);
//
//		SavePersonRequest spr = new SavePersonRequest();
//		spr.setUserName(person.getUserName());
//		spr.setFirstName(person.getFirstName());
//		spr.setLastName(person.getLastName());
//
//		mockMvc.perform(post("/uam")
//				.contentType(MediaType.APPLICATION_JSON)
//				.content(TestUtil.convertObjectToJsonBytes(spr))
//				.accept(TestUtil.APPLICATION_JSON_UTF8)
//				)
//				.andExpect(status().isOk())
//				.andExpect(content().string("4")) // init script creates 3 records
//				.andReturn();
//	}

}
