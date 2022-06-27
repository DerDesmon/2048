import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class NumSquare extends JComponent{
	int SCALE = 100;
	int BORDER = SCALE/20;
	int FONT_SIZE = (int)(SCALE*0.4);
	Font FONT = new Font("Consolas", Font.PLAIN, FONT_SIZE);
	private int value;
	
	public NumSquare(int value) {
		this.value = value;
		this.setFont(FONT);
		this.setPreferredSize(new Dimension(SCALE, SCALE));
	}
	
	/*public static void main(String args[]) {
		 JFrame frame = new JFrame();
		 JPanel panel = new JPanel();
		 panel.add(new NumSquare(16));
		 frame.add(panel);
		 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 frame.pack();
		 frame.setVisible(true);
		 }*/
	
	public void paintComponent(Graphics g) {
		((Graphics2D)g).setRenderingHint(
				 RenderingHints.KEY_ANTIALIASING,
				 RenderingHints.VALUE_ANTIALIAS_ON);
		g.setColor(Color.white);
		g.fillRect(0, 0,this.getWidth(), this.getHeight());
		g.setColor(Color.blue);
		g.fillRoundRect(0, 0,this.getWidth(), this.getHeight(), SCALE/3, SCALE/3);
		Color color;
		if (value == 0) {
		 color = Color.CYAN;
		} else {
		 int len = Integer.numberOfTrailingZeros(value);
		 color = Color.getHSBColor(len / 11.0f, 0.8f, 0.5f);
		}
		g.setColor(color);
		g.setColor(Color.lightGray);
		FontMetrics metrics = g.getFontMetrics();
		String txt = Integer.toString(value);
		g.drawString(txt,
		 (getWidth() - metrics.stringWidth(txt)) / 2,
		 getHeight() / 2 + metrics.getAscent() / 3);
	}
	
	int getValue() {
		return this.value;
	}
	void setValue(int value) {
		this.value = value;
	}
	
}
