package com.sun.mouse;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;
public class TestMouse {
	/**
	 * @param args
	 * MouseListener and MouseAdapter
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new MyFrame("MyFrame Test!");
	}
}
class MyFrame extends Frame {
	private ArrayList points;
	public MyFrame(String s){
		super(s);
		points = new ArrayList();
		setLayout(null);
		setBounds(400, 400, 400, 400);
		setBackground(new Color(200, 208, 255));
		setVisible(true);
		this.addMouseListener(new Monitor());
	}
	public void paint(Graphics g){
		Iterator i = points.iterator();
		while(i.hasNext()){
			Point p = (Point)i.next();
			g.setColor(Color.blue);
			g.fillOval(p.x, p.y, 5, 5);
		}
	}
	public void addPoint(Point p){
		points.add(p);
	}
}
class Monitor extends MouseAdapter {
	public void mousePressed(MouseEvent e){
		MyFrame mf = (MyFrame)e.getSource();
		mf.addPoint(new Point(e.getX(), e.getY()));
		mf.repaint();
	}
}

