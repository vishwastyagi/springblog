package org.blog.springblog.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.fasterxml.jackson.databind.ObjectMapper;

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
		System.out.println("htmlViewResolver method");  // sixth this will be called
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
		System.out.println(" jspViewResolver method ");  // seventh this will be called
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix(VIEW_DIR_JSP);
        viewResolver.setSuffix(VIEW_EXTN_JSP);
        viewResolver.setOrder(1);
        return viewResolver;
    }

	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		System.out.println("\n\nInside addResourceHandlers.....\n\n"); // fourth this will be called
		registry.addResourceHandler("/resources/**").addResourceLocations(
				"/resources/");
	}
	
	
	/*public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
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


	@Override
 	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		System.out.println("Inside configureMessageConverters");
		converters.add(converter());
	}
	
	@Bean
	  public MappingJackson2HttpMessageConverter converter() {
	      MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
	      converter.setObjectMapper(mapper());
	      System.out.println("Inside converter "+converter);
	      return converter;
	  }
	/*
	  * Provides the Jackson ObjectMapper with custom configuration for our JSON serialization.
	  * @return The Jackson object mapper with non-null serialization configured
	  */
	@Bean
	  public ObjectMapper mapper() {
	      return new ObjectMapper();
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
		System.out.println("\n\ninside configureDefaultServletHandling\n\n"); //fifth this will be called
		configurer.enable();
	}
}
