package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.plaf.basic.BasicTabbedPaneUI;

public class TabUI extends BasicTabbedPaneUI{
	
	@Override
	protected void paintTabBorder(Graphics g, int tabPlacement, int tabIndex, int x, int y, int w, int h,
			boolean isSelected) {
		// 보통 보이는 부분 여기서 그려주고
		g.setColor(Color.white);
		g.drawRoundRect(x, y, w, h, 20, 20);
		if (isSelected) {
			//여기는 선택시 보여주는 부분을 그려주면 됩니다.   
			g.setColor(Color.magenta);
			g.drawLine(x + 4, y + 4, x + w - 4, y + 4);
		}
	}
	
	@Override
	protected void paintTabBackground(Graphics g, int tabPlacement, int tabIndex, int x, int y, int w, int h,
			boolean isSelected) {
		
		g.setColor(Color.white);
		
		if (isSelected) {
			g.setColor(Color.magenta);
			g.drawLine(x + 4, y + 4, x + w - 4, y + 4);
		}
	}

}	
