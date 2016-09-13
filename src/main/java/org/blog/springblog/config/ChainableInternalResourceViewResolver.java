package org.blog.springblog.config;

import java.io.InputStream;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.AbstractUrlBasedView;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

public class ChainableInternalResourceViewResolver extends
		InternalResourceViewResolver {
	/*
	 * private static Log logger = LogFactory
	 * .getLogger(ChainableInternalResourceViewResolver.class);
	 */
	/**
	     * 
	     */
	protected AbstractUrlBasedView buildView(String viewName) throws Exception {
		// logger.entering("buildView");
		String url = getPrefix() + viewName + getSuffix();
		System.out.println("Inside buildView url= "+url);
		InputStream stream = getServletContext().getResourceAsStream(url);
		System.out.println("Inside buildView stream= "+stream);
		if (stream == null) {
			/*
			 * logger.log(Log.DEBUG,
			 * "-----!!!------resource not found-------!!!-----" + getPrefix() +
			 * viewName + getSuffix());
			 */
			return new NonExistentView();
		} else {
			/*
			 * logger.log(Log.DEBUG,
			 * "----***-------resource found-------***-----" + getPrefix() +
			 * viewName + getSuffix());
			 */
			stream.close();
		}
		return super.buildView(viewName);
	}

	/**
	 * 
	 * @author
	 *
	 */
	private static class NonExistentView extends AbstractUrlBasedView {

		// private static Log logger =
		// LogFactory.getLogger(NonExistentView.class);

		protected boolean isUrlRequired() {
			// logger.entering("isUrlRequired");
			System.out.println("Inside isUrlRequired");
			return false;
		}

		public boolean checkResource(Locale locale) throws Exception {
			// logger.entering("checkResource");
			System.out.println("Inside checkResource locale= "+locale);
			return false;
		}

		protected void renderMergedOutputModel(Map<String, Object> model,
				HttpServletRequest request, HttpServletResponse response)
				throws Exception {
			// logger.entering("renderMergedOutputModel");
			// Purposely empty, it should never get called
			System.out.println("Inside buildView model="+model+"  request="+request+"  response= "+response);
		}
	}
}