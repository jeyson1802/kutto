package com.kutto.plataforma.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler{

	private static final Logger logger = LogManager.getLogger(MyAuthenticationSuccessHandler.class);
	
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {

        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());

        if (roles.contains("ROLE_ADMIN")) {
            request.getSession(false).setMaxInactiveInterval(60000);
            response.sendRedirect(request.getContextPath() + "/horarios");
        } else if(roles.contains("ROLE_ATENCION")) {
            request.getSession(false).setMaxInactiveInterval(120);
            response.sendRedirect(request.getContextPath() + "/citas");
        } else if(roles.contains("ROLE_LOGISTICA")) {
            request.getSession(false).setMaxInactiveInterval(120);
            response.sendRedirect(request.getContextPath() + "/articulos");
        }

    }
}
