package com.qidian.layout;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
public class TestLayout {
	/**
	 * @param args
	 * FlowLayout 
	 */
	public static void main(String[] args) {
		Frame f = new Frame("MyFirstLayoutTest!");
		Button b1 = new Button("ok");
		Button b2 = new Button("open");
		Button b3 = new Button("back");
		f.setLayout(new FlowLayout(FlowLayout.LEFT,20,20));
		f.add(b1);
		f.add(b2);
		f.add(b3);
		f.setSize(500,500);
		f.setVisible(true);
	}
}
