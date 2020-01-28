package openapitest;

import java.net.MalformedURLException; 
import java.net.URL;
import java.rmi.RemoteException;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

//import kr.or.kpis.biz.sk.skf.ws.MsupBnoReqVo;
//import kr.or.kpis.biz.sk.skf.ws.MsupBnoResVo;
//import kr.or.kpis.biz.sk.skf.ws.MsupBriefSummary01ReqVo;
//import kr.or.kpis.biz.sk.skf.ws.MsupBriefSummary01ResVo;
//import kr.or.kpis.biz.sk.skf.ws.MsupRfidReqVo;
//import kr.or.kpis.biz.sk.skf.ws.MsupRfidResVo;
import kr.or.kpis.biz.sk.skf.ws.MsupStdCdReqVo;
import kr.or.kpis.biz.sk.skf.ws.MsupStdCdResVo;
//import kr.or.kpis.biz.sk.skf.ws.MsupBscqReqVo;
//import kr.or.kpis.biz.sk.skf.ws.MsupBscqResVo;
//import kr.or.kpis.biz.sk.skf.ws.MsupBnoWebService;
//import kr.or.kpis.biz.sk.skf.ws.MsupBriefSummary01WebService;
//import kr.or.kpis.biz.sk.skf.ws.MsupRfidWebService;
//import kr.or.kpis.biz.sk.skf.ws.MsupSnoDtlWebService;
//import kr.or.kpis.biz.sk.skf.ws.MsupSnoWebService;
import kr.or.kpis.biz.sk.skf.ws.MsupStdCdWebService;
//import kr.or.kpis.biz.sk.skf.ws.MsupBscqWebService;

/**
 * 
 *
 *
 * @className : APITest
 * @description : 웹서비스 구동 테스트 
 * @author 의약품일련번호
 * @version 1.0
 * @see
 *
 * Copyright (C) by HIRA All right reserved.
 */
public class sample {


/* 표준코드 마스터 조회*/

