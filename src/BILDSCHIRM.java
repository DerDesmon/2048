



import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;




public class BILDSCHIRM  {
	JFrame frame;
   JLabel[][] labels;
   JLabel labelScore;
   Board board;
   KeyListener key;
 
   //startet das Programm, indem es einen Bildschirm erstellt
   public static void main(String [] args) {
	   BILDSCHIRM window=new BILDSCHIRM();
	   window.frame.setVisible(true);
	   }

public BILDSCHIRM() {
	//Ein JFrame wird initialisiert
	frame=new JFrame("2048");
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setSize(800,800);
	frame.setLayout(null);
	
	//KeyListener, um bei Tastendruck die entsprechende Methode aufzurufen
KeyListener key=new KeyListener() {
		
		public void keyTyped(KeyEvent e) {
			
			
		}

		@Override
		public void keyPressed(KeyEvent e) {

			switch(e.getKeyCode()) {
			//exemplarisch für alle 4 cases
			case 37://Pfeiltaste nach links wurde gedrückt
				if(board.gameOver() == false) { //Prüfen ob das Game vorbei ist, also kein Zug mehr möglich
				if(board.left() == true) { //Aufrufen der nach Links-Bewegen-Methode und durch Rückgabewert prüfen, ob ein Block nach links bewegt wurde
					board.spawn();//wenn ein Block bewegt wurde, wird ein neuer Block erstellt und das Spielfeld wird geupdatet
					update();}
			}
				else{gameOver();} //wenn kein Zug mehr möglich ist wird die GameOver Methode aufgerufen
				break;
			case 38: if(board.gameOver() == false) {
				if(board.up() == true) {
					board.spawn();
					update();
				};
			}
			else{gameOver();}
			break;
			case 39: if(board.gameOver() == false) {
				if(board.right() == true) {
					board.spawn();
					update();} }
			else{gameOver();}
			break;
			case 40: if(board.gameOver() == false) {
				if(board.down() == true) {
					board.spawn();
					update();} }
			else{gameOver();}
			break;
			
		}
			
		
			
		}

		@Override
		public void keyReleased(KeyEvent e) {
		
			
		}
	};
frame.addKeyListener(key);
frame.setFocusable(true);

	
	
//Die Methode zum aufbauen des Startbildschirms wird aufgerufen
this.startbildschirm();


}
//Diese Methode baut auf dem erstellten JFrame den Startbildschirm auf
public void startbildschirm() {

	//Damit der Startbildschirm immer wieder aufgebaut werden kann, auch nach Beenden eines Spiels, müssen alle eventuell vorher erstellten
	//Components auf dem Frame gelöscht werden
frame.getContentPane().removeAll();
frame.getContentPane().setBackground(Color.WHITE);
frame.repaint();

//Button zum Starten des Spiels
JButton buttonStart=new JButton();
buttonStart.setBounds(290, 325, 220, 100);
buttonStart.setText("START GAME");
buttonStart.setBackground(Color.LIGHT_GRAY);
buttonStart.setFont(new Font("Tahoma", Font.PLAIN, 30));
//Bei Druck auf den Button wird die spielbildschirm Methode aufgerufen,welche das Spiel startet
buttonStart.addActionListener(e->{
	this.spielbildschirm();
});

//Bild auf dem Startbildschirm
ImageIcon icon=new ImageIcon(getClass().getResource("2048.png"));
JLabel image=new JLabel();
image.setBounds(325,100,150,150);
image.setIcon(icon);

//Button zum Anzeigen einer kurzen Anleitung 
JButton buttonAnleitung=new JButton();
buttonAnleitung.setBounds(290,500,220,100);
buttonAnleitung.setText("ANLEITUNG");
buttonAnleitung.setBackground(Color.LIGHT_GRAY);
buttonAnleitung.setFont(new Font("Tahoma", Font.PLAIN, 30));
buttonAnleitung.addActionListener(e->{
	this.anleitung();
});



frame.add(buttonAnleitung);
frame.add(image);
frame.add(buttonStart);
}

//Diese Methode ersttellt auf dem Frame den Spielbildschirm, sowie das erforderliche Board zum Spielen
public void spielbildschirm() {
	board=new Board();
	//Zuerst müssen alle vorher vorhandenen Components gelöscht werden
	frame.getContentPane().removeAll();
	frame.getContentPane().setBackground(Color.WHITE);
frame.repaint();


	//Dieses Label markiert den Hintergrund für die Spielfläche
	JLabel labelh=new JLabel();
	labelh.setBackground(Color.lightGray);
	labelh.setOpaque(true);
	labelh.setBounds(40,70,700,730);

	
	
	//Array zum Speichern der Labels, damit man später darauf zugreifen kann
	labels=new JLabel[4][4];
	
	//Diese Variablen werden benötigt, um das Array in der for-Schleife mit Labeln zu füllen
		int x=0;
	   int y=-1;
	
	   //In dieser For-Schleife werden die Label für die Blöcke erstellt und in erstellten Array gespeichert, i und m setzen dabei die Position der Label fest
	for(int i=90;i<700;i=i+170) {
	    x=0;
		y++;
		for(int m=60;m<700;m=m+170) {

	JLabel label1_1=new JLabel();
	label1_1.setFont(new Font("Tahoma", Font.PLAIN, 50));
	label1_1.setVerticalAlignment(SwingConstants.CENTER);
	label1_1.setHorizontalAlignment(SwingConstants.CENTER);
	label1_1.setBounds(m,i,150,150);
	label1_1.setBackground(new Color(242, 242, 242));
	label1_1.setOpaque(true);
	frame.add(label1_1);
	labels[x][y]=label1_1;

	x++;
	}
	}
	


	

//Label, um den aktuellen Score anzuzeigen
	labelScore=new JLabel();
	labelScore.setBounds(200,10,300,40);
	labelScore.setBackground(Color.LIGHT_GRAY);
	labelScore.setOpaque(true);
	labelScore.setText("Score: 0");
	labelScore.setVerticalAlignment(SwingConstants.CENTER);
	labelScore.setHorizontalAlignment(SwingConstants.CENTER);
	labelScore.setFont(new Font("Tahoma", Font.PLAIN, 30));
	
//Button, damit der Spieler das Spiel beendet kann und ein neues Starten kann
	JButton buttonEnde=new JButton();
	buttonEnde.setBounds(600,10,150,30);
	buttonEnde.setBackground(Color.LIGHT_GRAY);
	buttonEnde.addActionListener(e->{
	this.gameOver();
	});
	buttonEnde.setText("ENDE");
	buttonEnde.setFont(new Font("Tahoma", Font.PLAIN, 20));
	
	frame.add(buttonEnde);
	
	frame.add(labelScore);
	frame.add(labelh);
	this.update();
	}

//Diese Methode erstellt den Anleitungsbildschrim auf dem Frame
public void anleitung() {
	//Zuerst müssen alle vorher vorhandenen Components gelöscht werden
	frame.getContentPane().removeAll();
	frame.getContentPane().setBackground(Color.WHITE);
    frame.repaint();

    //Erstellen der Bilder und zugehörigen Texte für die Anleitung
ImageIcon iconArrows=new ImageIcon(getClass().getResource("arrowkeys.jpg"));
JLabel imageArrows=new JLabel();
imageArrows.setBounds(100,100,214,150);
imageArrows.setIcon(iconArrows);

JLabel textArrows=new JLabel();
textArrows.setBounds(450,100,300,150);
textArrows.setText("<html><body>Benutzen Sie die Pfeiltasten,<br> um die Blöcke zu verschieben.</body></html>");
textArrows.setBackground(Color.LIGHT_GRAY);
textArrows.setFont(new Font("Tahoma", Font.PLAIN, 15));
textArrows.setVerticalAlignment(SwingConstants.CENTER);
textArrows.setHorizontalAlignment(SwingConstants.CENTER);

ImageIcon icon2Tiles=new ImageIcon(getClass().getResource("2Tiles.png"));

JLabel image2Tiles=new JLabel();
image2Tiles.setBounds(100,300,299,150);
image2Tiles.setIcon(icon2Tiles);

JLabel text2Tiles=new JLabel();
text2Tiles.setBounds(450,300,300,150);
text2Tiles.setText("<html><body>Dabei können Sie Blöcke zusammenführen.<br>Aus 2 mal 2 wird so 4. Dies gibt Punkte.</body></html>" );
text2Tiles.setBackground(Color.LIGHT_GRAY);
text2Tiles.setFont(new Font("Tahoma", Font.PLAIN, 15));
text2Tiles.setVerticalAlignment(SwingConstants.CENTER);
text2Tiles.setHorizontalAlignment(SwingConstants.CENTER);

ImageIcon icon2048=new ImageIcon(getClass().getResource("2048.png"));

JLabel image2048=new JLabel();
image2048.setBounds(100,500,150,150);
image2048.setIcon(icon2048);

JLabel text2048=new JLabel();
text2048.setBounds(450,500,300,150);
text2048.setText("<html><body>Ziel ist es dabe einen Blöcke mit möglichst<br>hohen Werten zu erschaffen und so einen <br>hohen Score zu erzielen.</body></html>");
text2048.setBackground(Color.LIGHT_GRAY);
text2048.setFont(new Font("Tahoma", Font.PLAIN, 15));
text2048.setVerticalAlignment(SwingConstants.CENTER);
text2048.setHorizontalAlignment(SwingConstants.CENTER);

//Button, um zum Startbildschirm zurückzugelangen
JButton back=new JButton();
back.setBounds(350,25,100,50);
back.setBackground(Color.LIGHT_GRAY);
back.setText("BACK");
back.setFont(new Font("Tahoma", Font.PLAIN, 15));
back.addActionListener(e->{
	this.startbildschirm();
});


frame.add(back);
frame.add(text2048);
frame.add(image2048);
frame.add(text2Tiles);
frame.add(image2Tiles);
frame.add(textArrows);
frame.add(imageArrows);
frame.setVisible(true);
}

//Diese Methode updatet das Spielfeld und ändert auf dem Board veränderte Wert auch visuell
public void update() {
	//for-Schleife iteriert durch alle Label hindurch
	for(int i=0;i<4;i++) {
		for(int m=0;m<4;m++) {
			if(board.getTileValue(i, m)!=0) {
    labels[i][m].setText(String.valueOf(board.getTileValue(i, m)));//Jedes Label bekommt als anzuzeigenden Text den aktuellen Wert des zugehörigen Tiles übergeben
    }
			else{labels[i][m].setText(null);}//Tiles mit dem Wert 0 übergeben dem Label keinen Inhalt, so bleiben diese Felder leer
			
			//Mit dieser switch-case-Struktur wird die Farbe jedes Blocks, dem aktuellen Wert angepasst
    switch(board.getTileValue(i, m)) {
    case 0: labels[i][m].setBackground(new Color(242, 242, 242));
    	break;
    case 2:labels[i][m].setBackground(new Color(238, 228, 218 ));
    	break;
    case 4:labels[i][m].setBackground(new Color(237, 224, 200));
    	break;
    case 8:labels[i][m].setBackground(new Color(242, 177, 121));
    	break;
    case 16:labels[i][m].setBackground(new Color(245, 149, 99));
    	break;
    case 32:labels[i][m].setBackground(new Color(246, 124, 95));
    	break;
    case 64:labels[i][m].setBackground(new Color(246, 94, 59 ));
    	break;
    case 128:labels[i][m].setBackground(new Color(237, 207, 114));
    	break;
    case 256:labels[i][m].setBackground(new Color(237, 204, 97));
    	break;
    case 512:labels[i][m].setBackground(new Color(237, 200, 80));
    	break;
    case 1024:labels[i][m].setBackground(new Color(237, 197, 63));
    	break;
    case 2048:labels[i][m].setBackground(new Color(237, 194, 46));
    	break;
    
    }
		}
	}
	//Zum Schluss wird der anzuzeigende Score aktualisiert
	labelScore.setText("Score:"+board.getScore());
}

//Diese Methode wird aufgerufen wenn das Spiel beendet ist, dem Spieler wird sein Endscore angezeigt und er kann zum Startbilschirm zurückkehren
public void gameOver() {
	//Alle vorher vorhandenen Components werden gelöscht
	frame.getContentPane().removeAll();
	frame.repaint();
	frame.getContentPane().setBackground(null);
	
	//Game Over Bild auf dem Frame
	ImageIcon iconGameOver=new ImageIcon(getClass().getResource("gameOver.png"));
	JLabel imageGameOver=new JLabel();
	imageGameOver.setBounds(150,150,500,113);
	imageGameOver.setIcon(iconGameOver);
	imageGameOver.setOpaque(true);
	
	//Dieses Label zeigt den Score an
	JLabel scoreAnzeigen=new JLabel();
	scoreAnzeigen.setBounds(200,300,400,200);
	scoreAnzeigen.setVerticalAlignment(SwingConstants.CENTER);
	scoreAnzeigen.setHorizontalAlignment(SwingConstants.CENTER);
	scoreAnzeigen.setText("Score:"+board.getScore());
	scoreAnzeigen.setBackground(null);
	scoreAnzeigen.setFont(new Font("Tahoma", Font.PLAIN, 30));
	
	//Mit diesem Button gelangt der Spieler durch Aufruf der startbildschirm-Methode auf den Startbildschirm zurück
	JButton buttonNewGame=new JButton();
	buttonNewGame.setBounds(300,550,200,100);
	buttonNewGame.setBackground(Color.LIGHT_GRAY);
	buttonNewGame.addActionListener(e->{
	this.startbildschirm();
	});
	buttonNewGame.setText("NEW GAME");
	buttonNewGame.setFont(new Font("Tahoma", Font.PLAIN, 20));
	buttonNewGame.setFocusable(false);
	
	frame.add(scoreAnzeigen);
	frame.add(buttonNewGame);
	frame.add(imageGameOver);
}



	
}



