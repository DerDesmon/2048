
import java.util.Random;
public class Board {
	Tile board[][];
	int score;
	boolean movable;
	
	public Board() {
		score = 0;
		this.board = new Tile[4][4];
		// Initialisiert neue Tiles mit dem Wert 0 f�r jedes Feld im Tile-Array
		for(int x = 0; x<4; x++) {
			for(int y=0; y<4; y++) {
				board[x][y] = new Tile();
			}
		}
		// Spawnt 2 Zweier beim Kunstruieren des Boards
		this.spawn2();
		this.spawn2();
	}
	
	public int getTileValue(int x, int y) {
		return board[x][y].getValue();
	}
	
	public int getScore() {
		return this.score;
	}
	
	
	public void spawn() {
		// Generiert zuf�llige Zahl zwischen 1 bis 10
		Random rand = new Random();
		if (rand.nextInt(10) == 0) {
			// Chance 1:10, dass Vier spawnt, ruft spawn4() auf
			this.spawn4();
		}
		else {
			// Chance 9:10, dass Zwei spawnt, ruft spawn2() auf
			this.spawn2();
		}
	}
	
	public void spawn2() {
		// Generiert zwei Zuffalszahlen zwischen 1 bis 4, die sp�ter f�r die Reihen- und Spaltenposition im Array stehen
		Random rand = new Random();
		int randomX = rand.nextInt(4);
		int randomY = rand.nextInt(4);
		if (this.board[randomX][randomY].getValue() == 0) { // �berpr�ft, ob Tile in der Arrayposition existiert (entspricht != 0)
			// Wenn ja: Erzeuge neues Tile an der Postion
			this.board[randomX][randomY] = new Tile(2);
		}
		else {
			// Wenn nein: rufe dieselbe Methode rekursiv auf, damit neue Positon zum Spawnen gefunden wird
			this.spawn2();
		}
	}
	
	public void spawn4() {
		// Generiert zwei Zuffalszahlen zwischen 1 bis 4, die sp�ter f�r die Reihen- und Spaltenposition im Array stehen
		Random rand = new Random();
		int randomX = rand.nextInt(4);
		int randomY = rand.nextInt(4);
		if (this.board[randomX][randomY].getValue() == 0) { // �berpr�ft, ob Tile in der Arrayposition existiert (entspricht != 0)
			// Wenn ja: Erzeuge neues Tile an der Postion
			this.board[randomX][randomY] = new Tile(4);
		}
		else {
			// Wenn nein: rufe dieselbe Methode rekursiv auf, damit neue Positon zum Spawnen gefunden wird
			this.spawn4();
		}
	}
	
	public boolean blackOut() {	// �berpr�ft ob das Feld voll mit Tiles ist
		for(int x = 0; x<4; x++) {
			for(int y=0; y<4; y++) {
				if(this.board[x][y].getValue() == 0) {
					return false;	// Wenn an einer Position ein Tile existiert, wird false zur�ckgegeben
				}
			}
		}
		return true; // Existiert kein freies Feld, wird true zur�ckgegeben.
	}	
	
	public boolean gameOver() { // �berpr�ft ob das Spiel zu Ende ist
		if (this.blackOut() == true && this.checkUp()== false && this.checkDown()== false && this.checkLeft()== false && this.checkRight()== false) {
			return true; // Ist das feld voll und alle Bewegungsrichtung Methoden f�hren zu kiener Ver�nderung des Spielfeldes, wird true zur�ckgegeben
		}
		else {
			return false;
		}
	}
	
