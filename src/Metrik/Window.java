package Metrik;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Ellipse2D.Float;
import java.awt.event.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

public class Window extends Frame implements MouseListener {
	int circlecount = 0;

	public Window() {
		setSize(300, 300);
		setLayout(null);
		setVisible(true);
		setTitle("Metrik Version 1.0");
		this.addMouseListener(this);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 2) {
			Graphics g = this.getGraphics();
			paint(g, e.getX(), e.getY());
		}
	}

//	@Override
//	public void paint(Graphics g) {
//		Graphics2D g2 = (Graphics2D) g;
//	}

	public void paint(Graphics g, int x, int y) {
		Graphics2D g2 = (Graphics2D) g;
		if (circlecount == 2) {
			System.out.println("Feld geleert");
			g2.setColor(Color.white);
			g2.fillRect(0, 0, 1920, 1080);
			circlecount = 0;
		} else {
			System.out.println("Kreis " + ++circlecount);
			//X und Y werden um die Hälfte der Größe des Kreises subtrahiert, damit der Mittelpunkt des Kreises genau auf dem Mauszeiger liegt
			g2.drawOval(x - 25, y - 25, 50, 50);
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