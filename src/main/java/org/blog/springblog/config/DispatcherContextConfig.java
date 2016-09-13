package org.blog.springblog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
// @ComponentScan("org.blog.springblog")
@EnableWebMvc
// <mvc:annotation-driven />
public class DispatcherContextConfig extends WebMvcConfigurerAdapter {

	private static final String VIEW_DIR_HTML = "/WEB-INF/static/";
	private static final String VIEW_EXTN_HTML = ".html";

	private static final String VIEW_DIR_JSP = "/WEB-INF/";
	private static final String VIEW_EXTN_JSP = ".jsp";

	/*
	 * @Bean public UrlBasedViewResolver setupViewResolver() {
	 * System.out.println("\n\nsetupViewResolver\n\n"); UrlBasedViewResolver
	 * resolver = new UrlBasedViewResolver();
	 * resolver.setPrefix("/WEB-INF/pages/"); resolver.setSuffix(".xhtml");
	 * resolver.setViewClass(JstlView.class); return resolver; }
	 */

	/*
	 * or
	 */
	
	
	/** 
	 * to return html page to spring 
	 * @return instance of viewresolver
	 */
	@Bean
    public ViewResolver htmlViewResolver() {
		System.out.println("htmlViewResolver method");
        InternalResourceViewResolver viewResolver= new ChainableInternalResourceViewResolver();
        viewResolver.setPrefix(VIEW_DIR_HTML);
        viewResolver.setSuffix(VIEW_EXTN_HTML);
        viewResolver.setOrder(0);
        return viewResolver;
    }
	
	/** 
	 * to return jsp page to spring 
	 * @return instance of viewresolver
	 */
	@Bean
    public ViewResolver jspViewResolver() {
		System.out.println(" jspViewResolver method ");
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix(VIEW_DIR_JSP);
        viewResolver.setSuffix(VIEW_EXTN_JSP);
        viewResolver.setOrder(1);
        return viewResolver;
    }

	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		System.out.println("\n\nInside addResourceHandlers.....\n\n");
		registry.addResourceHandler("/resources/**").addResourceLocations(
				"/resources/");
	}

	/**
	 * Add view controllers to create a direct mapping between a URL path and
	 * view name without the need for a controller in between.
	 */
	/*
	 * @Override public void addViewControllers(ViewControllerRegistry registry)
	 * { registry.addViewController("/").setViewName("index"); }
	 */

	/*
	 * ReloadableResourceBundleMessageSource
	 * reloadableResourceBundleMessageSource=new
	 * ReloadableResourceBundleMessageSource();
	 * reloadableResourceBundleMessageSource
	 * .setBasename("classpath:resources/messages");
	 * reloadableResourceBundleMessageSource.setDefaultEncoding("UTF-8");
	 * System.out.println("\n\nInside getMessageSource.....\n\n"); return
	 * reloadableResourceBundleMessageSource; }
	 */

	@Override
	public void configureDefaultServletHandling(
			DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
}
