package test;

public class BarcodeManage {

	public static void main(String[] args) {
		
		CharSequence st = Character.toString((char) 232);
		CharSequence gs = Character.toString((char) 29);
		
		String scanData = "01088099999999971720111110aaaaaaaa"+gs+"21bbbbbbb";
		String scanData2 = "17201111010880999999999710aaaaaaaa"+gs+"21bbbbbbb";
		String scanData3 = "10aaaaaaaa"+gs+"21bbbbbbb"+gs+"010880999999999717201111";
		String scanData4 = "010880999999999710aaaaaaaa"+gs+"1720111121bbbbbbb";
		
		System.out.println("scanData원본 : " + scanData);
		
		//맨앞 AI 확인
		String firAI = scanData3.substring(0,2);
		System.out.println("첫번째AI:" + firAI);
		
		String fir = null;
		if(firAI.equals("01")) { //(01)이면 : GTIN
//			System.out.println(scanData.substring(scanData.indexOf("01")+2, 16));
//			System.out.println(scanData.substring(2,16));
			
			fir = scanData.substring(2,16);
		}else if(firAI.equals("17")) { //(17)이면 : exp
//			System.out.println(scanData2.substring(2,8));
			
			fir = scanData2.substring(2,8);
		}else if(firAI.equals("10") || firAI.equals("21")) { //가변이면
			String[] aa = scanData3.split(gs.toString());
//			System.out.println(aa.length);
//			System.out.println(aa[0]);
//			System.out.println(aa[1]);
//			System.out.println(aa[2]);
//			
			fir = aa[0].substring(2);
		}
		
		//두번째 AI확인
		String[] secArr = scanData3.split(fir);

		String secAI = secArr[1].replace(gs, "").substring(0,2);
		System.out.println("두번째 AI : " + secAI);
		
		String sec = null;
		if(secAI.equals("01")) { //(01)이면 : GTIN
			
			sec = secArr[1].replace(gs, "").substring(2,16);
		}else if(secAI.equals("17")) { //(17)이면 : exp
			System.out.println(secArr[1].replace(gs, "").substring(2,8));
			
			sec = secArr[1].replace(gs, "").substring(2,8);
		}else if(secAI.equals("10") || secAI.equals("21")) { //가변이면
			String[] aa = secArr[1].startsWith(gs.toString()) ? secArr[1].substring(1).split(gs.toString()) : secArr[1].split(gs.toString());
			
			System.out.println(aa.length);
			System.out.println("0 " + aa[0]);
			System.out.println("1 " + aa[1]);
			System.out.println("2 " + aa[2]);
//			
//			sec = aa[0].substring(2);
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		String gtinS = scanData.substring(scanData.indexOf("01")+2, 16);
		System.out.println("gtin " + gtinS);///////////////-AI
		
//		infoArea.setText("표준코드 (01) : " + gtinS);
		
		String[] expArr = scanData.split(gtinS);
		
		String expS = expArr[1].substring(expArr[1].indexOf("17")+2, 8);
		System.out.println("exp " + expS);////////////-AI
		
//		infoArea.append("\n유효기한 (17) : " + expS) ;
		
		String[] lotArr = expArr[1].split(expS);
		
		String[] lotSer = lotArr[1].split(gs.toString());
		System.out.println("lot " + lotSer[0]);////////////////+AI
		System.out.println("ser " + lotSer[1]);///////////////////+AI
		
//		infoArea.append("\n제조번호 (10) : " + lotSer[0].substring(2));
//		infoArea.append("\n일련번호 (21) : " + lotSer[1].substring(2));
		
	}

}
