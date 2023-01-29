package com.mycompany.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.mycompany.domain.Member;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class MemberMapperTest {

	@Autowired
	private MemberMapper memberMapper;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Test
	public void getMemberById() {
		Member member =  memberMapper.getMemberById("admin");
		log.info(member+"");
	}
	
	@Test
	public void insertMember() {
		Member member = new Member();
		member.setId("user2");
		member.setName("name2");
		String pw = "1111";
		member.setPw(passwordEncoder.encode(pw));
		
		memberMapper.insertMember(member);
	}
}
