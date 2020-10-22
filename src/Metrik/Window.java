package Metrik;

import java.awt.Button;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Label;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.*;

import javax.swing.JTextField;

public class Window extends Frame implements MouseListener, WindowListener {
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
		setTitle("Metrik Version 1.2.1");

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
			//Dialogfeld 
			dialog = new Dialog(null, true);
			dialog.setLayout(new FlowLayout());
//			dialog.setModal(true);
			final JTextField textfield = new JTextField(radius + "", 10);
			Button button = new Button("OK");
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dialog.setVisible(false);
					String string = textfield.getText();
					if (validateInt(string)) {
						radius = Integer.parseInt(string);
						drawBar();
						circlecount = 0;
					} else {
						System.out.println("ERROR: Eingabe war keine Zahl!");
					}
					;
				}
			});

			dialog.add(new Label("Bitte geben Sie einen neuen Kreisradius ein: "));
			dialog.add(textfield);
			dialog.add(button);
			dialog.setSize(300, 300);
			dialog.setLocation(550, 350);
			dialog.setVisible(true);
		}
	};

	public void mouseClicked(MouseEvent e) {
		// ClickCount == 2 ist Doppelklick
		if (e.getClickCount() == 2) {
			paint(this.getGraphics(), e.getX(), e.getY());
		}
	}

	public void paint(Graphics g, int x, int y) {
		Graphics2D g2 = (Graphics2D) g;
		// Circlecount == 2 -> 2 Kreise aktuell im Fenster, also löschen und Count
		// resetten
		if (circlecount == 2) {
			clear(g2);
			circlecount = 0;
			
			circlecoords[0][0] = 0;
			circlecoords[0][1] = 0;
			circlecoords[1][0] = 0;
			circlecoords[1][1] = 0;
		} else {
			circlecoords[circlecount][0] = x;
			circlecoords[circlecount][1] = y;
			circlecount++;
			// X und Y werden um die Hälfte des Radius des Kreises subtrahiert, damit der
			// Mittelpunkt des Kreises genau auf dem Mauszeiger liegt
			g2.drawOval(x - (radius / 2), y - (radius / 2), radius, radius);
			g2.drawString("Kreis: " + circlecount + ", X: " + x + ", Y: " + y, 20, 50 + (circlecount * 20));
			
			if (circlecount == 2) {
				int abstandx = Math.abs(circlecoords[0][0]) - Math.abs(circlecoords[1][0]);
				int abstandy = Math.abs(circlecoords[0][1]) - Math.abs(circlecoords[1][1]);
				g2.drawString("Abstand in X: " + Math.abs(abstandx) + "", 20, 50 + (circlecount * 30));
				g2.drawString("Abstand in Y: " + Math.abs(abstandy) + "", 20, 50 + (circlecount * 40));
			}
		}
	}

	public void clear(Graphics2D g2) {
		// Löscht den Bereich 0/0 bis maxWidth/MaxHeight des Fensters
		g2.clearRect(0, 0, this.getWidth(), this.getHeight());
	}

	public void windowClosing(WindowEvent windowEvent) {
		// Schließe Fenster, wenn das rote X gedrückt wird
		dispose();
	}

	public boolean validateInt(String string) {
		try {
			Integer.parseInt(string);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public void drawBar() {
		mb.remove(menu2);
		menu2 = new Menu("Kreisradius: " + radius);
		MenuItem radius = new MenuItem("verändern");
		menu2.add(radius);
		radius.addActionListener(aliRadius);
		mb.add(menu2);
	}

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