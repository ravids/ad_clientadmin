package com.ad.clientadmin.user.springconfig;

import com.ad.core.model.user.service.UserService;
import org.mockito.Mockito;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
@ComponentScan(basePackages = { "com.ad.clientadmin.user.dto" })
public class UserControllerTestConfig {

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public UserService mockUserService() {
        return Mockito.mock(UserService.class);
    }
}
