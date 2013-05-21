package com.qidian.frame2;
import java.awt.Color;
import java.awt.Frame;
public class TestFrame {
	public static void main(String[] args) {
		MyFrame mf1 = new MyFrame(100,100,200,200,Color.red);
		MyFrame mf2 = new MyFrame(300,100,200,200,Color.blue);
		MyFrame mf3 = new MyFrame(100,300,200,200,Color.yellow);
		MyFrame mf4 = new MyFrame(300,300,200,200,Color.gray);
	}
}
class MyFrame extends Frame {
	static int id;
	public MyFrame(int x, int y, int w, int h, Color c){
		super("MyFrame " + (++id));
		setBackground(c);
		setLayout(null);
		setBounds(x, y, w, h);
		setVisible(true);
	}
}