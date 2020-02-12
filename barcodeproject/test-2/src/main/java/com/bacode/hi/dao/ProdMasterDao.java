package com.bacode.hi.dao;

import java.util.List;

import com.bacode.hi.vo.MemberVO;
import com.bacode.hi.vo.ProdMasterVO;

public interface ProdMasterDao {

	//제품마스터 리스트
	List<ProdMasterVO>  selectAllMaster();
	
	
	
}
