package com.bacode.hi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bacode.hi.dao.ItestDao;
import com.bacode.hi.vo.TestVO;

@Service
public class TestServiceImpl implements ItestService {

	 @Autowired
	 ItestDao dao;
	
	@Override
	public TestVO selectALL() {
		TestVO vo = dao.getTestVO();
		return vo;
	}

	@Override
	public List<TestVO> getTestVoList() {
		 List<TestVO> voList = dao.getTestVoList();
		return voList;
	}

	

	// @Override
	/*
	 * public TestVO selectALL() {
	 * 
	 * //TestVO vo = dao.getTestVO();
	 * 
	 * //return vo; return null; }
	 */

}
