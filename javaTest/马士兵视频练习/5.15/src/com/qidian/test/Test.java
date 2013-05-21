package com.qidian.test;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Panel;
public class Test {
	/**
	 * @param args
	 * 组成比较复杂的布局
	 */
	public static void main(String[] args) {
		Frame f = new Frame("My Frame!");
		f.setSize(300,200);
		f.setLocation(300,250);
		f.setBackground(Color.red);
		f.setLayout(new GridLayout(2,1));
		Panel p1 = new Panel(new BorderLayout());
		Panel p2 = new Panel(new BorderLayout());
		Panel p11 = new Panel(new GridLayout(2,1));
		Panel p21 = new Panel(new GridLayout(2,2));
		p1.add(p11, BorderLayout.CENTER);
		p11.add(new Button("button"));
		p11.add(new Button("button"));
		p1.add(new Button("button"), BorderLayout.WEST);
		p1.add(new Button("button"), BorderLayout.EAST);
		p2.add(p21, BorderLayout.CENTER);
		for(int i=0; i<4; i++){
			p21.add(new Button("button"));
		}
		p2.add(new Button("button"), BorderLayout.WEST);
		p2.add(new Button("button"), BorderLayout.EAST);
		f.add(p1);
		f.add(p2);
		f.setVisible(true);
	}
}
