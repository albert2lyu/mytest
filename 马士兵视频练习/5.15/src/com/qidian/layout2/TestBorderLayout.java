package com.qidian.layout2;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Frame;
public class TestBorderLayout {
	/**
	 * @param args
	 * BorderLayout
	 */
	public static void main(String[] args) {
		Frame f = new Frame("My BorderLayout Test!");
		Button b1 = new Button("N");
		Button b2 = new Button("S");
		Button b3 = new Button("W");
		Button b4 = new Button("E");
		Button b5 = new Button("C");
		f.add(b1, BorderLayout.NORTH);
		f.add(b2, BorderLayout.SOUTH);
		f.add(b3, BorderLayout.WEST);
		f.add(b4, BorderLayout.EAST);
		f.add(b5, BorderLayout.CENTER);
		f.setSize(200,200);
		f.setLocation(300,200);
		f.setVisible(true);
	}
}
