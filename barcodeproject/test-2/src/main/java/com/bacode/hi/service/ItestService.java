package com.bacode.hi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bacode.hi.vo.MemberVO;
import com.bacode.hi.vo.TestVO;

@Service
public interface ItestService {

		TestVO selectALL();

		List<TestVO> getTestVoList();
		
		

}
