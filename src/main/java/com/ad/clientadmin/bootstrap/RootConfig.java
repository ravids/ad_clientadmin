package com.ad.clientadmin.bootstrap;

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
//@ComponentScan(basePackages = { "com.ad.core", "com.ad.clientadmin" })
@ComponentScan(basePackages = { "com.ad.core.model.user", "com.ad.core.model.company","com.ad.core.dao.sql.*", "com.ad.clientadmin.*.dto" })
public class RootConfig {

}
