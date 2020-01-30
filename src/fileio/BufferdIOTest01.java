package fileio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class BufferdIOTest01 {

	public static void main(String[] args) {
		
//		File path = new File(".");
//		System.out.println(path.getAbsolutePath());
		
		String path = BufferdIOTest01.class.getResource("").getPath(); // 현재 클래스의 절대 경로를 가져온다.
		System.out.println(path); //--> 절대 경로가 출력됨
//		File fileInSamePa0ckage = new File(path + "test.txt"); // path 폴더 내의 test.txt 를 가리킨다.

		
//		File file = new File("C:\\Users\\Metabiz0350\\Documents\\My Labels\\Labels\\gs1DM_test.prn");
//		
//		System.out.println("파일명 : " + file.getName());
//		System.out.println("파일 여부 : " + file.isFile());
//		System.out.println("디렉토리(폴더) 여부 : " + file.isDirectory());
//		System.out.println(file.getPath());
//		System.out.println(file.getAbsolutePath());
//		System.out.println(file.length() + "byte(s)");
//		System.out.println();
//		
//		String str = "";	//읽어온 파일을 저장할 객체
//		
//		//파일의 내용을 읽어서 콘솔에 출력하기
//		try {
//			//파일과 연결된 바이트 스트림객체 생성
//			FileInputStream fis = new FileInputStream(file);
//			
//			int c;	//읽어온 데이터를 저장할 변수
//			while((c=fis.read()) != -1) {
//				//읽어온 데이터 콘솔에 출력
//				System.out.print((char)c);
//				str += (char)c;
//			}
//			
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//			System.out.println("파일이 업슴");
//		}
//		catch (IOException e) {
//			e.printStackTrace();
//			System.out.println("입출력오류");
//		}
//		
//		System.out.println();
//		System.out.println();
//		System.out.println();
//		System.out.println(str);
//		
////		FileOutputStream fos = new FileOutputStream(str);
//				
//
//				
//				
////		try {
////			//문자 기반의 파일 입력용 스트림 개체 생성
////			FileReader fr = new FileReader(file);
////			
////			int c;
////			
////			while((c=fr.read())!=-1){
////				System.out.print((char)c);
////			}
////			fr.close();
////			
////		} catch (IOException e) {
////			// TODO: handle exception
////		}
//		
//		
		
		
	}

}
