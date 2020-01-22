package ui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Barcode4jTest {

	public static void main(String[] args) {
		Dimension dim = new Dimension(500,500);
		
		JFrame jf = new JFrame("Barcode4j Test");
		jf.setLocationRelativeTo(null);
		jf.setPreferredSize(dim);
		
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		panel.add(new JTextArea());
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		jf.add(panel, BorderLayout.CENTER);
		jf.pack();
		jf.setVisible(true);
	}

}
