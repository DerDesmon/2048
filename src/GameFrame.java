import java.awt.Color;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
public class GameFrame extends JFrame {
	JPanel panelMain, panelButtons;
	GamePanel panelGame;
	JButton btnLeft, btnRight, btnUp, btnDown; 
	Board boardMain;
	public GameFrame(String title) {
		super(title);
		panelMain = new JPanel();
		panelMain.setBackground(Color.CYAN);
		this.add(panelMain);
		boardMain = new Board();
		panelGame = new GamePanel(4, 4);
		panelMain.add(panelGame);
		btnLeft = new JButton("slideLeft");
		btnLeft.addActionListener(e -> {
			if(boardMain.movePossible("Left") == true ) {
				boardMain.spawn();
			}
			this.updateNumSquares();
			panelGame.repaint();
		});
		btnRight = new JButton("slideRight");
		btnRight.addActionListener(e -> {
			if(boardMain.movePossible("Right") == true) {
				boardMain.spawn();
			}
			this.updateNumSquares();
			panelGame.repaint();
		});
		btnUp = new JButton("slideUp");
		btnUp.addActionListener(e -> {
			if(boardMain.movePossible("Up") == true ) {
				boardMain.spawn();
			}
			this.updateNumSquares();
			panelGame.repaint();
		});
		btnDown = new JButton("slideDown");
		btnDown.addActionListener(e -> {
			if(boardMain.movePossible("down") == true ) {
				boardMain.spawn();
			}
			this.updateNumSquares();
			panelGame.repaint();
			}
		);
		BoxLayout layoutMain = new BoxLayout(panelMain, BoxLayout.Y_AXIS);
		panelMain.setLayout(layoutMain);
		panelButtons = new JPanel();
		panelButtons.setBackground(Color.lightGray);
		panelButtons.setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridx = 0; constraints.gridy = 1;
		panelButtons.add(btnLeft, constraints);
		constraints.gridx = 2; constraints.gridy = 1;
		panelButtons.add(btnRight, constraints);
		constraints.gridx = 1; constraints.gridy = 0;
		panelButtons.add(btnUp, constraints);
		constraints.gridx = 1; constraints.gridy = 2;
		panelButtons.add(btnDown, constraints);
		panelMain.add(panelButtons);
		this.updateNumSquares();
	}
	public static void main(String[] agrs) {
		GameFrame frame = new GameFrame("2048");
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
		}
	
	void updateNumSquares() {
		for(int x = 0; x<4; x++) {
			for(int y=0; y<4; y++) {
				panelGame.setValue(x, y, boardMain.getTileValue(x, y));;
			}
		}
		panelGame.repaint();
	}
}
