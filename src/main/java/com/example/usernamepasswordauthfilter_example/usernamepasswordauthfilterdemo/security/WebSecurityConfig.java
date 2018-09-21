package com.example.usernamepasswordauthfilter_example.usernamepasswordauthfilterdemo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Created on August, 2018
 *
 * @author yagiz
 */
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//formatter:off
		http
				.addFilterBefore(usernamePasswordAuthenticationCustomFilterFilter(), UsernamePasswordAuthenticationFilter.class )
				.csrf()
				.ignoringAntMatchers("/h2-console/**")
				.and()
				.headers()
				.frameOptions()
				.sameOrigin()
				.and()
				.authorizeRequests()
				.anyRequest().authenticated()
				.and()
				.formLogin()
				.loginPage("/login")
				.permitAll().and()
				.logout()
				.permitAll();
		//formatter:on
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
				.withUser("user")
				.password("{noop}password")
				.roles("USER");
	}

	public UsernamePasswordAuthenticationCustomFilter usernamePasswordAuthenticationCustomFilterFilter() throws Exception {
		UsernamePasswordAuthenticationCustomFilter filter = new UsernamePasswordAuthenticationCustomFilter();
		filter.setAuthenticationManager(authenticationManagerBean());
		filter.setAuthenticationFailureHandler(failureHandler());
		return filter;
	}

	@Bean
	public SimpleUrlAuthenticationFailureHandler failureHandler() {
		return new SimpleUrlAuthenticationFailureHandler("/login?error");
	}

}
