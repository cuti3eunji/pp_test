package _Final;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileModify {

	public static void main(String[] args) throws IOException {
		fileModify("08806416055502", "newfileeeee");
	}
	
	
	public static void fileModify(String barcode, String fileName) {
		String path = FileModify.class.getResource("").getPath(); // 현재 클래스의 절대 경로를 가져온다.
		File basicfile = new File(path + "gs1DM.prn");	// 기본이 될 파일 경로
		
		File savefile = new File(fileName + ".prn");	// 새로 저장할 파일 경로
		
		
		String str = "";	//읽어온 파일을 저장
		
		//파일의 내용을 읽어서 저장하고 지정 경로에 새파일로 저장(복사)
		try {
			FileInputStream fis = new FileInputStream(basicfile);
			FileOutputStream fos = new FileOutputStream(savefile);
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					new FileInputStream(basicfile), "UTF-8"));
			
			//파일읽기
			int c;	//읽어온 데이터를 저장할 변수
			while((c=reader.read()) != -1) {
				str += (char)c;
			}
			reader.close();
			
//			System.out.println(str.replace("@@@@@", barcode));
			
			//읽어온 파일의 바코드
			
			
			//파일저장 
			byte[] strToBytes = str.replace("@@@@@", barcode).getBytes();
			fos.write(strToBytes);
			System.out.println("저장완료");
			
			fos.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("파일이 업슴니다");
		}
		catch (IOException e) {
			e.printStackTrace();
			System.out.println("입출력오류");
		}
	}
	public static byte[] barcodeChange(String barcode) {
		String path = FileModify.class.getResource("").getPath(); // 현재 클래스의 절대 경로를 가져온다.
		File basicfile = new File(path + "gs1DM.prn");	// 기본이 될 파일 경로
		
//		File savefile = new File(fileName + ".prn");	// 새로 저장할 파일 경로
		
		
		String str = "";	//읽어온 파일을 저장
		byte[] strToBytes = null;
		
		//파일의 내용을 읽어서 저장하고 지정 경로에 새파일로 저장(복사)
		try {
			FileInputStream fis = new FileInputStream(basicfile);
//			FileOutputStream fos = new FileOutputStream(savefile);
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					new FileInputStream(basicfile), "UTF-8"));
			
			//파일읽기
			int c;	//읽어온 데이터를 저장할 변수
			while((c=reader.read()) != -1) {
				str += (char)c;
			}
			reader.close();
			
//			System.out.println(str.replace("@@@@@", barcode));
			
			//읽어온 파일의 바코드
			
			
			strToBytes = str.replace("@@@@@", barcode).getBytes();
//			fos.write(strToBytes);
//			System.out.println("저장완료");
			
//			fos.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("파일이 업슴니다");
		}
		catch (IOException e) {
			e.printStackTrace();
			System.out.println("입출력오류");
		}
		return strToBytes;
	}

}
