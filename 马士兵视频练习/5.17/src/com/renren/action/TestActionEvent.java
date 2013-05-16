package com.renren.action;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class TestActionEvent {
	public static void main(String[] args) {
		Frame f = new Frame("ActionEvent");
		Button b = new Button("press me");
		Monitor m = new Monitor();
		b.addActionListener(m);
		f.add(b, BorderLayout.CENTER);
		f.pack();
		f.setVisible(true);
	}
}
class Monitor implements ActionListener{
	public void actionPerformed(ActionEvent e) {
		System.out.println("ok press me over!");		
	}
}