package com.qidian.frame1;
import java.awt.Color;
import java.awt.Frame;
public class TestFrame {
	public static void main(String[] args) {
		Frame f = new Frame("my first frame test!");
		f.setSize(500, 400);
		f.setLocation(100, 50);
		f.setBackground(Color.red);
		f.setResizable(false);
		f.setVisible(true);
	}
}
