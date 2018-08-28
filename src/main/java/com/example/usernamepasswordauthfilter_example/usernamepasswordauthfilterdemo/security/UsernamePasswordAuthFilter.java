package com.example.usernamepasswordauthfilter_example.usernamepasswordauthfilterdemo.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created on August, 2018
 *
 * @author yagiz
 */

@Slf4j
public class UsernamePasswordAuthFilter extends UsernamePasswordAuthenticationFilter {

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
		request.getSession().removeAttribute("enteredUsername");
		return super.attemptAuthentication(request, response);
	}

	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed)
			throws IOException, ServletException {
		String formComingUsername = obtainUsername(request);
		request.getSession().setAttribute("enteredUsername", formComingUsername);
		logger.info("Entered username is set on session");
		super.unsuccessfulAuthentication(request, response, failed);
	}
}
