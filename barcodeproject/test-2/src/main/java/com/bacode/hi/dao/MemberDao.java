package com.bacode.hi.dao;

import java.util.List;

import com.bacode.hi.vo.MemberVO;

public interface MemberDao {

	List<MemberVO>  selectAllMember();
	
	MemberVO selectMember();
	MemberVO selectOne(String mem_name);
	void insertMember(MemberVO memberInfo);
	
}
