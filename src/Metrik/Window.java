package Metrik;

import java.awt.*;
import java.awt.event.*;

import javax.swing.JTextField;

public class Window extends Frame implements MouseListener, WindowListener {
	private static final long serialVersionUID = 1L;
	
	int circlecount = 0;
	int radius = 100;
	Dialog dialog;
	MenuBar mb = new MenuBar();
	Menu menu2;
	int[][] circlecoords = new int[2][2];

	public Window() {
		// Mouse- und Windowlistener für später hinzufügen
		addMouseListener(this);
		addWindowListener(this);

		// Fenster konfigurieren und anzeigen
		setSize(500, 500);
		setVisible(true);
		setTitle("Metrik Version 1.3");

		// Menüleiste anlegen, MenuItems und Menu anlegen und sichtbare Namen zuweisen
		Menu menu = new Menu("Hintergrundfarbe");
		MenuItem blue = new MenuItem("Blau");
		MenuItem green = new MenuItem("Grün");
		MenuItem red = new MenuItem("Rot");
		MenuItem yellow = new MenuItem("Gelb");
		MenuItem white = new MenuItem("Weiß");

		// MenuItems dem Menu zuweisen
		menu.add(blue);
		menu.add(green);
		menu.add(red);
		menu.add(yellow);
		menu.add(white);

		// Jedem MenuItem einen ActionListener zuweisen
		blue.addActionListener(aliBlue);
		green.addActionListener(aliGreen);
		red.addActionListener(aliRed);
		yellow.addActionListener(aliYellow);
		white.addActionListener(aliWhite);

		// Menu2 für Version 1.2 anlegen
		menu2 = new Menu("Kreisradius: " + radius);
		MenuItem radius = new MenuItem("verändern");
		menu2.add(radius);
		radius.addActionListener(aliRadius);

		// Menüs an Menubar anheften und Menubar dem Frame-Fenster zuweisen
		mb.add(menu);
		mb.add(menu2);
		setMenuBar(mb);

		// Frame verschieben, damit es nich oben links in der Ecke hängt (QoL)
		setLocation(500, 300);
	}

