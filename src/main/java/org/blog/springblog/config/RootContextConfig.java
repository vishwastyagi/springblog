package org.blog.springblog.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan("org.blog.springblog")
@EnableWebMvc
public class RootContextConfig {

	@Bean
	public static PropertyPlaceholderConfigurer properties() {
		PropertyPlaceholderConfigurer propertyPlaceholderConfigurer = new PropertyPlaceholderConfigurer();
		ClassPathResource[] resources = new ClassPathResource[] {
				new ClassPathResource("jdbc.properties"),
				new ClassPathResource("messages.properties")};
		propertyPlaceholderConfigurer.setLocations(resources);
		// propertyPlaceholderConfigurer.setIgnoreUnresolvablePlaceholders( true
		// );
		System.out.println("\n\nInside properties\n\n");
		return propertyPlaceholderConfigurer;
	}

	@Bean(name = "dataSource")
	public DataSource getDataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		System.out.println("\n\njdbcDriver= " + jdbcDriver + " \n jdbcUrl= "
				+ jdbcUrl + " \njdbcUsername= " + jdbcUsername
				+ "\n jdbcPassword" + jdbcPassword);
		dataSource.setDriverClassName(jdbcDriver);
		dataSource.setUrl(jdbcUrl);
		dataSource.setUsername(jdbcUsername);
		dataSource.setPassword(jdbcPassword);
		return dataSource;

	}

	@Value("${jdbc.driver}")
	private String jdbcDriver;

	@Value("${jdbc.url}")
	private String jdbcUrl;

	@Value("${jdbc.username}")
	private String jdbcUsername;

	@Value("${jdbc.password}")
	private String jdbcPassword;
}