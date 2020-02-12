package com.bacode.hi.service.member;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bacode.hi.vo.MemberVO;
import com.bacode.hi.vo.TestVO;

@Service
public interface IMemberService {

	List<MemberVO> selectAllMember();

	MemberVO selectMember();

	public MemberVO selectOne(String mem_name);
	public void insertMember(MemberVO memberInfo);

}
