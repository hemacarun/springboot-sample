package com.pollmgmt.security.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.session.jdbc.config.annotation.web.http.EnableJdbcHttpSession;
import org.springframework.session.web.http.HeaderHttpSessionIdResolver;
import org.springframework.session.web.http.HttpSessionIdResolver;

import com.pollmgmt.security.services.UserDetailsServiceImpl;

@Order(2)
@Configuration
@ConditionalOnProperty(prefix = "mobile.security.custom", 
name = "enabled", 
havingValue="true")
@EnableWebSecurity
@EnableJdbcHttpSession(maxInactiveIntervalInSeconds = 84600)
public class  WebSessionSecurityConfig {
	@Autowired
	  UserDetailsServiceImpl userDetailsService;

	  @Bean
	  public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
	      return authenticationConfiguration.getAuthenticationManager();
	  }

	  @Bean
	  public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	  }
	  
	  @Bean
	  public SecurityFilterChain SessionfilterChain(HttpSecurity http) throws Exception{
	    http.cors().and().csrf().disable().formLogin()
				.disable()
	    
	       .sessionManagement(session -> session
	            .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED))
	       .sessionManagement(session -> session
	               .maximumSessions(1))
	           
	        
	      //.exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
	     // .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
	      .authorizeRequests().antMatchers("/**").permitAll()
	     // .antMatchers("/api/test/**").permitAll()
	    .and()
				.logout(logout -> logout
							.logoutUrl("/logout"));
							//	.logoutSuccessUrl("/my/index")
							//	.logoutSuccessHandler(logoutSuccessHandler)
						//		.invalidateHttpSession(true));
							//	.addLogoutHandler(logoutHandler)
							//	.deleteCookies(cookieNamesToClear));

						//  http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
	    return http.build();
	  }
	  
	  @Bean
	    public HttpSessionIdResolver httpSessionIdResolver() {

		  return new HeaderHttpSessionIdResolver("X-Auth-Token");
	    }

}
