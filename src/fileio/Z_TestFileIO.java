package fileio;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Z_TestFileIO {

	public static void main(String[] args) throws IOException {
		FileOutputStream output = new FileOutputStream("out.txt");
        String str = "hello, world";
        byte[] bytes = str.getBytes();
        output.write(bytes);
        output.close();
        
//		String path = Z_TestFileIO.class.getResource("").getPath(); // 현재 클래스의 절대 경로를 가져온다.
//		System.out.println(path); //--> 절대 경로가 출력됨
//		File basicfile = new File(path + "gs1DM_test.prn");	// 기본이 될 파일 경로
//		File savefile = new File("C:\\Users\\Metabiz0350\\Documents\\My Labels\\Labels\\savetest.prn");	// 새로 저장할 파일 경로
//		
//		String str = "";	//읽어온 파일을 저장
//		
//		//파일의 내용을 읽어서 저장하고 지정 경로에 새파일로 저장(복사)
//		try {
//			FileInputStream fis = new FileInputStream(basicfile);
//			FileOutputStream fos = new FileOutputStream(savefile);
//
//			//파일읽기
//			int c;	//읽어온 데이터를 저장할 변수
//			while((c=fis.read()) != -1) {
//				str += (char)c;
//			}
//			fis.close();
//			
//			System.out.println(str);
//			//읽어온 파일의 바코드
//			
//			
//			//파일저장 
//			byte[] strToBytes = str.getBytes();
//			fos.write(strToBytes);
//			fos.close();
//			
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//			System.out.println("파일이 업슴니다");
//		}
//		catch (IOException e) {
//			e.printStackTrace();
//			System.out.println("입출력오류");
//		}
	}

}
