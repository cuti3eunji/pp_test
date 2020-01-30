package barcode4j;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.krysalis.barcode4j.BarcodeClassResolver;
import org.krysalis.barcode4j.DefaultBarcodeClassResolver;
import org.krysalis.barcode4j.impl.AbstractBarcodeBean;
import org.krysalis.barcode4j.impl.code39.Code39;
import org.krysalis.barcode4j.impl.code39.Code39Bean;
import org.krysalis.barcode4j.impl.code39.Code39LogicImpl;
import org.krysalis.barcode4j.output.java2d.Java2DCanvasProvider;

/**
 * JPanel의 canvas에 바코드를 그리기
 * @author kdarkdev
 */
public class DrawCanvasBarcodeImage extends JPanel {

	private static final long serialVersionUID = -4733771243760787113L;

	public static void main(String args[]) {
		/* swing jframe 생성하고 띄우기 */
		JFrame frame = new JFrame("Barcode Draw Test");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(new DrawCanvasBarcodeImage());
		frame.setSize(400, 500);
		frame.setVisible(true);
	}
	
	/**
	 * 바코드 생성전 데이터 초기화
	 * @param g
	 */
	private void init (Graphics g) {
		/* 바코드 타입 
		 * "codabar", "code39", "postnet", "intl2of5", "ean-128"
		 * "royal-mail-cbc", "ean-13", "itf-14", "datamatrix", "code128"
		 * "pdf417", "upc-a", "upc-e", "usps4cb", "ean-8", "ean-13" */
		String barcodeType = "datamatrix";

		/* 바코드 데이터 */
		String barcodeData = "8901138509231";

		/* 좌표 */
		int x = 100;
		int y = 50;
		
		/* canvas에 표현될 바코드의 scale */
		int scaleX = 7;
		int scaleY = 7;

		try {
			createBarcode(g, barcodeType, barcodeData, x, y, scaleX, scaleY);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void paintComponent(Graphics g) {
		init(g);
	}

	/**
	 * 바코드 생성
	 * 
	 * @param barcodeType
	 * @param barcodeData
	 * @param scaleY 
	 * @param scaleX 
	 * @param dpi
	 */
	private void createBarcode(Graphics g, String barcodeType, String barcodeData, int x, int y,  int scaleX, int scaleY) throws Exception {
		AbstractBarcodeBean bean = null;
		
		BarcodeClassResolver resolver = new DefaultBarcodeClassResolver();
		Class clazz = resolver.resolveBean(barcodeType);
		bean = (AbstractBarcodeBean) clazz.newInstance();
		bean.doQuietZone(true);

		Graphics2D g2d = (Graphics2D) g;
		g2d.translate(x, y); 			/* 좌표 지정 */
		g2d.scale(scaleX, scaleY); 				/* 크기 지정 */

		Java2DCanvasProvider j2dp = new Java2DCanvasProvider(g2d, 0);
		bean.generateBarcode(j2dp, barcodeData);
	}

}