package _Final;


import java.io.File;
import java.io.FileInputStream;

//import org.springframework.core.io.ClassPathResource;

import com.zebra.sdk.comm.Connection;
import com.zebra.sdk.comm.ConnectionException;
import com.zebra.sdk.comm.TcpConnection;
import com.zebra.sdk.printer.PrinterLanguage;
import com.zebra.sdk.printer.ZebraPrinter;
import com.zebra.sdk.printer.ZebraPrinterFactory;
import com.zebra.sdk.printer.ZebraPrinterLanguageUnknownException;

public class Z_PrintTest {
	
	//라벨인쇄하기
	public static void main(String[] args) throws Exception{
		
		
		//파일가져오기
//		String path = Z_PrintTest.class.getResource("").getPath(); // 현재 클래스의 절대 경로를 가져온다.
//		System.out.println(path); //--> 절대 경로가 출력됨
//		File basicfile = new File(path + "gs1DM_test.prn");	// 기본이 될 파일 경로
		
		String path = Z_PrintTest.class.getResource("").getPath(); // 현재 클래스의 절대 경로를 가져온다.
		System.out.println(path); //--> 절대 경로가 출력됨
		File basicfile = new File(path + "gs1DataMatrixTest.prn");	// 기본이 될 파일 경로
		
		byte[] bb = FileModify.barcodeChange("08806416055519");
//		String fileName = "newPrintTest";
//		FileModify.fileModify("08806416055519", fileName);
		
//		FileInputStream fis = new FileInputStream(fileName + ".prn");
//		FileInputStream fis = new FileInputStream(basicfile);
		
		//프린터연결
		Connection connection = new TcpConnection("192.168.1.154", TcpConnection.DEFAULT_ZPL_TCP_PORT);
		try {
			connection.open();
			ZebraPrinter zPrinter = ZebraPrinterFactory.getInstance(connection);
			PrinterLanguage pcLanguage = zPrinter.getPrinterControlLanguage();
			System.out.println("Printer Control Language is " + pcLanguage);
			System.out.println("연결됨");

			// 파일인쇄
			connection.write(bb);

			System.out.println("인쇄중");

			connection.close();

			System.out.println("연결해제");
		} catch (ConnectionException e) {
			e.printStackTrace();
		} catch (ZebraPrinterLanguageUnknownException e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
	}

}


