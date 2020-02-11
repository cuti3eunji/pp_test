package ui;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class AddPanelTest extends JPanel{
	
	
	public AddPanelTest() {
		this.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
		this.setBorder(new LineBorder(Color.yellow));
		
		String[] prod = new String[] { "이지엔6이브연질캡슐", "타이레놀어쩌구", "두통약어쩌구" };
		JComboBox<String> gtinCombo = new JComboBox<String>(prod);
		
		this.add(new JLabel("표준코드(GTIN)"));   
		this.add(gtinCombo);
	}
	
}
