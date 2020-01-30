package funcTest;

import java.util.ArrayList;

public class CheckDigit {
	public static void main(String[] args) {
		
		//입력된 바코드 번호--- 13자리
		String codeNumStr = "8901138509231";
		
		//받아온 바코드의 마지막 자리(check digit)
		String cd = String.valueOf(codeNumStr.charAt(codeNumStr.length()-1));
//		System.out.println("cd ==> " + cd);
		
		ArrayList<Integer> oddList = new ArrayList<Integer>();
		ArrayList<Integer> evenList = new ArrayList<Integer>();
		
		for(int i=1; i<=codeNumStr.length()-1; i++) {
//		for(int i=1; i<=12; i++) {
			if(i%2 == 0) { //인덱스가 짝수면
				evenList.add(Integer.parseInt(String.valueOf(codeNumStr.charAt(i-1))));
			}else {
				oddList.add(Integer.parseInt(String.valueOf(codeNumStr.charAt(i-1))));
			}
		}
		
		System.out.println("홀수리스트 : " + oddList.toString());
		System.out.println("짝수리스트 : " + evenList.toString());
		
		int sum = 0;
		for(Integer odd : oddList) {
			sum += odd;
		}
//		System.out.println("oddsum : " + sum );
		for(Integer even : evenList) {
			sum += even*3;
		}
//		System.out.println("evensum : " + sum );
		
		int chkSum = 0;
		chkSum = 10-(sum%10);
		System.out.println(chkSum);
		
		if(chkSum == Integer.parseInt(cd)) {
			System.out.println("checkDigit 마자용");
		}else {
			System.out.println("checkDigit 틀려용");
		}
		
		//메소드로 만들어서 chkSum 리턴 / 파라미터 바코드값 
		// 검증이면 입력된바코드의 마지막 자리와 리턴값 비교
		// 생성이면 입력된바코드의 마지막 자리에 리턴값 추가
	}
	
	public int checkDigit(String barcodeNum) {
		return 0;
	};
}
