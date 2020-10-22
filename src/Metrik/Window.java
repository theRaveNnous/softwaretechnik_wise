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
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class Window extends Frame implements MouseListener,WindowListener {

	private static final long serialVersionUID = 1L;
	int circlecount = 0;
	Color color = Color.white;

	public Window() {
		this.addMouseListener(this);	
		addWindowListener(this);

		// Fenster erstellen
		setSize(500, 500);
		setVisible(true);
		setTitle("Metrik Version 1.0");

		// Menüleiste anlegen, jedem Button einen ActionListener zuweisen
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
		setLocation(200,200);
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

	public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 2) {
			paint(this.getGraphics(), e.getX(), e.getY());
		}
	}

	public void paint(Graphics g, int x, int y) {
		Graphics2D g2 = (Graphics2D) g;
		if (circlecount == 2) {
			clear(g2);
			circlecount = 0;
		} else {
			// X und Y werden um die Hälfte der Größe des Kreises subtrahiert, damit der
			// Mittelpunkt des Kreises genau auf dem Mauszeiger liegt
			circlecount++;
			g2.drawOval(x - 50, y - 50, 100, 100);
			g2.drawString("X: " + x + ", Y: " + y, 20, 50 + (circlecount * 20));
		}
	}
	
	public void clear(Graphics2D g2) {
		//Setze Farbe zum Zeichnen auf die aktuelle Hintergrundfarbe und zeichne ein ausgefülltes Rechteck auf größe des Bildschirms
		setBackground(Color.magenta);
		setBackground(color);
		
	}

	public void windowClosing(WindowEvent windowEvent) {
        //Schließe Fenster, wenn das rote X gedrückt wird
        System.exit(0);
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

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		dispose();
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

}