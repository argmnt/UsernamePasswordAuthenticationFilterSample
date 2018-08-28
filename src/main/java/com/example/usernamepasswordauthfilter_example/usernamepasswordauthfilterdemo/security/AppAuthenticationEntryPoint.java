package com.example.usernamepasswordauthfilter_example.usernamepasswordauthfilterdemo.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created on August, 2018
 *
 * @author yagiz
 */
public class AppAuthenticationEntryPoint extends LoginUrlAuthenticationEntryPoint {

	public AppAuthenticationEntryPoint(String loginFormUrl) {
		super(loginFormUrl);
	}

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
			throws IOException, ServletException {
		super.commence(request, response, authException);
	}
}
