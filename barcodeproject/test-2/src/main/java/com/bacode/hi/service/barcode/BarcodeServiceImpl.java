package com.bacode.hi.service.barcode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bacode.hi.dao.BarcodeDao;
import com.bacode.hi.vo.BarcodeVO;
@Service
public class BarcodeServiceImpl implements IBarcodeService {

	@Autowired
	BarcodeDao dao;
	
	@Override
	public void insertBarcode(BarcodeVO barcodeInfo) {
		dao.insertBarcode(barcodeInfo);

	}

}
