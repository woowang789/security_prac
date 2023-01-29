package com.mycompany.security.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mycompany.domain.Member;
import com.mycompany.mapper.MemberMapper;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private MemberMapper memberMapper;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Member member = memberMapper.getMemberById(username);
		if(member == null) {
			throw new UsernameNotFoundException("No user found with username : "+ username);
		}
		
		ArrayList<GrantedAuthority> roles = new ArrayList<>();
		roles.add(new SimpleGrantedAuthority(member.getRole()));
		
		return new MemberContext(member, roles);
	}
	
	

}
