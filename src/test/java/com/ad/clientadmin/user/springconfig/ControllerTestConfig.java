package com.ad.clientadmin.user.springconfig;

import com.ad.core.user.service.PersonService;
import com.ad.core.user.service.UserService;
import org.mockito.Mockito;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
@ComponentScan(basePackages = { "com.ad.clientadmin.user.dto" })
public class ControllerTestConfig {

	@Bean
	@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	public PersonService mockPersonService() {
		return Mockito.mock(PersonService.class);
	}

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public UserService mockUserService() {
        return Mockito.mock(UserService.class);
    }
}
