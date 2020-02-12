package com.bacode.hi.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.onbarcode.barcode.DataMatrix;
import com.onbarcode.barcode.IBarcode;
import com.zebra.sdk.comm.Connection;
import com.zebra.sdk.comm.ConnectionException;
import com.zebra.sdk.comm.TcpConnection;
import com.zebra.sdk.printer.PrinterLanguage;
import com.zebra.sdk.printer.ZebraPrinter;
import com.zebra.sdk.printer.ZebraPrinterFactory;
import com.zebra.sdk.printer.ZebraPrinterLanguageUnknownException;

@Controller
public class TestController {

	@RequestMapping("/home")
	public void home() {

	}
	
	

	/**
	 * full 바코드를 생성하기 위해 ajax 통신하는 메서드
	 * 
	 * @param request : fullbarcode 생성하기 위해 jsp 에서 값 보내주는
	 * @return
	 * @throws Exception
//	 */
//	@RequestMapping("/genBarcode")
//	@ResponseBody
//	public String generate(HttpServletRequest request, Model model) throws Exception {
//
//		String fullBarcode = request.getParameter("fullBarcode");
//
//		String result = fullBarcode;
//
//		DataMatrix Dbarcode = new DataMatrix();
//		Dbarcode.setData(fullBarcode);
//		Dbarcode.setDataMode(DataMatrix.M_AUTO);
//		Dbarcode.setFormatMode(DataMatrix.F_10X10);
//		Dbarcode.setProcessTilde(true);
//		Dbarcode.setUom(IBarcode.UOM_PIXEL);
//		Dbarcode.setX(3f);
//
//		// margin 주는거
//		Dbarcode.setLeftMargin(10f);
//		Dbarcode.setRightMargin(10f);
//		Dbarcode.setTopMargin(10f);
//		Dbarcode.setBottomMargin(10f);
//		// barcode image resolution in dpi
//		Dbarcode.setResolution(72);
//
//		  int ran = (int)(Math.random() * 100 + 1);
//		  
//		  String random =""+ran;
//		  
//		try {
////			Dbarcode.drawBarcode("D:\\dfdfdfdf\\test-2\\src\\main\\resources\\static\\img\\barcode"+random+".gif");
//			Dbarcode.drawBarcode("D:\\dfdfdfdf\\test-2\\src\\main\\resources\\static\\img\\barcode.gif");
//			Dbarcode.drawBarcode("D:\\images\\barcode.gif");
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		System.out.println("데이터 매트릭스 바코드 생성 완료");
//
////		result ="/img/barcode"+random+".gif";
////		model.addAttribute("img",result);
//		
//		return result;
//
//	}
//	
	
