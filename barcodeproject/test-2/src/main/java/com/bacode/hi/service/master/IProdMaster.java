package com.bacode.hi.service.master;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bacode.hi.vo.ProdMasterVO;
@Service
public interface IProdMaster {

	//제품마스터 리스트
		List<ProdMasterVO>  selectAllMaster();
}
