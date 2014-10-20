package com.ad.clientadmin.bootstrap;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Bootstrap for REST layer. It's important to isolate this for testability.
 * 
 * @author RD
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.ad.clientadmin")
public class MvcConfig extends WebMvcConfigurerAdapter {

}
