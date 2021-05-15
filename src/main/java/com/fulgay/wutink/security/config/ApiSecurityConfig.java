package com.fulgay.wutink.security.config;

import com.fulgay.wutink.security.entrypoint.fail.AuthenticationFailureEntryPoint;
import com.fulgay.wutink.security.jwt.filter.JwtAuthorizationFilter;
import com.fulgay.wutink.security.jwt.manager.JwtTokenManager;
import com.fulgay.wutink.security.provider.UserAuthenticationProvider;
import com.fulgay.wutink.security.service.userdetails.AuthenticationUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@Order(1)
public class ApiSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private AuthenticationFailureEntryPoint failureEntryPoint;
	
	@Autowired
	private JwtTokenManager tokenManager;

	@Autowired
	private AuthenticationUserDetailsService authenticationUserDetailsService;

	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		
		http = http.cors().and().csrf().disable();
		
		http = http
	            .sessionManagement()
	            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	            .and();
		
		http = http
	            .exceptionHandling()
	            .authenticationEntryPoint(failureEntryPoint)
	            .and();
		
		http.authorizeRequests()
				.antMatchers(
						 "/configuration/ui", "/configuration/security",
						 "/swagger-ui/**", "/swagger-resources/**", "/swagger-ui.html", "/webjars/**","/getUserById").permitAll()
				.antMatchers(HttpMethod.POST, "/login").permitAll()
				.anyRequest().authenticated()
			.and()
			.addFilter(new JwtAuthorizationFilter(authenticationManager(), tokenManager)); // kendi hazırladığımız filter ı yerleştiriyoruz
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) {

		UserAuthenticationProvider authenticationProvider = new UserAuthenticationProvider();
		authenticationProvider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
		authenticationProvider.setUserDetailsService(authenticationUserDetailsService);
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		auth.authenticationProvider(authenticationProvider);
	}

	@Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
