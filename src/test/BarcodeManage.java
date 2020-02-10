package test;

public class BarcodeManage {

	public static void main(String[] args) {
		
		CharSequence st = Character.toString((char) 232);
		CharSequence gs = Character.toString((char) 29);
		
//		String scanData = "01088099999999971720111110aaaaaaaa"+gs+"21bbbbbbb";
//		String scanData = "17201111010880999999999710aaaaaaaa"+gs+"21bbbbbbb";
		String scanData = "10aaaaaaaa"+gs+"21bbbbbbb"+gs+"010880999999999717201111";
//		String scanData = "010880999999999710aaaaaaaa"+gs+"1720111121bbbbbbb";
		
		System.out.println("scanData원본 : " + scanData);
		
		//맨앞 AI 확인
		String firAI = scanData.substring(0,2);
		System.out.println("첫번째AI:" + firAI);
		
		String fir = null;
		if(firAI.equals("01")) { //(01)이면 : GTIN
			
			fir = scanData.substring(2,16);
		}else if(firAI.equals("17")) { //(17)이면 : exp
			
			fir = scanData.substring(2,8);
		}else if(firAI.equals("10") || firAI.equals("21")) { //가변이면
			String[] aa = scanData.split(gs.toString());

			fir = aa[0].substring(2);
		}
		System.out.println("fir:"+fir);
		
		//두번째 AI확인
		String[] secArr = scanData.split(fir);

		String secAI = secArr[1].startsWith(gs.toString()) ? secArr[1].substring(1,3) : secArr[1].substring(0,2);
		System.out.println("두번째 AI:" + secAI);
		
		String sec = null;
		if(secAI.equals("01")) { //(01)이면 : GTIN
			sec = secArr[1].startsWith(gs.toString()) ? secArr[1].substring(3,17) : secArr[1].substring(2,16);
			
		}else if(secAI.equals("17")) { //(17)이면 : exp

			sec = secArr[1].startsWith(gs.toString()) ? secArr[1].substring(3,9) : secArr[1].substring(2,8);
			
		}else if(secAI.equals("10") || secAI.equals("21")) { //가변이면
			
			String[] aa = secArr[1].startsWith(gs.toString()) ? secArr[1].substring(1).split(gs.toString()) : secArr[1].split(gs.toString());
			
			sec = aa[0].substring(2);
		}
		System.out.println("sec:"+sec);
		
		
		//세번째 AI확인
		String[] thiArr = scanData.split(sec);
		
		String thiAI = thiArr[1].startsWith(gs.toString()) ? thiArr[1].substring(1,3) : thiArr[1].substring(0,2);
		System.out.println("세번째 AI:" + thiAI);
		
		String thi = null;
		if(thiAI.equals("01")) { //(01)이면 : GTIN
			thi = thiArr[1].startsWith(gs.toString()) ? thiArr[1].substring(3,17) : thiArr[1].substring(2,16);
			
		}else if(thiAI.equals("17")) { //(17)이면 : exp
			
			thi = thiArr[1].startsWith(gs.toString()) ? thiArr[1].substring(3,9) : thiArr[1].substring(2,8);
			
		}else if(thiAI.equals("10") || thiAI.equals("21")) { //가변이면
			String[] aa = thiArr[1].startsWith(gs.toString()) ? thiArr[1].substring(1).split(gs.toString()) : thiArr[1].split(gs.toString());

			thi = aa[0].substring(2);
		}
		System.out.println("thi:"+thi);
		
		
		//세번째 AI확인
		String[] fourArr = scanData.split(thi);
		
		String fourAI = fourArr[1].startsWith(gs.toString()) ? fourArr[1].substring(1,3) : fourArr[1].substring(0,2);
		System.out.println("네번째 AI:" + fourAI);
		
		String four = null;
		if(fourAI.equals("01")) { //(01)이면 : GTIN
			four = fourArr[1].startsWith(gs.toString()) ? fourArr[1].substring(3,17) : fourArr[1].substring(2,16);
			
		}else if(fourAI.equals("17")) { //(17)이면 : exp
			
			four = fourArr[1].startsWith(gs.toString()) ? fourArr[1].substring(3,9) : fourArr[1].substring(2,8);
			
		}else if(fourAI.equals("10") || fourAI.equals("21")) { //가변이면
			
			String[] aa = fourArr[1].startsWith(gs.toString()) ? fourArr[1].substring(1).split(gs.toString()) : fourArr[1].split(gs.toString());
			
			four = aa[0].substring(2);
		}
		System.out.println("four:"+four);
		
		System.out.println("==========================================================================================");
		System.out.println("첫번째 ai : " + firAI + ", 첫번째데이터 : " + fir );
		System.out.println("두번째 ai : " + secAI + ", 두번째데이터 : " + sec );
		System.out.println("세번째 ai : " + thiAI + ", 세번째데이터 : " + thi );
		System.out.println("네번째 ai : " + fourAI + ", 네번째데이터 : " + four );
		
		
		
	}

}
