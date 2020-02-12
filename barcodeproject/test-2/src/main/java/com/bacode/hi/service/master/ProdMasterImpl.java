package com.bacode.hi.service.master;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bacode.hi.dao.ProdMasterDao;
import com.bacode.hi.vo.ProdMasterVO;
@Service
public class ProdMasterImpl implements IProdMaster {

	@Autowired
	ProdMasterDao dao;
	
	@Override
	public List<ProdMasterVO> selectAllMaster() {
		
		List<ProdMasterVO> masterList =  dao.selectAllMaster();
		
		return masterList;
	}

}
