
import java.util.Random;
public class Board {
	Tile board[][];
	int score;
	boolean movable;
	
	public Board() {
		score = 0;
		this.board = new Tile[4][4];
		// Initialisiert neue Tiles mit dem Wert 0 für jedes Feld im Tile-Array
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
		// Generiert zufällige Zahl zwischen 1 bis 10
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
		// Generiert zwei Zuffalszahlen zwischen 1 bis 4, die später für die Reihen- und Spaltenposition im Array stehen
		Random rand = new Random();
		int randomX = rand.nextInt(4);
		int randomY = rand.nextInt(4);
		if (this.board[randomX][randomY].getValue() == 0) { // Überprüft, ob Tile in der Arrayposition existiert (entspricht != 0)
			// Wenn ja: Erzeuge neues Tile an der Postion
			this.board[randomX][randomY] = new Tile(2);
		}
		else {
			// Wenn nein: rufe dieselbe Methode rekursiv auf, damit neue Positon zum Spawnen gefunden wird
			this.spawn2();
		}
	}
	
	public void spawn4() {
		// Generiert zwei Zuffalszahlen zwischen 1 bis 4, die später für die Reihen- und Spaltenposition im Array stehen
		Random rand = new Random();
		int randomX = rand.nextInt(4);
		int randomY = rand.nextInt(4);
		if (this.board[randomX][randomY].getValue() == 0) { // Überprüft, ob Tile in der Arrayposition existiert (entspricht != 0)
			// Wenn ja: Erzeuge neues Tile an der Postion
			this.board[randomX][randomY] = new Tile(4);
		}
		else {
			// Wenn nein: rufe dieselbe Methode rekursiv auf, damit neue Positon zum Spawnen gefunden wird
			this.spawn4();
		}
	}
	
	public boolean blackOut() {	// Überprüft ob das Feld voll mit Tiles ist
		for(int x = 0; x<4; x++) {
			for(int y=0; y<4; y++) {
				if(this.board[x][y].getValue() == 0) {
					return false;	// Wenn an einer Position ein Tile existiert, wird false zurückgegeben
				}
			}
		}
		return true; // Existiert kein freies Feld, wird true zurückgegeben.
	}	
	
	public boolean gameOver() { // Überprüft ob das Spiel zu Ende ist
		if (this.blackOut() == true && this.checkUp()== false && this.checkDown()== false && this.checkLeft()== false && this.checkRight()== false) {
			return true; // Ist das feld voll und alle Bewegungsrichtung Methoden führen zu kiener Veränderung des Spielfeldes, wird true zurückgegeben
		}
		else {
			return false;
		}
	}
	
	public boolean up() { // Bewegt alle Tiles, außer die schon am oberen Rand liegen, nach oben, bis sie auf eine anderes Tile treffen. Falls zwei gleichwertige Tiles zusammentreffen, addieren sich ihre Werte. Zudem wird überprüft ob sich das Spielfeld ändert
		movable = false; // Spilefeldänderung wird anfangs auf false gesetzt, kann sich mit Tileaktionen verändern
		for(int x = 0; x<4; x++) {
			for(int y = 1; y<4; y++) { // Geht zuerst Reihen und dann Spalten durch, fängt in der Reihe unter dem Rand an
				Tile thisTile = this.board[x][y]; // Speichert aktuelles Tile mit dem agiert wird
				if (y != 0 && thisTile.getValue() != 0) { // Agieren mit Tile nur, wenn Tile  existiert und sich nicht am Rand befindet
					Tile upTile = this.board[x][y-1]; // Speichert oben liegendes Tile mit dem agiert wird
					if(upTile.getValue() == 0 && thisTile.getValue() != -1) { // Unterschieden wird: 1: Ist über mir kein Feld und ich befinde mich nicht auf einem Bewegtmarker (Wert -1), Situation: Tile muss in Richtung Rand bewegt werde und über Tile befindet sich kein Tile
						upTile.setValue(thisTile.getValue()); // Übergebe Wert an oben liegenden Tile
						movable = true; // Setze Spielfeldänderung auf true
						thisTile.setValue(-1); // Dieses Feld wird mit einem Bewegungsmarker versehen, denn eine Bewgung des Tiles ohne Addieren führt dazu, dass das oben liegende Feld wieder aufgerufen wird. Würde die gleiche Reihe wieder erreicht werden, ergäbe sich eine Endlosschleife
						y = y-2; // Gelangt zu oben liegendem Feld, wenn for Durchgang zu Ende ist (+2, da nach Durchgang automatisch -1)
					}
					else if(upTile.getValue() == thisTile.getValue()) { // 2: Oben liegendes Feld hat den gleichen Wert, wie aktuelles Feld
						upTile.setValue(thisTile.getValue()*2); // Addieren und setzen des Wertes auf oben liegendem Feld
						score = score + thisTile.getValue()*2; // Score ändert sich um Additionswert
						movable = true;	// Setze Spielfeldänderung auf true
						thisTile.setValue(0); // Aktuelles Feld wurde bewegt und existiert deswegen nicht mehr
					}
					else if(thisTile.getValue() == -1) { // 3: Tile befindet sich auf Bewegungsmarker 
						thisTile.setValue(0); // Bewegungsmarker löschen
					}
				}
			}
		}
		return movable; // Falls sich Spielfeldbewegung im Laufe des Durchlaufens aller Tiles nicht verändert hat, wird false zurückgegeben
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
		// Überprüft ob zwei Tiles zusammenschmelzen würden, wenn man sie nach oben bewegen würde, nur Überprüfung und keine Veränderung des Spielfeldes
		movable = false;
		for(int x = 0; x<4; x++) {
			for(int y = 1; y<4; y++) {
				Tile thisTile = this.board[x][y];
				if (y != 0 && thisTile.getValue() != 0) {
					Tile upTile = this.board[x][y-1];
					
					if(upTile.getValue() == thisTile.getValue()) { // Zwei Tiles würden zusammenschmelzen
						movable = true; // setze Feldänderung auf true
						
					}
					
				}
			}
		}
		return movable; // Falls sich Spielfeldbewegung im Laufe des Durchlaufens aller Tiles nicht verändert hat, wird false zurückgegeben
	}
	
	//Gleiche Funktionsweise wie die CheckUp-Methode, prüft dasselbe aber für die Bewegung nach unten
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
	
	//Gleiche Funktionsweise wie die CheckUp-Methode, prüft dasselbe aber für die Bewegung nach links
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
	
	//Gleiche Funktionsweise wie die CheckUp-Methode, prüft dasselbe aber für die Bewegung nach rechts
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
