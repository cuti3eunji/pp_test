package fileio;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/*
	'e:/D_Other/dog.png'파일을
	'd:/D_Other/연습용'폴더에 'dog_복사본.png'라는 이름으로 복사하는 프로그램 작성하기
*/

public class FileCopyTest {

	public static void main(String[] args) {
		try {
			//복사할 파일 스트림 객체 생성(입력용)
//			FileInputStream isf = new FileInputStream("E:/D_Other/dog.png");
			FileInputStream isf = new FileInputStream("C:\\Users\\Metabiz0350\\Documents\\My Labels\\Labels\\gs1DM_test.prn");
			BufferedInputStream bis = new BufferedInputStream(isf);
			
			//복사될 파일 스트림 객체 생성(출력용)
			FileOutputStream osf = new FileOutputStream("C:\\Users\\Metabiz0350\\Documents\\My Labels\\Labels\\gs1DM_test1.prn");
			BufferedOutputStream bos = new BufferedOutputStream(osf);
			
			int c;
			
			long startTime = System.currentTimeMillis();

//			while((c=isf.read())!=-1){
//				osf.write(c);
//			}
//			osf.flush();
	
			
			while((c=bis.read())!=-1){
				bos.write(c);
			}
			bos.flush();
//			
			System.out.println("복사완료");
			
//			isf.close();
//			osf.close();

			bis.close();
			bos.close();
			long endTime = System.currentTimeMillis();
			System.out.println("경과시간 : " + (endTime - startTime));


		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}

}