	@RequestMapping("/barcodeImg")
	public void barcodeImg() {
		
	}
	
//	
//	/**
//	 * 라벨로 프린터 하기 위해 ajax 통신을 위한 메서드
//	 * 
//	 * @param request
//	 * @throws Exception
//	 */
//	@RequestMapping("/print")
//	@ResponseBody
//	public void print(HttpServletRequest request) throws Exception {
//		
//	//파라미터값 가져오기
//	String gs1code = request.getParameter("gs1code");
//	String expireIn = request.getParameter("expireIn");
//	String lineNumIn = request.getParameter("lineNumIn");
//	String serialNumIn = request.getParameter("serialNumIn");
//
//		// 파일가져오기
//		//2차원 데이터매트릭스
//		ClassPathResource resource = new ClassPathResource("hihi.prn");
//		//2차원 데이터매트릭스
////		ClassPathResource resource = new ClassPathResource("datamatrix.prn");
//
//		// 파일 읽기
//		// 버퍼를 이용하여 입력하기 위해
//		// 버퍼 ? 데이터를 한 곳에서 다른 한 곳으로 전송하는 동안 일시적으로 데이터를 보관하는 임시 메모리 영역
//		// 입출력 속도 향상을 위해 버퍼 사용
//		BufferedReader bReader = null;
//
//		byte[] buffers = null;
//
//		try {
//			// 파일 내용 한줄한줄 닮을 String
//			String s;
//
//			// 파일 객체생성, uri에 있는 파일로
//			File file = new File(resource.getURI());
//
//			// 파일 읽어와서 담기
//			bReader = new BufferedReader(new FileReader(file));
//
//			// 파일 전체내용을 닮을 객체
//			String total = "";
//
//			// 더이상 읽어들일게 없을 때까지 읽어들이게 합니다.
//			while ((s = bReader.readLine()) != null) {
//				total += s;
//				System.out.println(s);
//			}
//			
//			// 입력할 바코드값 바꾸기
//			String ffff = total.replace("**************", gs1code).replace("zzz", expireIn).replace("###", lineNumIn).replace("vvvvvvvvvvvvvvvvvvvv", serialNumIn);
//			System.out.println(ffff);
//
//			// 바코드 바이트로 가져오기
//			buffers = ffff.getBytes();
//
//		} catch (IOException e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				if (bReader != null)
//					bReader.close();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//
//		// 프린터연결
//		Connection connection = new TcpConnection("192.168.1.154", TcpConnection.DEFAULT_ZPL_TCP_PORT);
//		
//		try {
//			connection.open();
//			ZebraPrinter zPrinter = ZebraPrinterFactory.getInstance(connection);
//			PrinterLanguage pcLanguage = zPrinter.getPrinterControlLanguage();
//			System.out.println("Printer Control Language is " + pcLanguage);
//			System.out.println("연결됨.................");
//
//			// 파일인쇄
//			// connection.write(resource.getInputStream());
//			
//			// Byte로 읽어서 출력하기
//			 connection.write(buffers);
//			System.out.println("인쇄중임ㅋ.....");
//			
//			connection.close();
//			System.out.println("연결해제.");
//			
//		} catch (ConnectionException e) {
//			e.printStackTrace();
//		} catch (ZebraPrinterLanguageUnknownException e) {
//			e.printStackTrace();
//		} finally {
//			connection.close();
//		}
//	}
//	
//	/**
//	 * 바코드 조회하는거
//	 * @param andView
//	 * @param inputBarcode
//	 * @return
//	 */
//	@RequestMapping("/search")
//	public ModelAndView searchBarcode(ModelAndView andView,@RequestParam String inputBarcode) {
//		
//		System.out.println("==========search=======================");
//		//샘플바코드
//		//01/08806469007114/17/200131/10/010101/21/a4545
//		//010880999999999717180131.1014001.2100000014
//		
//		//gsfuction존재하는가?
//		 CharSequence gs = Character.toString((char) 29);
//		 System.out.println("contains gs ? " + inputBarcode.contains(gs));
//		 //받아온 바코드
//		 System.out.println(inputBarcode);
//		
//		System.out.println("========================================");
//		System.out.println(inputBarcode.length());
//		
//		
//		
//	      //맨앞 AI 확인
//	      String firAI = inputBarcode.substring(0,2);
//	      String fir = null;
//	      
//	      if(firAI.equals("01")) { //(01)이면 : GTIN
//	         fir = inputBarcode.substring(2,16);
//	      }else if(firAI.equals("17")) { //(17)이면 : exp
//	         fir = inputBarcode.substring(2,8);
//	      }else if(firAI.equals("10")) {
//	      	fir = inputBarcode.substring(2,7);
//	      }else if(firAI.equals("21")) { //가변이면
//	          fir = inputBarcode.substring(2,12);
//	      }
//	      
//	      //두번째 AI확인
//	      String[] secArr = inputBarcode.split(fir);
//	      String secAI = secArr[1].substring(0,2);
//	      String sec = null;
//	      
//	      if(secAI.equals("01")) { //(01)이면 : GTIN
//	         sec = secArr[1].substring(2,16);
//	      }else if(secAI.equals("17")) { //(17)이면 : exp
//	         sec = secArr[1].substring(2,8);
//	      }else if(secAI.equals("10")) {
//	    	  sec = secArr[1].substring(2,7);
//	      }else if(secAI.equals("21")) { //가변이면
//	    	  sec = secArr[1].substring(2,12);
//	        
//	      }
//	      
//	      //세번째 AI확인
//	      String[] thiArr = inputBarcode.split(sec);
//	      String thiAI =  thiArr[1].substring(0,2);
//	      String thi = null;
//	      
//	      if(thiAI.equals("01")) { //(01)이면 : GTIN
//	         thi = thiArr[1].substring(2,16);
//	      }else if(thiAI.equals("17")) { //(17)이면 : exp
//	         thi =  thiArr[1].substring(2,8);
//	      }else if(thiAI.equals("10")){
//	    	  thi =  thiArr[1].substring(2,7);
//	      }else if(thiAI.equals("21")) { //가변이면
//	            thi = thiArr[1].substring(2,12);
//	      }
//	      
//	      //세번째 AI확인
//	      String[] fourArr = inputBarcode.split(thi);
//	      String fourAI = fourArr[1].substring(0,2);
//	      String four = null;
//	      
//	      if(fourAI.equals("01")) { //(01)이면 : GTIN
//	         four = fourArr[1].substring(2,16);
//	         
//	      }else if(fourAI.equals("17")) { //(17)이면 : exp
//	         
//	         four = fourArr[1].substring(2,8);
//	         
//	      }else if(fourAI.equals("10")){
//	    	  four = fourArr[1].substring(2,7);
//	    	  
//	      }else if(fourAI.equals("21")) { //가변이면
//	    	  four = fourArr[1].substring(2,12);
//	     
//	      }
//
//	      
//	      String[] aiArr = {firAI, secAI, thiAI, fourAI};
//	      String[] dataArr = {fir, sec, thi, four};
//	      
//	      
//	      String search = "";
//	      
//	      for(int i=0; i<aiArr.length; i++) {
//	         if(aiArr[i].equals("01")){
//	            System.out.println("표준코드 (01) : " + dataArr[i] + "\n");
//	            search += dataArr[i];
//	         }else if(aiArr[i].equals("17")) {
//	        	   System.out.println("유효기한 (17) : " + dataArr[i] + "\n");
//	        	   search += dataArr[i];
//	         }else if(aiArr[i].equals("10")) {
//	        	 System.out.println("제조번호 (10) : " + dataArr[i] + "\n");
//	        	  search += dataArr[i];	        	 
//	         }else if(aiArr[i].equals("21")) {
//	        	   System.out.println("일련번호 (21) : " + dataArr[i] + "\n");
//	        	   search += dataArr[i];
//	         }
//	         
//	         
//	      }
//		
//		
//		
////		BarcodeVO vo = new BarcodeVO();
////		
////		
////		vo.setGs1code(inputBarcode.substring(0,16));
////		vo.setExpire(inputBarcode.substring(16,24));
////		
//////		vo.setlNum(inputBarcode.split((String) gs));
////		
////		vo.setlNum(inputBarcode.substring(26,31));
////		vo.setsNum(inputBarcode.substring(34));
//		
////		andView.addObject("zz",vo);
//		andView.addObject("msg","나가세요");
//		
//		andView.setViewName("/home");
//		
//		System.out.println("dd");
//		return andView;
//		
//		
//		
//		
//	}
	
	

}