	public void msupStdCdInfo() throws RemoteException {
		System.out.println("■ 표준코드 마스터 조회 웹서버스");
		try {

			URL url = new URL("http://openapi.kpis.or.kr/services/msupStdCdInfo?wsdl");	//운영서버 (수정하지 말것)
			QName svcNm = new QName("http://service.biz.kpis.or.kr", "msupStdCdWebService"); //서비스 주소 (수정하지 말것)

			Service service = Service.create(url, svcNm);

			MsupStdCdWebService msupService = service.getPort(MsupStdCdWebService.class);

			MsupStdCdReqVo request = new MsupStdCdReqVo();
//			request.setApiKey("응답KEY 입력");
//			request.setAplHbin("사업자번호 입력"); //공급내역보고업체
			request.setMsupStdCd("8806400060123"); //표준코드(예시)

			MsupStdCdResVo response = msupService.getMsupStdCdInfo(request);

			System.out.println(response.getResult());
			System.out.println(response.getResultMessage());
			System.out.println(response.getApiKey());
			System.out.println(response.getAplHbin());
			System.out.println(response.getMsupStdCd());

			if (response.getMsupStdCdList() != null) {

				for (int i = 0; i < response.getMsupStdCdList().length; i++) {
					System.out.println(response.getMsupStdCdList()[i].getStdCd() + "//" + response.getMsupStdCdList()[i].getKorMgdsNm()+ "//" + response.getMsupStdCdList()[i].getAdptFrDt()+"//" + response.getMsupStdCdList()[i].getAdptToDt());
				}
			}

		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
	
///* RFID 정보 조회*/	
//
//	public void getMsupRfidInfo() {
//		System.out.println("■ RFID 정보 조회 웹서버스");
//		try {
//			URL url = new URL("http://openapi.kpis.or.kr/services/msupRfidInfo?wsdl"); //운영서버 (수정하지 말것)
//			QName svcNm = new QName("http://service.biz.kpis.or.kr", "msupRfidWebService"); //서비스 주소 (수정하지 말것)
//
//			Service service = Service.create(url, svcNm);
//
//			MsupRfidWebService msupService = service.getPort(MsupRfidWebService.class);
//
//			MsupRfidReqVo request = new MsupRfidReqVo();
//			request.setApiKey("응답KEY 입력");
//			request.setAplHbin("사업자번호 입력"); //공급내역보고업체
//			request.setMsupRfidTagCd("8806429002203"); //RFID태그 코드(예시)
//			request.setMsupSno("150002413645"); //RFID일련번호(예시)
//
//			MsupRfidResVo response = msupService.getMsupRfidInfo(request);
//
//			System.out.println(response.getResult());
//			System.out.println(response.getResultMessage());
//			System.out.println(response.getApiKey());
//			System.out.println(response.getAplHbin());			
//			System.out.println(response.getMsupRfidTagCd());
//			System.out.println(response.getMsupSno());
//
//			if (response.getMsupRfidTagList() != null) {
//
//				for (int i = 0; i < response.getMsupRfidTagList().length; i++) {
//					System.out.println(response.getMsupRfidTagList()[i].toString());
//				}
//			}
//
//		} catch (MalformedURLException e) {
//			e.printStackTrace();
//		}
//	}
//	
///* 묶음번호 최소박스 조회*/			
//
//	public void getMinMsupBnoInfo() {
//		System.out.println("■ 묶음번호 최소박스 조회 웹서버스");
//		try {
//
//			URL url = new URL("http://openapi.kpis.or.kr/services/msupBnoInfo?wsdl"); //운영서버 (수정하지 말것)
//			QName svcNm = new QName("http://service.biz.kpis.or.kr", "msupBnoWebService"); //서비스 주소 (수정하지 말것)
//
//			Service service = Service.create(url, svcNm);
//
//			MsupBnoWebService msupService = service.getPort(MsupBnoWebService.class);
//
//			MsupBnoReqVo request = new MsupBnoReqVo();
//			request.setApiKey("응답KEY 입력");
//			request.setAplHbin("사업자번호 입력"); //공급내역보고업체
//			request.setMsupBno("(01)8806572021520(21)382F07AAC1000036"); //묶음번호(예시)
//
//			MsupBnoResVo response = msupService.getMinMsupBnoInfo(request);
//
//			System.out.println(response.getResult());
//			System.out.println(response.getResultMessage());
//			System.out.println(response.getApiKey());
//			System.out.println(response.getAplHbin());			
//			System.out.println(response.getMsupBno());
//
//			if (response.getMinMsupBnoList() != null) {
//
//				for (int i = 0; i < response.getMinMsupBnoList().length; i++) {
//					System.out.println(response.getMinMsupBnoList()[i]);
//				}
//			}
//
//		} catch (MalformedURLException e) {
//			e.printStackTrace();
//		}
//	}
//
///* 묶음번호 일련번호 조회*/	
//
//	public void getMsupSnoInfo() {
//		System.out.println("■ 묶음번호 일련번호 조회 웹서버스");
//		try {
//			URL url = new URL("http://openapi.kpis.or.kr/services/msupSnoInfo?wsdl"); //운영서버 (수정하지 말것)
//			QName svcNm = new QName("http://service.biz.kpis.or.kr", "msupSnoWebService"); //서비스 주소 (수정하지 말것)
//
//			Service service = Service.create(url, svcNm);
//
//			MsupSnoWebService msupService = service.getPort(MsupSnoWebService.class);
//
//			MsupBnoReqVo request = new MsupBnoReqVo();
//			request.setApiKey("응답KEY 입력");
//			request.setAplHbin("사업자번호 입력"); //공급내역보고업체
//			request.setMsupBno("(01)8806572021523(21)382F07AAC1000850"); //최소묶음번호(예시)
//
//			MsupBnoResVo response = msupService.getMsupSnoInfo(request);
//
//			System.out.println(response.getResult());
//			System.out.println(response.getResultMessage());			
//			System.out.println(response.getApiKey());
//			System.out.println(response.getAplHbin());
//			System.out.println(response.getMsupBno());
//			System.out.println(response.getMsupSnoList());
//
//			if (response.getMsupSnoList() != null) {
//
//				for (int i = 0; i < response.getMsupSnoList().length; i++) {
//					System.out.println(response.getMsupSnoList()[i].toString());
//				}
//			}
//		} catch (MalformedURLException e) {
//			e.printStackTrace();
//		}
//	}
//
//
///* 묶음번호 일련번호 상세 정보 조회*/	
//
//	public void getMsupSnoDtlInfo() {
//		System.out.println("■ 묶음번호 일련번호 상세 조회 웹서버스");
//		try {
//			URL url = new URL("http://openapi.kpis.or.kr/services/msupSnoDtlInfo?wsdl");			//운영서버 (수정하지 말것)
//			QName svcNm = new QName("http://service.biz.kpis.or.kr", "msupSnoDtlWebService");		//서비스 주소 (수정하지 말것)
//
//			Service service = Service.create(url, svcNm);
//			
//			MsupSnoDtlWebService msupService = service.getPort(MsupSnoDtlWebService.class);
//			
//			MsupBnoReqVo request = new MsupBnoReqVo();
//			request.setApiKey("응답KEY 입력");
//			request.setAplHbin("사업자번호 입력"); //공급내역보고업체
//			request.setMsupBno("(01)8806572021523(21)382F07AAC1000850");
//
//			
//			for (int j=0; j<1; j++){
//
//				MsupBnoResVo response = msupService.getMsupSnoDtlInfo(request);
//	
//				System.out.println(response.getResult());
//				System.out.println(response.getResultMessage());			
//				System.out.println(response.getApiKey());
//				System.out.println(response.getAplHbin());
//				System.out.println(response.getMsupBno());
//				System.out.println(response.getMsupSnoDtlList());
//	
//				if (response.getMsupSnoDtlList() != null) {
//	
//					for (int i = 0; i < response.getMsupSnoDtlList().length; i++) {
//						System.out.println(response.getMsupSnoDtlList()[i].toString());
//					}
//				}
//			}
//		} catch (MalformedURLException e) {
//			e.printStackTrace();
//		}
//	}	
//
///* 거래처 정보 조회*/
//	public void getMsupBscqInfo() {
//		System.out.println("■ 거래처 정보 조회 웹서비스");
//		try {
//			URL url = new URL("http://openapi.kpis.or.kr/services/msupBscqInfo?wsdl");
//			QName svcNm = new QName("http://service.biz.kpis.or.kr", "msupBscqWebService");
//
//			Service service = Service.create(url, svcNm);
//			
//			MsupBscqWebService msupService = service.getPort(MsupBscqWebService.class);
//			
//	   	        MsupBscqReqVo request = new MsupBscqReqVo();
//		        request.setApiKey("응답KEY 입력");
//		        request.setAplHbin("사업자번호 입력"); //공급내역보고업체
//		        request.setSplyHbin("공급자사업자번호 입력");
//		        request.setMsupSplyFrmCd("5"); 
//		        request.setTkrHbin("1234567890"); 		     
//		        request.setYkiho("99999999");
//			
//			for (int j=0; j<1; j++){
//
//				MsupBscqResVo response = msupService.getMsupBscqInfo(request);
//	
//				System.out.println(response.getResult());
//				System.out.println(response.getResultMessage());			
//				System.out.println(response.getApiKey());
//				System.out.println(response.getMsupBscqList());
//				
//				if (response.getMsupBscqList() != null) {
//	
//					for (int i = 0; i < response.getMsupBscqList().length; i++) {
//						System.out.println(response.getMsupBscqList()[i].toString());
//					}
//				}
//			}
//		} catch (MalformedURLException e) {
//			e.printStackTrace();
//		}
//	}	
//	
//	
///* 공급내역 집계 정보 조회*/
//	public void getMsupBriefSummary01Info() {
//		System.out.println("■ 공급내역 집계 정보 조회 웹서비스");
//		try {
//			URL url = new URL("http://openapi.kpis.or.kr/services/msupBriefSummary01Info?wsdl");			                    
//			QName svcNm = new QName("http://service.biz.kpis.or.kr", "msupBriefSummary01WebService");
//			Service service = Service.create(url, svcNm);
//			
//			MsupBriefSummary01WebService msupService = service.getPort(MsupBriefSummary01WebService.class);
//			
//		        MsupBriefSummary01ReqVo request = new MsupBriefSummary01ReqVo();
//		        request.setApiKey("응답KEY 입력");
//		        request.setAplHbin("사업자번호 입력"); //공급내역보고업체
//		        request.setSplyHbin("공급자사업자번호 입력");		    
//  		        request.setSplyDd("20170706");		    
//			
//			for (int j=0; j<1; j++){
//
//				MsupBriefSummary01ResVo response = msupService.getMsupBriefSummary01Info(request);
//	
//				System.out.println(response.getResult());
//				System.out.println(response.getResultMessage());			
//				System.out.println(response.getApiKey());
//				System.out.println(response.getMsupBriefSummary01List());
//				
//				if (response.getMsupBriefSummary01List() != null) {
//	
//					for (int i = 0; i < response.getMsupBriefSummary01List().length; i++) {
//						System.out.println(response.getMsupBriefSummary01List()[i].toString());						
//					}
//				}
//			}
//		} catch (MalformedURLException e) {
//			e.printStackTrace();
//		}
//	}	

}