	public boolean up() { // Bewegt alle Tiles, au�er die schon am oberen Rand liegen, nach oben, bis sie auf eine anderes Tile treffen. Falls zwei gleichwertige Tiles zusammentreffen, addieren sich ihre Werte. Zudem wird �berpr�ft ob sich das Spielfeld �ndert
		movable = false; // Spilefeld�nderung wird anfangs auf false gesetzt, kann sich mit Tileaktionen ver�ndern
		for(int x = 0; x<4; x++) {
			for(int y = 1; y<4; y++) { // Geht zuerst Reihen und dann Spalten durch, f�ngt in der Reihe unter dem Rand an
				Tile thisTile = this.board[x][y]; // Speichert aktuelles Tile mit dem agiert wird
				if (y != 0 && thisTile.getValue() != 0) { // Agieren mit Tile nur, wenn Tile  existiert und sich nicht am Rand befindet
					Tile upTile = this.board[x][y-1]; // Speichert oben liegendes Tile mit dem agiert wird
					if(upTile.getValue() == 0 && thisTile.getValue() != -1) { // Unterschieden wird: 1: Ist �ber mir kein Feld und ich befinde mich nicht auf einem Bewegtmarker (Wert -1), Situation: Tile muss in Richtung Rand bewegt werde und �ber Tile befindet sich kein Tile
						upTile.setValue(thisTile.getValue()); // �bergebe Wert an oben liegenden Tile
						movable = true; // Setze Spielfeld�nderung auf true
						thisTile.setValue(-1); // Dieses Feld wird mit einem Bewegungsmarker versehen, denn eine Bewgung des Tiles ohne Addieren f�hrt dazu, dass das oben liegende Feld wieder aufgerufen wird. W�rde die gleiche Reihe wieder erreicht werden, erg�be sich eine Endlosschleife
						y = y-2; // Gelangt zu oben liegendem Feld, wenn for Durchgang zu Ende ist (+2, da nach Durchgang automatisch -1)
					}
					else if(upTile.getValue() == thisTile.getValue()) { // 2: Oben liegendes Feld hat den gleichen Wert, wie aktuelles Feld
						upTile.setValue(thisTile.getValue()*2); // Addieren und setzen des Wertes auf oben liegendem Feld
						score = score + thisTile.getValue()*2; // Score �ndert sich um Additionswert
						movable = true;	// Setze Spielfeld�nderung auf true
						thisTile.setValue(0); // Aktuelles Feld wurde bewegt und existiert deswegen nicht mehr
					}
					else if(thisTile.getValue() == -1) { // 3: Tile befindet sich auf Bewegungsmarker 
						thisTile.setValue(0); // Bewegungsmarker l�schen
					}
				}
			}
		}
		return movable; // Falls sich Spielfeldbewegung im Laufe des Durchlaufens aller Tiles nicht ver�ndert hat, wird false zur�ckgegeben
	}
	
	//Gleiche Funktionsweise wie bei der up-Methode, bewegt aber die Tiles nach unten
	public boolean down() {
		movable = false;
		for(int x = 0; x<4; x++) {
			for(int y = 2; y>-1; y--) {
				Tile thisTile = this.board[x][y];
				if (y != 3 && thisTile.getValue() != 0) {
					Tile downTile = this.board[x][y+1];
					if(downTile.getValue() == 0 && thisTile.getValue() != -1) {
						downTile.setValue(thisTile.getValue());
						movable = true;
						thisTile.setValue(-1);
						y = y+2;
					}
					else if(downTile.getValue() == thisTile.getValue()) {
						downTile.setValue(thisTile.getValue()*2);
						score = score + thisTile.getValue()*2;
						movable = true;
						thisTile.setValue(0);
					}
					else if(thisTile.getValue() == -1) {
						thisTile.setValue(0);
					}
				}
			}
		}
		return movable;
	}
	
	//Gleiche Funktionsweise wie bei der up-Methode, bewegt aber die Tiles nach links
	public boolean left() {
		movable = false;
		for(int y = 0; y<4; y++) {
			for(int x = 1; x<4; x++) {
				Tile thisTile = this.board[x][y];
				if (x != 0 && thisTile.getValue() != 0) {
					Tile leftTile = this.board[x-1][y];
					if(leftTile.getValue() == 0 && thisTile.getValue() != -1) {
						leftTile.setValue(thisTile.getValue());
						movable = true;
						thisTile.setValue(-1);
						x = x-2;
					}
					else if(leftTile.getValue() == thisTile.getValue()) {
						leftTile.setValue(thisTile.getValue()*2);
						score = score + thisTile.getValue()*2;
						movable = true;
						thisTile.setValue(0);
					}
					else if(thisTile.getValue() == -1) {
						thisTile.setValue(0);
					}
				}
			}
		}
		return movable;
	}
	
