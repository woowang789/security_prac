package com.mycompany.security.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.mycompany.domain.Member;

import lombok.Data;

@Data
public class MemberContext extends User{
	
	private Member member;
	
	public MemberContext( Member member ,Collection<? extends GrantedAuthority> authorities) {
		super(member.getId(),member.getPw(), authorities);
		this.member = member;
	}


	
	
	

}
