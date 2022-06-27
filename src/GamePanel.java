import java.awt.GridLayout;

import javax.swing.JPanel;

public class GamePanel extends JPanel {
	int COLUMNS, ROWS;
	NumSquare numbers[][];
	
	public GamePanel(int xSize, int ySize) {
		this.init(xSize, ySize);
	}
	
	void init(int xSize, int ySize) {
		removeAll();
		COLUMNS = xSize;
		ROWS = ySize;
		setLayout(new GridLayout(ROWS, COLUMNS));
		numbers = new NumSquare[COLUMNS][ROWS];
		for (int row = 0; row < ROWS; row++) {
		 for(int col = 0; col < COLUMNS; col++) {
		 numbers[col][row] = new NumSquare(0);
		 add(numbers[col][row]);
		 }
		}
	}
	
	int getValue(int row, int column) {
		return numbers[row][column].getValue();
	}
	
	void setValue(int row, int column, int value) {
		numbers[row][column].setValue(value);
	}
}
