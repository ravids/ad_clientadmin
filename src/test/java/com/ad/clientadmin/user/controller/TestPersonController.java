package com.ad.clientadmin.user.controller;

import com.ad.clientadmin.user.dto.save.SaveDriverRequest;
import com.ad.framework.util.converter.JsonConverter;
import com.ad.clientadmin.user.controller.fixture.ControllerTestFixture;
import com.ad.clientadmin.user.springconfig.UserControllerTestConfig;
import com.ad.core.model.user.domain.User;
import com.ad.core.model.user.exception.UserNotFoundException;
import com.ad.core.model.user.service.UserService;
import com.ad.clientadmin.user.dto.UserDtoFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Unit tests the controller, including JSON serialization.
 * 
 * @author RD
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { UserControllerTestConfig.class })
public class TestPersonController {

    @Autowired private UserService mockUserService;
	@Autowired private UserDtoFactory dtoFactory;

	private MockMvc mockMvc;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(new PrimaryDriverController(mockUserService, dtoFactory)).build();
	}

	@Test
	public void test_getPersonById() throws Exception {
		ControllerTestFixture f = new ControllerTestFixture();
		User person = f.createTestUser();
		when(mockUserService.getUserById(anyInt())).thenReturn(person);

		mockMvc.perform(get("/uam/{id}", 1)
				.accept(JsonConverter.APPLICATION_JSON_UTF8)
				)
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id", is(1)))
				.andExpect(jsonPath("$.fullname", is(person.getFirstName() + " " + person.getLastName())))
				.andReturn();
	}

	@Test
	public void test_getPersonById_NotFound() throws Exception {
		final String errorMessage = "Mocking 404 message";
		when(mockUserService.getUserById(anyInt())).thenAnswer(new Answer<User>() {
			public User answer(InvocationOnMock invocation) throws Throwable {
				throw new UserNotFoundException(errorMessage);
			}
		});

		mockMvc.perform(get("/uam/{id}", -1)
				.accept(JsonConverter.APPLICATION_JSON_UTF8)
				)
				.andExpect(status().isNotFound())
				.andExpect(content().string(errorMessage))
				.andReturn();
	}


	@Test
	public void test_savePerson() throws Exception {
		ControllerTestFixture f = new ControllerTestFixture();
		User person = f.createTestUser();
		final Integer newId = person.getId();
		person.setId(null);
		
		doAnswer(new Answer<Object>() {
			public Object answer(InvocationOnMock invocation) throws Throwable {
				Object[] args = invocation.getArguments();
                User p = (User) args[0];
				p.setId(newId); // emulate the successful save populating the id
	            return "called with arguments: " + args;
			}
		}).when(mockUserService).saveUser((User) anyObject());

        SaveDriverRequest spr = new SaveDriverRequest();
		spr.setUserName(person.getUserName());
		spr.setFirstName(person.getFirstName());
		spr.setLastName(person.getLastName());
		
		mockMvc.perform(post("/uam")
				.contentType(MediaType.APPLICATION_JSON)
				.content(JsonConverter.convertObjectToJsonBytes(spr))
				.accept(JsonConverter.APPLICATION_JSON_UTF8)
				)
				.andExpect(status().isOk())
				.andExpect(content().string(newId.toString()))
				.andReturn();
	}
	
}

