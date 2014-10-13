package com.ad.clientadmin.user.bootstrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import java.util.Properties;


import javax.sql.DataSource;

/**
 * Database configuration. Note the 'Import' annotation in RootConfig that activates this. PropertySource and
 * Environment pair allows externalized settings.
 * 
 * @author Trey
 */
@Configuration
@EnableTransactionManagement
@PropertySource("jdbc.properties")
public class DatabaseConfig {

@Autowired
Environment env;

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan(new String[] { "com.ad.core.user.domain.*" });
        sessionFactory.setHibernateProperties(hibernateProperties());

        return sessionFactory;
    }

	@Bean
	public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("jdbc.driver"));
        dataSource.setUrl(env.getProperty("jdbc.url"));
        dataSource.setUsername(env.getProperty("jdbc.username"));
        dataSource.setPassword(env.getProperty("jdbc.password"));

       // dataSource.addScript("classpath:init.sql").build();

//		DataSource dataSource = new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.HSQL)
//				.addScript("classpath:init.sql").build();

        return dataSource;
	}

    @Bean
    public HibernateTransactionManager transactionManager() {
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(sessionFactory().getObject());

        return txManager;
    }
	
//	@Bean
//    public PlatformTransactionManager txManager() {
//        return new DataSourceTransactionManager(dataSource());
//    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }


    Properties hibernateProperties() {
        return new Properties() {
            {
                setProperty("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
                setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
            }
        };
    }

}
