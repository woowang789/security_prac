package com.mycompany.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.mycompany.domain.Member;

@Mapper
public interface MemberMapper {
	
	public Member getMemberById(String id);
	public void insertMember(Member member);

}
