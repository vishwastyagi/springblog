package org.blog.springblog.controller;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Scope("request")
public class LoginController {
	private static final Logger logger = LogManager.getLogger(LoginController.class.getName());
	@RequestMapping(value={"/*"},method=RequestMethod.GET)
	public ModelAndView welcomePage(){
		logger.debug("Inside welcomePage method");
		ModelAndView modelAndView=new ModelAndView();
		//modelAndView.setViewName("pages/welcomePage");
		modelAndView.setViewName("jsp/welcomePage");
		logger.debug("Exiting welcomePage method");
		return modelAndView;
	}
}
