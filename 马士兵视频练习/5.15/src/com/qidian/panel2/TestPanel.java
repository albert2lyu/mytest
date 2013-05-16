package com.qidian.panel2;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Panel;
public class TestPanel {
	public static void main(String[] args) {
		MyFrame my = new MyFrame("MyTestFramePanle", 300, 400, 300, 400);
	}
}
class MyFrame extends Frame{
	private Panel p1,p2,p3,p4;
	public MyFrame(String s, int x, int y, int w, int h){
		super(s);
		setLayout(null);
		p1 = new Panel(null);
		p2 = new Panel(null);
		p3 = new Panel(null);
		p4 = new Panel(null);
		p1.setBounds(0, 0, w/2, h/2);
		p2.setBounds(0, y/2, w/2, h/2);
		p3.setBounds(x/2, 0, w/2, h/2);
		p4.setBounds(x/2, y/2, w/2, h/2);
		p1.setBackground(Color.red);
		p2.setBackground(Color.black);
		p3.setBackground(Color.blue);
		p4.setBackground(Color.yellow);
		add(p1);
		add(p2);
		add(p3);
		add(p4);
		setBounds(x, y, w, h);
		setVisible(true);
	}
}