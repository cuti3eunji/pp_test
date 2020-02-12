package com.bacode.hi.service.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bacode.hi.dao.MemberDao;
import com.bacode.hi.vo.MemberVO;
@Service
public class MemberServiceImpl implements IMemberService {

	@Autowired
	MemberDao dao;
	
	@Override
	public List<MemberVO> selectAllMember() {
		 List<MemberVO> memberList = dao.selectAllMember();
		return memberList;
	}

	@Override
	public MemberVO selectMember() {
		MemberVO member = dao.selectMember();
		return member;
	}

	@Override
	public MemberVO selectOne(String mem_name) {
		MemberVO mem = dao.selectOne(mem_name);
		return mem;
	}

	@Override
	public void insertMember(MemberVO memberInfo) {
		dao.insertMember(memberInfo);
		
	}

	

}
