package com.example.usernamepasswordauthfilter_example.usernamepasswordauthfilterdemo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
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
				.addFilterBefore(usernamePasswordAuthFilter(), UsernamePasswordAuthenticationFilter.class)
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
				.exceptionHandling().authenticationEntryPoint(new AppAuthenticationEntryPoint("/login"))
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

	@Bean
	public UsernamePasswordAuthFilter usernamePasswordAuthFilter() throws Exception {
		UsernamePasswordAuthFilter filter = new UsernamePasswordAuthFilter();
		filter.setAuthenticationManager(authenticationManagerBean());
		return filter;

	}

}
