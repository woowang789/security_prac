package com.mycompany.security.provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.mycompany.security.service.MemberContext;

public class FormAuthenticationProvider implements AuthenticationProvider{
	
	@Autowired
	private UserDetailsService userDetailService;
	
	private PasswordEncoder passwordEncoder;
	
	

	public FormAuthenticationProvider(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String id = authentication.getName();
		String pw = (String) authentication.getCredentials();
		
		MemberContext memberContext = (MemberContext) userDetailService.loadUserByUsername(id);
		
		if(!passwordEncoder.matches(pw, memberContext.getPassword())) {
			throw new BadCredentialsException("Invalid password");
		}
		
		return new UsernamePasswordAuthenticationToken(memberContext.getMember(), null, memberContext.getAuthorities());
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
	
	

}
