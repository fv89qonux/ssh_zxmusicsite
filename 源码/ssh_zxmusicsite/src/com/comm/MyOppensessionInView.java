package com.comm;


import javax.servlet.http.HttpServletRequest;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.OpenSessionInViewFilter;
import org.springframework.web.context.WebApplicationContext;

public class MyOppensessionInView extends OpenSessionInViewFilter {

	
	@Override
	protected SessionFactory lookupSessionFactory(HttpServletRequest request) {
		if (logger.isDebugEnabled()) {
			logger.debug("Using SessionFactory '" + getSessionFactoryBeanName() + "' for OpenSessionInViewFilter");
		}
		WebApplicationContext wac =
				(WebApplicationContext)getServletContext().getAttribute("org.springframework.web.servlet.FrameworkServlet.CONTEXT.spring_mvc");

		return (SessionFactory) wac.getBean(getSessionFactoryBeanName(), SessionFactory.class);
	}

}
