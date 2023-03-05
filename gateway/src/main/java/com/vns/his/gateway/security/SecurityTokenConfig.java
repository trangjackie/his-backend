package com.vns.his.gateway.security;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
@EnableWebSecurity 	// Enable security config. This annotation denotes config for spring security.
public class SecurityTokenConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private JwtConfig jwtConfig;

	final private String backGroundRole = "backGroundRole";
	
	
	@Override
  	protected void configure(HttpSecurity http) throws Exception {
    	   http
		.cors().and()
		.csrf().disable()
		    // make sure we use stateless session; session won't be used to store user's state.
	 	    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) 	
		.and()
		    // handle an authorized attempts 
		    .exceptionHandling().authenticationEntryPoint((req, rsp, e) -> rsp.sendError(HttpServletResponse.SC_UNAUTHORIZED)) 	
		.and()
		   // Add a filter to validate the tokens with every request
		.addFilterAfter(new JwtTokenAuthenticationFilter(jwtConfig), UsernamePasswordAuthenticationFilter.class)
		// authorization requests config
		.authorizeRequests()
		    // allow all to access swagger
			.antMatchers("/auth/**").permitAll()
			.antMatchers("/user/**").permitAll()
			.antMatchers("/common/**").permitAll()
			.antMatchers(HttpMethod.GET, "/swagger-ui/**","/v3/api-docs/**").permitAll()
			.antMatchers(HttpMethod.GET, "/user/swagger-ui/**","/user/v3/api-docs/**").permitAll() 
			.antMatchers(HttpMethod.GET, "/common/swagger-ui/**","/user/v3/api-docs/**").permitAll() 
			.antMatchers(HttpMethod.GET, "/filestorage/swagger-ui/**","/filestorage/v3/api-docs/**").permitAll() 
		   // Any other request must be authenticated
		   .anyRequest().authenticated(); 
		
	}
}