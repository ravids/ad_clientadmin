package com.ad.clientadmin.company.e2e.springconfig;

import com.ad.core.model.company.service.CompanyService;
import org.mockito.Mockito;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
@ComponentScan(basePackages = { "com.ad.clientadmin.company.dto" })
public class CompanyControllerTestConfig {

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public CompanyService mockCompanyService() {
        return Mockito.mock(CompanyService.class);
    }
}
