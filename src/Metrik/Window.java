package Metrik;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

public class Window extends Frame implements MouseListener {
	int circlecount = 0;
	Color color = Color.white;

	public Window() {
		this.addMouseListener(this);
		

		// Fenster erstellen
		setSize(500, 500);
		setLayout(null);
		setVisible(true);
		setTitle("Metrik Version 1.0");

		// Menüleiste anlegen
		MenuBar mb = new MenuBar();
		Menu menu = new Menu("Hintergrundfarbe");
		MenuItem blue = new MenuItem("Blau");
		MenuItem green = new MenuItem("Grün");
		MenuItem red = new MenuItem("Rot");
		MenuItem yellow = new MenuItem("Gelb");
		MenuItem white = new MenuItem("Weiß");

		menu.add(blue);
		menu.add(green);
		menu.add(red);
		menu.add(yellow);
		menu.add(white);

		blue.addActionListener(aliBlue);
		green.addActionListener(aliGreen);
		red.addActionListener(aliRed);
		yellow.addActionListener(aliYellow);
		white.addActionListener(aliWhite);

		mb.add(menu);
		setMenuBar(mb);
	}

	ActionListener aliBlue = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			setBackground(Color.blue);
			color = Color.blue;
			circlecount = 0;
		}
	};
	ActionListener aliGreen = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			setBackground(Color.green);
			color = Color.green;
			circlecount = 0;
		}
	};
	ActionListener aliRed = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			setBackground(Color.red);
			color = Color.red;
			circlecount = 0;
		}
	};
	ActionListener aliYellow = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			setBackground(Color.yellow);
			color = Color.yellow;
			circlecount = 0;
		}
	};
	ActionListener aliWhite = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			setBackground(Color.white);
			color = Color.white;
			circlecount = 0;
		}
	};

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 2) {
			Graphics g = this.getGraphics();
			paint(g, e.getX(), e.getY());
		}
	}

	public void paint(Graphics g, int x, int y) {
		Graphics2D g2 = (Graphics2D) g;
		if (circlecount == 2) {
			System.out.println("Feld geleert");
//			g2.setBackground(Color.white);
//			g2.setBackground(color);
			
			g2.setColor(color);
			g2.fillRect(0, 0, 1920, 1080);
			g2.setColor(Color.black);
			circlecount = 0;
		} else {
			System.out.println("Kreis " + ++circlecount);
			// X und Y werden um die Hälfte der Größe des Kreises subtrahiert, damit der
			// Mittelpunkt des Kreises genau auf dem Mauszeiger liegt
			g2.drawOval(x - 50, y - 50, 100, 100);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
//		System.out.println("Mouse " + e.getClickCount() + " times pressed at " + e.getPoint());
	}

	@Override
	public void mouseReleased(MouseEvent e) {
//		System.out.println("Mouse " + e.getClickCount() + " times pressed at " + e.getPoint());
	}

	@Override
	public void mouseEntered(MouseEvent e) {
//		System.out.println("Mouse entered at " + e.getPoint());
	}

	@Override
	public void mouseExited(MouseEvent e) {
//		System.out.println("Mouse exited at " + e.getPoint());
	}

}