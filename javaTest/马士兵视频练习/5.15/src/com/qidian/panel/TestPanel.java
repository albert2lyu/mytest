package com.qidian.panel;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Panel;
public class TestPanel {
	public static void main(String[] args) {
		Frame f = new Frame("Test Panel!");
		Panel p = new Panel(null);
		f.setLayout(null);
		f.setBackground(new Color(0,0,133));
		f.setBounds(300,300,500,500);
		p.setBackground(new Color(123,145,0));
		p.setBounds(50,50,400,400);
		f.add(p);
		f.setVisible(true);
	}
}
