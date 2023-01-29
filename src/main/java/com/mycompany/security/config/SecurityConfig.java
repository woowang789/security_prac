package com.mycompany.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.mycompany.security.handler.FormAccessDeniedHandler;
import com.mycompany.security.provider.FormAuthenticationProvider;

import lombok.extern.slf4j.Slf4j;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
@Slf4j
public class SecurityConfig  extends WebSecurityConfigurerAdapter{
	
    @Autowired
    private AuthenticationSuccessHandler formAuthenticationSuccessHandler;
    
    @Autowired
    private AuthenticationFailureHandler formAuthenticationFailureHandler;

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}


	@Override
	protected void configure(HttpSecurity http) throws Exception {
        http
	        .authorizeRequests()
	        .antMatchers("/user").hasRole("USER")
	        .antMatchers("/api/user").hasRole("USER")
	        .antMatchers("/admin").hasRole("ADMIN")
	        .antMatchers("/**").permitAll()
	        .anyRequest().authenticated()
	   .and()
            .formLogin()
            .successHandler(formAuthenticationSuccessHandler)
            .failureHandler(formAuthenticationFailureHandler)
            .permitAll()
       .and()
       		.exceptionHandling()
       		.accessDeniedHandler(accessDeniedHandler())
       	.and()
       		.logout()
       		.logoutSuccessUrl("/")
       		.invalidateHttpSession(true)
       		.deleteCookies("JSESSIONID, remember-me")
       	.and()
       		.csrf().disable();
	}
	
	
    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
    
    @Bean
    public AuthenticationProvider authenticationProvider() {
    	return new FormAuthenticationProvider(passwordEncoder());
    }
    
    public AccessDeniedHandler accessDeniedHandler() {
        FormAccessDeniedHandler commonAccessDeniedHandler = new FormAccessDeniedHandler();
        commonAccessDeniedHandler.setErrorPage("/denied");
        return commonAccessDeniedHandler;
    }
    
    

	
	

}
