package fileio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class FileIO {
    public static void main(String[] args) {
    	
    	String path = FileIO.class.getResource("").getPath(); // 현재 클래스의 절대 경로를 가져온다.
		System.out.println(path); //--> 절대 경로가 출력됨
		File basicfile = new File(path + "gs1DM_test.prn");	// 기본이 될 파일 경로
    	
        System.out.println("InputStream으로 읽기");
        System.out.println(new String(readFile2(basicfile)));
        System.out.println("utf8.txt -- 읽기완료");

        try {
            System.out.println(new String(readFile2(basicfile),"euc-kr"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println("ansi.txt -- 읽기완료");
    }

   public static byte[] readFile2(File basicfile){
        FileInputStream fis=null;
        byte[] data = null;
        try {
            fis = new FileInputStream(basicfile);
            data = new byte[fis.available()];
            fis.read(data);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            try{
                if(fis!=null) fis.close();
            }catch(IOException e){ ; }
        }
        return data;
    }

}