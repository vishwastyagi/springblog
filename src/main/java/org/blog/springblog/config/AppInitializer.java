package org.blog.springblog.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.blog.springblog.event.LoadDBProperties;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

public class AppInitializer implements WebApplicationInitializer {

	public void onStartup(ServletContext servletContext)
			throws ServletException {
		// Create the 'root' Spring application context
		System.out.println("Inside AppInitialzer");
		AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
		rootContext.register(RootContextConfig.class);
		rootContext.addApplicationListener(new LoadDBProperties());

		// Manage the lifecycle of the root application context
		servletContext.addListener(new ContextLoaderListener(rootContext));

		// Create the dispatcher servlet's Spring application context
		AnnotationConfigWebApplicationContext dispatcherContext = new AnnotationConfigWebApplicationContext();
		dispatcherContext.register(DispatcherContextConfig.class);

		// AnnotationConfigWebApplicationContext ctx = new
		// AnnotationConfigWebApplicationContext();
		// ctx.register(ITDMAppConfig.class);

		// ctx.setServletContext(servletContext);

		// Add Character Encoding Filter
		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
		characterEncodingFilter.setEncoding("UTF-8");
		characterEncodingFilter.setForceEncoding(true);

		servletContext.addFilter("CharacterEncodingFilter",
				characterEncodingFilter);

		dispatcherContext.setServletContext(servletContext);

		// dispatcherContext.setConfigLocation("com.techm.itdm");
		// rootContext.setConfigLocation("com.techm.itdm");

		// Register and map the dispatcher servlet
		ServletRegistration.Dynamic dispatcher = servletContext.addServlet(
				"DispatcherServlet", new DispatcherServlet(rootContext));
		dispatcher.addMapping("/");
		dispatcher.setLoadOnStartup(1);

		System.out.println("\n\n onStartup....\n\n");
		/*
		 * XmlWebApplicationContext appContext = new XmlWebApplicationContext();
		 * appContext
		 * .setConfigLocation("/WEB-INF/spring/dispatcher-config.xml");
		 */

	}

}
