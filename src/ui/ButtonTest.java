package ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.plaf.basic.BasicButtonUI;

/**
 * @see https://stackoverflow.com/a/14599176/230513
 * @see https://stackoverflow.com/a/11949899/230513
 */
public class ButtonTest {

    private void display() {
        JFrame f = new JFrame("Test");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setBackground(new Color(0xfff0f0f0));
        f.setLayout(new GridLayout(0, 1));
        f.add(createToolBar(f));
        f.add(variantPanel("mini"));
        f.add(variantPanel("small"));
        f.add(variantPanel("regular"));
        f.add(variantPanel("large"));
        JPanel customPanel = new JPanel();
        customPanel.add(createCustom("One"));
        customPanel.add(createCustom("Two"));
        customPanel.add(createCustom("Three"));
        f.add(customPanel);
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }

    private static JPanel variantPanel(String size) {
        JPanel variantPanel = new JPanel();
        variantPanel.add(createVariant("One", size));
        variantPanel.add(createVariant("Two", size));
        variantPanel.add(createVariant("Three", size));
        return variantPanel;
    }

    private static JButton createVariant(String name, String size) {
        JButton b = new JButton(name);
        b.putClientProperty("JComponent.sizeVariant", size);
        return b;
    }

    private static JButton createCustom(String name) {
        JButton b = new JButton(name) {

            @Override
            public void updateUI() {
                super.updateUI();
                setUI(new CustomButtonUI());
            }
        };
        return b;
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                new ButtonTest().display();
            }
        });
    }

    private static class CustomButtonUI extends BasicButtonUI {

        private static final Color BACKGROUND_COLOR = new Color(173, 193, 226);
        private static final Color SELECT_COLOR = new Color(102, 132, 186);

        @Override
        protected void paintText(Graphics g, AbstractButton b, Rectangle r, String t) {
            super.paintText(g, b, r, t);
            g.setColor(SELECT_COLOR);
            g.drawRect(r.x, r.y, r.width, r.height);
        }

        @Override
        protected void paintFocus(Graphics g, AbstractButton b,
            Rectangle viewRect, Rectangle textRect, Rectangle iconRect) {
            super.paintFocus(g, b, viewRect, textRect, iconRect);
            g.setColor(Color.blue.darker());
            g.drawRect(viewRect.x, viewRect.y, viewRect.width, viewRect.height);
        }

        @Override
        protected void paintButtonPressed(Graphics g, AbstractButton b) {
            if (b.isContentAreaFilled()) {
                g.setColor(SELECT_COLOR);
                g.fillRect(0, 0, b.getWidth(), b.getHeight());
            }
        }

        @Override
        protected void installDefaults(AbstractButton b) {
            super.installDefaults(b);
            b.setFont(b.getFont().deriveFont(11f));
            b.setBackground(BACKGROUND_COLOR);
        }

        public CustomButtonUI() {
            super();
        }
    }

    private static JToolBar createToolBar(final Component parent) {
        final UIManager.LookAndFeelInfo[] available =
            UIManager.getInstalledLookAndFeels();
        List<String> names = new ArrayList<String>();
        for (UIManager.LookAndFeelInfo info : available) {
            names.add(info.getName());
        }
        final JComboBox combo = new JComboBox(names.toArray());
        String current = UIManager.getLookAndFeel().getName();
        combo.setSelectedItem(current);
        combo.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                int index = combo.getSelectedIndex();
                try {
                    UIManager.setLookAndFeel(
                        available[index].getClassName());
                    SwingUtilities.updateComponentTreeUI(parent);
                } catch (Exception e) {
                    e.printStackTrace(System.err);
                }
            }
        });
        JToolBar bar = new JToolBar("L&F");
        bar.setLayout(new FlowLayout(FlowLayout.LEFT));
        bar.add(combo);
        return bar;
    }
}