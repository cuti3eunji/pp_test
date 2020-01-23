package barcode4jtest;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.MediaPrintableArea;
import javax.print.attribute.standard.OrientationRequested;
import javax.print.attribute.standard.PrinterResolution;
import javax.swing.UIManager;

import org.krysalis.barcode4j.BarcodeClassResolver;
import org.krysalis.barcode4j.DefaultBarcodeClassResolver;
import org.krysalis.barcode4j.impl.AbstractBarcodeBean;
import org.krysalis.barcode4j.output.java2d.Java2DCanvasProvider;

/**
 * 바코드를 라벨프린터로 출력하기 
 * (sewoo의 LK-B30 모델로 테스트 되었음.
 *  LK-B30의 경우 드라이버를 설치한후 인쇄기본속성에서 용지를 100 * 200으로 맞춰야 제대로 출력됨
 * )
 * @author kdarkdev
 */
public class PrintBarcodeImage implements Printable {
	
	private int dpi 						 = 203; /* 프린터의 dpi */
	private OrientationRequested orientation = OrientationRequested.LANDSCAPE; /* 방향 설정 */
	private int printAreaWidth  			 = 100;	/* 프린트될 용지 크기 width (mm) */
	private int printAreaHeight 			 = 200; /* 프린트될 용지 크기 height (mm) */
	
	private boolean isPrintDialogOpen = false;		/* 인쇄시 다이얼로그를 open할것인가? */
	
	public static void main(String args[]) {
		new PrintBarcodeImage();
	}
	
	public PrintBarcodeImage () {
		PrinterJob job = PrinterJob.getPrinterJob();
	    job.setPrintable(this);
	    PrintRequestAttributeSet aset = printSetup();
	    
	    if(isPrintDialogOpen) {
		    boolean ok = job.printDialog(aset);
		    try {
		    	/* print dialog에서 확인 누르면 프린트 수행 */
		    	if(ok){
		    		job.print(aset);
		    	}
		    } catch (PrinterException e) {
		    }
	    } else {
	    	try {
		         job.print(aset);
		    } catch (PrinterException e) {
		    }
	    }
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
	
	/**
	 * 프린트 옵션 셋업
	 * @return
	 */
	private PrintRequestAttributeSet printSetup () {
		/* swing ui를 os기본으로 변경 */
		String cn = UIManager.getSystemLookAndFeelClassName();
        try {
			UIManager.setLookAndFeel(cn);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
		/* 설정 기본값 셋 */
		aset.add(orientation);
		aset.add(new MediaPrintableArea(0, 0, printAreaWidth, printAreaHeight, MediaPrintableArea.MM));
		aset.add(new PrinterResolution(dpi, dpi, PrinterResolution.DPI));
		return aset;
	}
	
	@Override
	public int print(Graphics graphics, PageFormat pageFormat, int pageIndex)
			throws PrinterException {
		
		if (pageIndex > 0) { /* We have only one page, and 'page' is zero-based */
            return NO_SUCH_PAGE;
        }
	    
	    Graphics2D g2d = (Graphics2D)graphics;
	    
	    /* 안티앨리어싱, 퀄리티우선, 랜더링방법 설정 */
	    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	    g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
	    g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	    
	    /* javascript에서 넘어온 프린트 데이터들을 파싱하여 지정된 좌표와 옵션에 맞춰서 프린트 한다. */
	    init(g2d);
        
	    g2d.dispose();
        /* tell the caller that this page is part of the printed document */
        return PAGE_EXISTS;
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