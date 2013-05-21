package com.eset.actionevevt;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class TestActionEvent {
	public static void main(String[] args) {
		Frame f = new Frame("My Test Action Event!");
		Button b1 = new Button("start!");
		Button b2 = new Button("stop!");
		Monitor m = new Monitor();
		b1.addActionListener(m);
		b2.addActionListener(m);
		b2.setActionCommand("game over");
		f.add(b1,BorderLayout.NORTH);
		f.add(b2,BorderLayout.CENTER);
		f.pack();
		f.setVisible(true);
	}
}
class Monitor implements ActionListener{
	public void actionPerformed(ActionEvent e){
		System.out.println("A button is pressed!" + e.getActionCommand());
	}
}