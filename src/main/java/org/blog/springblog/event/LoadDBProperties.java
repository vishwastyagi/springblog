package org.blog.springblog.event;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.beans.factory.BeanCreationException;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.jdbc.core.JdbcTemplate;

public class LoadDBProperties implements
		ApplicationListener<ContextRefreshedEvent> {

	private DataSource dataSource;

	private JdbcTemplate jdbcTemplate;

	@Resource(name = "dataSource")
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@PostConstruct
	public void afterPropertiesSet() throws Exception {
		System.out.println("afterPropertiesSet of LoadDBProperties");
		if (getDataSource() == null)
			throw new BeanCreationException(
					"Must set datasource to LoadDBProperties");

		if (getJdbcTemplate() == null)
			throw new BeanCreationException(
					"Fail to create jdbcTemplate in LoadDBProperties");
	}

	public void onApplicationEvent(ContextRefreshedEvent event) {
		System.out.println("application context= "
				+ event.getApplicationContext());		
	}

}
