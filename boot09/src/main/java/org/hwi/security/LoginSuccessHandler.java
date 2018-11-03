package org.hwi.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import lombok.extern.java.Log;

@Log
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
	@Override
	protected String determineTargetUrl(HttpServletRequest request, HttpServletResponse response) {
		log.info("------------------determineTargetUrl---------------------");
		
		Object dest = request.getSession().getAttribute("dest");
		
		String nextURL = null;
		
		if (dest != null) {
			request.getSession().removeAttribute("dest");
			
			nextURL = (String) dest;
		} else {
			nextURL = super.determineTargetUrl(request, response);
		}
		
		log.info("------------------" + nextURL + "-------------------");
		
		return nextURL;
	}
}
