package com.ad.clientadmin.user.bootstrap;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Bootstrap for service layer.
 * 
 * @author RD
 */
@Configuration
@Import(DatabaseConfig.class)
@ComponentScan(basePackages = { "com.ad.core.user.service", "com.ad.core.user.dao", "com.ad.core.user.util" })
public class RootConfig {

}