	//Gleiche Funktionsweise wie bei der up-Methode, bewegt aber die Tiles nach rechts
	public boolean right() {
		movable = false;
		for(int y = 0; y<4; y++) {
			for(int x = 2; x>-1; x--) {
				Tile thisTile = this.board[x][y];
				if (x != 3 && thisTile.getValue() != 0) {
					Tile rightTile = this.board[x+1][y];
					if(rightTile.getValue() == 0 && thisTile.getValue() != -1) {
						rightTile.setValue(thisTile.getValue());
						movable = true;
						thisTile.setValue(-1);
						x = x+2;
					}
					else if(rightTile.getValue() == thisTile.getValue()) {
						rightTile.setValue(thisTile.getValue()*2);
						score = score + thisTile.getValue()*2;
						movable = true;
						thisTile.setValue(0);
						
					}
					else if(thisTile.getValue() == -1) {
						thisTile.setValue(0);
					}
				}
			}
		}
		return movable;
	}	
	
	public boolean checkUp() {
		// �berpr�ft ob zwei Tiles zusammenschmelzen w�rden, wenn man sie nach oben bewegen w�rde, nur �berpr�fung und keine Ver�nderung des Spielfeldes
		movable = false;
		for(int x = 0; x<4; x++) {
			for(int y = 1; y<4; y++) {
				Tile thisTile = this.board[x][y];
				if (y != 0 && thisTile.getValue() != 0) {
					Tile upTile = this.board[x][y-1];
					
					if(upTile.getValue() == thisTile.getValue()) { // Zwei Tiles w�rden zusammenschmelzen
						movable = true; // setze Feld�nderung auf true
						
					}
					
				}
			}
		}
		return movable; // Falls sich Spielfeldbewegung im Laufe des Durchlaufens aller Tiles nicht ver�ndert hat, wird false zur�ckgegeben
	}
	
	//Gleiche Funktionsweise wie die CheckUp-Methode, pr�ft dasselbe aber f�r die Bewegung nach unten
	public boolean checkDown() {
		movable = false;
		for(int x = 0; x<4; x++) {
			for(int y = 2; y>-1; y--) {
				Tile thisTile = this.board[x][y];
				if (y != 3 && thisTile.getValue() != 0) {
					Tile downTile = this.board[x][y+1];
					
					if(downTile.getValue() == thisTile.getValue()) {
						
						movable = true;
						
					}
					
				}
			}
		}
		return movable;
	}
	
	//Gleiche Funktionsweise wie die CheckUp-Methode, pr�ft dasselbe aber f�r die Bewegung nach links
	public boolean checkLeft() {
		movable = false;
		for(int y = 0; y<4; y++) {
			for(int x = 1; x<4; x++) {
				Tile thisTile = this.board[x][y];
				if (x != 0 && thisTile.getValue() != 0) {
					Tile leftTile = this.board[x-1][y];
					
					if(leftTile.getValue() == thisTile.getValue()) {
			
						movable = true;
						
					}
					
				}
			}
		}
		return movable;
	}
	
	//Gleiche Funktionsweise wie die CheckUp-Methode, pr�ft dasselbe aber f�r die Bewegung nach rechts
	public boolean checkRight() {
		movable = false;
		for(int y = 0; y<4; y++) {
			for(int x = 2; x>-1; x--) {
				Tile thisTile = this.board[x][y];
				if (x != 3 && thisTile.getValue() != 0) {
					Tile rightTile = this.board[x+1][y];
					
					if(rightTile.getValue() == thisTile.getValue()) {
					
						movable = true;
						
					}
					
				}
			}
		}
		return movable;
	}	
}
