package print;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.MediaSizeName;

public class ImagePrintTest {
	protected Image printImage;

	public ImagePrintTest(String fileName) {
		printImage = new javax.swing.ImageIcon(fileName).getImage(); // 이미지파일생성
		Paper p = new Paper();

		// 출력될 영역 설정
		p.setImageableArea(1 * 72, // Left margin 1 inch //for mm 0.0395
				1.5 * 72, // Top margin 1.5 inches
				6.5 * 72, // Width 6.5 inches
				8 * 72); // Height 8 inches
		PageFormat format = new PageFormat();
		format.setPaper(p); // 페이지포맷 페이지영역설정을 인자로..
		PrinterJob pj = PrinterJob.getPrinterJob();
		pj.setPrintable(new MyPrintable(), format);
		
		PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
        //aset.add(new Copies(1));
        aset.add(MediaSizeName.ISO_A4);
		
		try {
			pj.print(aset);
		} catch (PrinterException pe) {
			System.out.println("Printingfailed : " + pe.getMessage());
		}
	}

	class MyPrintable implements Printable {
		public int print(Graphics g, PageFormat pf, int pageIndex) {
			g.translate((int) (pf.getImageableX()), (int) (pf.getImageableY()));

			if (pageIndex == 0) {
				g.drawImage(printImage, 0, 0, null);
				return Printable.PAGE_EXISTS;
			}
			return Printable.NO_SUCH_PAGE;
		}
	}
}
