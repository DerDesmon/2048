import java.util.Random;
public class Board {
	Tile board[][];
	int score;
	boolean movable;
	public Board() {
		this.board = new Tile[4][4];
		for(int x = 0; x<4; x++) {
			for(int y=0; y<4; y++) {
				board[x][y] = new Tile();
			}
		}
		this.spawn2();
		this.spawn2();
	}
	
	public int getTileValue(int x, int y) {
		return board[x][y].getValue();
	}
	
	public int getScore() {
		return this.score;
	}
	
	public int getHighTile() {
		int highestValue = 0;
		for(int x = 0; x<4; x++) {
			for(int y=0; y<4; y++) {
				int value = this.board[x][y].getValue();
				if(value<highestValue) {
					highestValue = value;
				}
			}
		}
		return highestValue;
	}
	
	public void spawn() {
		Random rand = new Random();
		if (rand.nextInt(9) == 0) {
			this.spawn4();
		}
		else {
			this.spawn2();
		}
	}
	
	public void spawn2() {
		Random rand = new Random();
		int randomX = rand.nextInt(4);
		int randomY = rand.nextInt(4);
		if (this.board[randomX][randomY].getValue() == 0) {
			this.board[randomX][randomY] = new Tile(2);
		}
		else {
			this.spawn2();
		}
	}
	
	public void spawn4() {
		Random rand = new Random();
		int randomX = rand.nextInt(3);
		int randomY = rand.nextInt(3);
		if (this.board[randomX][randomY].getValue() == 0) {
			this.board[randomX][randomY] = new Tile(4);

		}
		else {
			this.spawn4();
		}
	}
	
	public boolean blackOut() {
		for(int x = 0; x<4; x++) {
			for(int y=0; y<4; y++) {
				if(this.board[x][y].getValue() == 0) {
					return false;
				}
			}
		}
		return true;
	}
	
	public boolean movePossible(String direction) {
		movable = false;
		if (direction.equalsIgnoreCase("up")) {
			this.up();
		}
		else if(direction.equalsIgnoreCase("down")) {
			this.down();
		}
		else if (direction.equalsIgnoreCase("left")) {
			this.left();
		}
		else {
			this.right();
		}
		return movable;
	}
	
	public boolean gameOver() {
		if (this.blackOut()) {
			this.up();
			this.down();
			this.left();
			this.right();
			return true;
		}
		else {
			return false;
		}
	}
	
	public void up() {
		for(int x = 0; x<4; x++) {
			for(int y = 1; y<4; y++) {
				if (y != 0) {
					Tile thisTile = this.board[x][y];
					Tile upTile = this.board[x][y-1];
					if(upTile.getValue() == 0 && thisTile.getValue() != -1 && thisTile.getValue() != 0) {
						upTile.setValue(thisTile.getValue());
						movable = true;
						thisTile.setValue(-1);
						y = y-2;
					}
					else if(upTile.getValue() == thisTile.getValue() && thisTile.getValue() != 0) {
						upTile.setValue(thisTile.getValue()*2);
						movable = true;
						thisTile.setValue(0);
					}
					else if(thisTile.getValue() == -1) {
						thisTile.setValue(0);
					}
				}
			}
		}
	}
	
	public void down() {
		for(int x = 0; x<4; x++) {
			for(int y = 2; y>-1; y--) {
				if (y != 3) {
					Tile thisTile = this.board[x][y];
					Tile downTile = this.board[x][y+1];
					if(downTile.getValue() == 0 && thisTile.getValue() != -1 && thisTile.getValue() != 0) {
						downTile.setValue(thisTile.getValue());
						movable = true;
						thisTile.setValue(-1);
						y = y+2;
					}
					else if(downTile.getValue() == thisTile.getValue() && thisTile.getValue() != 0) {
						downTile.setValue(thisTile.getValue()*2);
						movable = true;
						thisTile.setValue(0);
					}
					else if(thisTile.getValue() == -1) {
						thisTile.setValue(0);
					}
				}
			}
		}
	}
	
	public void left() {
		for(int y = 0; y<4; y++) {
			for(int x = 1; x<4; x++) {
				if (x != 0) {
					Tile thisTile = this.board[x][y];
					Tile leftTile = this.board[x-1][y];
					if(leftTile.getValue() == 0 && thisTile.getValue() != -1 && thisTile.getValue() != 0) {
						leftTile.setValue(thisTile.getValue());
						movable = true;
						thisTile.setValue(-1);
						x = x-2;
					}
					else if(leftTile.getValue() == thisTile.getValue() && thisTile.getValue() != 0) {
						leftTile.setValue(thisTile.getValue()*2);
						movable = true;
						thisTile.setValue(0);
					}
					else if(thisTile.getValue() == -1) {
						thisTile.setValue(0);
					}
				}
			}
		}
	}
	
	public void right() {
		for(int y = 0; y<4; y++) {
			for(int x = 2; x>-1; x--) {
				if (x != 3) {
					Tile thisTile = this.board[x][y];
					Tile rightTile = this.board[x+1][y];
					if(rightTile.getValue() == 0 && thisTile.getValue() != -1 && thisTile.getValue() != 0) {
						rightTile.setValue(thisTile.getValue());
						movable = true;
						thisTile.setValue(-1);
						x = x+2;
					}
					else if(rightTile.getValue() == thisTile.getValue() && thisTile.getValue() != 0) {
						rightTile.setValue(thisTile.getValue()*2);
						movable = true;
						thisTile.setValue(0);
						
					}
					else if(thisTile.getValue() == -1) {
						thisTile.setValue(0);
					}
				}
			}
		}
	}	
}
