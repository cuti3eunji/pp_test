package com.bacode.hi.service.barcode;

import org.springframework.stereotype.Service;

import com.bacode.hi.vo.BarcodeVO;
@Service
public interface IBarcodeService {

	void insertBarcode(BarcodeVO barcodeInfo);
}
