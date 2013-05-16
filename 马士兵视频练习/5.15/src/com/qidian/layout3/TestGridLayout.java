package com.qidian.layout3;
import java.awt.Button;
import java.awt.Frame;
import java.awt.GridLayout;
public class TestGridLayout {
	/**
	 * @param args
	 * GridLayout
	 */
	public static void main(String[] args) {
		Frame f = new Frame("My GridLayout Test");
		Button b1 = new Button("B1");
		Button b2 = new Button("B2");
		Button b3 = new Button("B3");
		Button b4 = new Button("B4");
		Button b5 = new Button("B5");
		Button b6 = new Button("B6");
		f.setLayout(new GridLayout(3,2));
		f.add(b1);
		f.add(b2);
		f.add(b3);
		f.add(b4);
		f.add(b5);
		f.add(b6);
		f.pack();
		f.setVisible(true);
	}
}
