package org.blog.springblog.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan("org.blog.springblog")
@EnableWebMvc
public class RootContextConfig {

	@Bean
	public static PropertyPlaceholderConfigurer properties() {
		System.out.println("Inside PropertyPlaceholderConfigurer properties");// third this will be called
		PropertyPlaceholderConfigurer propertyPlaceholderConfigurer = new PropertyPlaceholderConfigurer();
		ClassPathResource[] resources = new ClassPathResource[] {
				new ClassPathResource("jdbc.properties"),
				new ClassPathResource("messages.properties")};
		propertyPlaceholderConfigurer.setLocations(resources);
		// propertyPlaceholderConfigurer.setIgnoreUnresolvablePlaceholders( true
		// );
		//System.out.println("\n\nInside properties\n\n");
		return propertyPlaceholderConfigurer;
	}

	@Bean(name = "dataSource")
	public DataSource getDataSource() {
		System.out.println("Inside getDataSource"); // eight this will be called
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
	
	@Bean(name="jdbcTemplate")
	public JdbcTemplate createJdbcTemplate(DataSource dataSource){
		System.out.println("Inside JdbcTemplate");
		JdbcTemplate jdbcTemplate= new JdbcTemplate(dataSource);
		System.out.println(jdbcTemplate.getDataSource()+"  "+getDataSource());
		return new JdbcTemplate(dataSource);
	}
	/*@Override
	  public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
	        argumentResolvers.add(resolver());
	  }

	  @Bean
	  public ServletWebArgumentResolverAdapter resolver() {
	      return new ServletWebArgumentResolverAdapter(pageable());
	  }


	  @Bean
	  public PageableArgumentResolver pageable() {
	      return new PageableArgumentResolver();
	  }

*/
	
	

	@Value("${jdbc.driver}")
	private String jdbcDriver;

	@Value("${jdbc.url}")
	private String jdbcUrl;

	@Value("${jdbc.username}")
	private String jdbcUsername;

	@Value("${jdbc.password}")
	private String jdbcPassword;
}