	// Jeweils ein ActionListener pro MenuItem im Menu,
	// die jeweils die Hintergrundfarbe ändert und den Count zurücksetzt,
	// da beim Wechseln der Farbe alle Elemente im Fenster gelöscht werden
	
	
	ActionListener aliBlue = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			setBackground(Color.blue);
			circlecount = 0;
		}
	};

	ActionListener aliGreen = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			setBackground(Color.green);
			circlecount = 0;
		}
	};

	ActionListener aliRed = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			setBackground(Color.red);
			circlecount = 0;
		}
	};

	ActionListener aliYellow = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			setBackground(Color.yellow);
			circlecount = 0;
		}
	};

	ActionListener aliWhite = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			setBackground(Color.white);
			circlecount = 0;
		}
	};

	ActionListener aliRadius = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			// Dialogfenster anlegen und konfigurieren, Textfeld und Button anlegen 
			dialog = new Dialog(null, true);
			dialog.setSize(300, 300);
			dialog.setLocation(550, 350);
			dialog.setTitle("Kreisradius anpassen");
			
			dialog.setLayout(new FlowLayout());
			final JTextField textfield = new JTextField(radius + "", 10);
			Button button = new Button("OK");
			
			//Text, Textfeld Button dem Dialogfenster hinzufügen
			dialog.add(new Label("Bitte geben Sie einen neuen Kreisradius ein: "));
			dialog.add(textfield);
			dialog.add(button);
			
			//Dem Button einen Actionlistener hinzufügen, um den Inhalt des Feldes an die radius-Variable zu übergeben
			//Fehlercheck, ob die Eingabe im Feld auch eine Zahl ist
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String string = textfield.getText();
					if (validateInt(string)) {
						radius = Math.abs(Integer.parseInt(string));
						drawBar();
						circlecount = 0;
					} else {
						System.out.println("ERROR: Eingabe war keine Zahl!");
					}
					;
					dialog.setVisible(false);
				}
			});
			dialog.setVisible(true);
		}
	};

	public void mouseClicked(MouseEvent e) {
		// ClickCount == 2 ist Doppelklick, also zeichne Kreis wenn Doppelklick passiert
		if (e.getClickCount() == 2) {
			paint(this.getGraphics(), e.getX(), e.getY());
		}
	}

	public void paint(Graphics g, int x, int y) {
		Graphics2D g2 = (Graphics2D) g;
		// Circlecount == 2 -> 2 Kreise aktuell im Fenster, also löschen und Count resetten
		if (circlecount == 2) {
			clear(g2);
			circlecount = 0;

			//Koordinaten der Kreise auf 0 setzen
			for (int i = 0; i < 2; i++) {
				for (int j = 0; j < 2; j++) {
					circlecoords[i][j] = 0;
				}
			}
		} else {
			//Koordinaten der Kreise setzen
			circlecoords[circlecount][0] = x;
			circlecoords[circlecount][1] = y;
			circlecount++;
			
			// X und Y werden um die Hälfte des Radius des Kreises subtrahiert, damit der
			// Mittelpunkt des Kreises genau auf dem Mauszeiger liegt
			g2.drawOval(x - (radius / 2), y - (radius / 2), radius, radius);
			g2.drawString("Kreis: " + circlecount + ", X: " + x + ", Y: " + y, 20, 50 + (circlecount * 20));

			// Zeichne Abstände und Linie nur wenn 2 Kreise existieren, aber bevor alles beim dritten Doppelklick gelöscht wird
			if (circlecount == 2) {
				// Rechnet jeweils x1-x2 und y1-y2, bildet danach den Betrag und zeigt jeweils Abstände in X- und Y- Richtung voneinander
				int abstandx = Math.abs(circlecoords[0][0]) - Math.abs(circlecoords[1][0]);
				int abstandy = Math.abs(circlecoords[0][1]) - Math.abs(circlecoords[1][1]);
				g2.drawString("Abstand in X: " + Math.abs(abstandx) + "", 20, 50 + (circlecount * 30));
				g2.drawString("Abstand in Y: " + Math.abs(abstandy) + "", 20, 50 + (circlecount * 40));
				
				// Errechnet den Abstand mit Hilfe des Satzes von Pythagoras und gibt ihn auf dem Bildschirm aus
				double abstand = Math.sqrt(Math.pow(Math.abs(circlecoords[1][0] - circlecoords[0][0]), 2) + Math.pow(Math.abs(circlecoords[1][1] - circlecoords[1][0]), 2));
				abstand = (double) Math.round(abstand * 100) / 100;
				g2.drawString("Abstand der Punkte: " + abstand, 20, 50 + (circlecount * 50));
				
				// Zeichnet die Linie zwischen den beiden Mittelpunkten der Kreise
				g2.drawLine(circlecoords[0][0], circlecoords[0][1], circlecoords[1][0], circlecoords[1][1]);
			}
		}
	}

	public void clear(Graphics2D g2) {
		// Löscht den Bereich 0/0 bis Width/Height des Fensters
		g2.clearRect(0, 0, this.getWidth(), this.getHeight());
	}

	public void windowClosing(WindowEvent windowEvent) {
		// Beende Programm, wenn das rote X gedrückt wird
		System.exit(0);
		// Wenn das Fenster geschlossen und das Programm weiterlaufen soll
		// dispose();
	}

	public boolean validateInt(String string) {
		//Überprüfe, ob Zeichenkette nur Zahlen enthält
		//Andernfalls, return false
		try {
			Integer.parseInt(string);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public void drawBar() {
		//Aufruf aus Konstruktor: Lege zweiten MenuEintrag an
		//Aufruf aus aliRadius: Aktualisiere zweiten MenuEintrag, sodass immer der aktuelle Radius um Menu steht
		mb.remove(menu2);
		menu2 = new Menu("Kreisradius: " + radius);
		MenuItem radius = new MenuItem("verändern");
		menu2.add(radius);
		radius.addActionListener(aliRadius);
		mb.add(menu2);
	}

	/////////////////////////////////////////////////////////////////////////////
	/// Muss implementiert sein, wird in diesem Programm aber nicht verwendet ///
	/////////////////////////////////////////////////////////////////////////////
	
	public void mousePressed(MouseEvent e) {
		// Event Mouse Pressed, nicht benötigt
	}

	public void mouseReleased(MouseEvent e) {
		// Event Mouse Released, nicht benötigt
	}

	public void mouseEntered(MouseEvent e) {
		// Event Mouse Entered, nicht benötigt
	}

	public void mouseExited(MouseEvent e) {
		// Event Mouse Exited, nicht benötigt
	}

	public void windowOpened(WindowEvent e) {
		// Event Fenster öffnet sich, nicht benötigt
	}

	public void windowClosed(WindowEvent e) {
		// Event Fenster schließt, nicht benötigt
	}

	public void windowIconified(WindowEvent e) {
	}

	public void windowDeiconified(WindowEvent e) {
	}

	public void windowActivated(WindowEvent e) {
	}

	public void windowDeactivated(WindowEvent e) {
	}

}