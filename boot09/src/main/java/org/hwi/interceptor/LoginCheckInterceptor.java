package org.hwi.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import lombok.extern.java.Log;

@Log
public class LoginCheckInterceptor extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		log.info("preHandle.......................................");
		
		String dest = request.getParameter("dest");
		
		if (dest != null) {
			request.getSession().setAttribute("dest", dest);
		}
		return super.preHandle(request, response, handler);
	}
}
